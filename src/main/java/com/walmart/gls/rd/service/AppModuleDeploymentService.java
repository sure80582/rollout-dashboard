package com.walmart.gls.rd.service;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation.GroupOperationBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.operation.AggregateOperation;
import com.walmart.gls.rd.dataobj.CountryCodeAndDC;
import com.walmart.gls.rd.entity.AppModuleCloudDeployment;
import com.walmart.gls.rd.entity.AppModuleDCDeployment;
import com.walmart.gls.rd.entity.AppModuleVersion;
import com.walmart.gls.rd.repo.AppModuleCloudDeploymentRepo;
import com.walmart.gls.rd.repo.AppModuleDCDeploymentRepo;

//imports as static
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Repository
public class AppModuleDeploymentService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private AppModuleDCDeploymentRepo appModuleDCDeploymentRepo;	

	@Autowired
	private AppModuleCloudDeploymentRepo appModuleCloudDeploymentRepo;
	
	public List<AppModuleVersion> getVersionByArtifactId(String artifactId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("artifactId").is(artifactId));
		List<AppModuleVersion> list = mongoTemplate.find(query,AppModuleVersion.class);
		return list;
	}
	
	public List<AppModuleCloudDeployment> getCloudDeployemntByArtifactId(boolean activeOnly,String artifactId) {
		Query query = new Query();
		if(artifactId != null) {
			query.addCriteria(Criteria.where("artifactId").is(artifactId));
		}
		if(activeOnly) {
			query.addCriteria(Criteria.where("activeVersion").is(true));			
		}
		List<AppModuleCloudDeployment> list = mongoTemplate.find(query,AppModuleCloudDeployment.class);
		return list;
	}
	
	public List<AppModuleDCDeployment> getDCdDeployemntByArtifactIdCCAndDC(boolean activeOnly, String artifactId, String countryCode, String dc) {
		Query query = new Query();
		if(artifactId != null) {
			query.addCriteria(Criteria.where("artifactId").is(artifactId));
		}
		if(countryCode != null) {
			query.addCriteria(Criteria.where("countryCode").is(countryCode));
		}
		if(dc != null) {
			query.addCriteria(Criteria.where("dc").is(dc));
		}
		if(activeOnly) {
			query.addCriteria(Criteria.where("activeVersion").is(true));			
		}
		List<AppModuleDCDeployment> list = mongoTemplate.find(query,AppModuleDCDeployment.class);
		return list;
	}
	
	
	public List<CountryCodeAndDC> getAllCCAndDC() {
		Aggregation agg = newAggregation(
				group("artifactId","countryCode", "dc"),
				project("artifactId","countryCode", "dc")
			);
		
		AggregationResults<CountryCodeAndDC> groupResult = mongoTemplate.aggregate(agg, AppModuleDCDeployment.class, CountryCodeAndDC.class);
		List<CountryCodeAndDC> list = groupResult.getMappedResults();
		return list;
	}
	
	public AppModuleCloudDeployment saveCloudDeployment(AppModuleCloudDeployment deployment) {
		if(deployment.getActiveVersion()) {
			Query query = new Query();
			query.addCriteria(Criteria.where("artifactId").is(deployment.getArtifactId()));
			query.addCriteria(Criteria.where("envType").is(deployment.getEnvType()));
			query.addCriteria(Criteria.where("activeVersion").is(true));
			
			Update update = new Update();
			update.set("activeVersion", false);
			update.set("lastModifiedDate", Instant.now());
			
			UpdateResult result = mongoTemplate.updateMulti(query, update, AppModuleCloudDeployment.class);
			LOG.debug("Updated Size: {}" + result.getModifiedCount());
		}
		return appModuleCloudDeploymentRepo.save(deployment);
	}
	
	public AppModuleDCDeployment saveDCDeployment(AppModuleDCDeployment deployment) {
		if(deployment.getActiveVersion()) {
			Query query = new Query();
			query.addCriteria(Criteria.where("artifactId").is(deployment.getArtifactId()));
			query.addCriteria(Criteria.where("envType").is(deployment.getEnvType()));
			query.addCriteria(Criteria.where("countryCode").is(deployment.getCountryCode()));
			query.addCriteria(Criteria.where("dc").is(deployment.getDc()));
			query.addCriteria(Criteria.where("activeVersion").is(true));
			
			Update update = new Update();
			update.set("activeVersion", false);
			update.set("lastModifiedDate", Instant.now());
			UpdateResult result = mongoTemplate.updateMulti(query, update, AppModuleDCDeployment.class);
			LOG.debug("Updated Size: {}" + result.getModifiedCount());
		}
		return appModuleDCDeploymentRepo.save(deployment);
	}
	
}
