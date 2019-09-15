package com.walmart.gls.rd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.gls.rd.entity.AppModuleVersion;
import com.walmart.gls.rd.repo.AppModuleVersionRepo;
import com.walmart.gls.rd.service.AppModuleDeploymentService;
import com.walmart.gls.rd.utils.AuditValueUpdateUtils;

@RestController
@RequestMapping(value = "/appModuleVersion")
public class AppModuleVersionController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppModuleVersionRepo appModuleVersionRepo;
	

	@Autowired
	private AppModuleDeploymentService appModuleDeploymentService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<AppModuleVersion> getAllAppModuleVersion() {
		LOG.info("Getting all");
		return appModuleVersionRepo.findAll();
	}
	
	@RequestMapping(value = "/{artifactId}", method = RequestMethod.GET)
	public List<AppModuleVersion> getAppModuleVersionByArtifactId(@PathVariable(name = "artifactId") String artifactId) {
		LOG.info("Getting all by {}", artifactId);
		return appModuleDeploymentService.getVersionByArtifactId(artifactId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public AppModuleVersion addAppModuleVersion(@RequestBody AppModuleVersion entity) {
		LOG.info("Saving..");
		AuditValueUpdateUtils.updateAuditDateValue(entity);
		return appModuleVersionRepo.save(entity);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public AppModuleVersion updateAppModuleVersion(@RequestBody AppModuleVersion entity) {
		LOG.info("updating..");
		AuditValueUpdateUtils.updateAuditDateValue(entity);
		return appModuleVersionRepo.save(entity);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void deleteAppModuleVersion(@RequestBody AppModuleVersion entity) {
		LOG.info("deleting..");
		appModuleVersionRepo.delete(entity);
	}
}
