package com.walmart.gls.rd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.gls.rd.dataobj.CountryCodeAndDC;
import com.walmart.gls.rd.entity.AppModuleDCDeployment;
import com.walmart.gls.rd.repo.AppModuleDCDeploymentRepo;
import com.walmart.gls.rd.service.AppModuleDeploymentService;
import com.walmart.gls.rd.utils.AuditValueUpdateUtils;


@RestController
@RequestMapping(value = "/appModuleDCDeployment")
public class AppModuleDCDeploymentController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppModuleDeploymentService appModuleDeploymentService;
	
	@Autowired
	private AppModuleDCDeploymentRepo appModuleDCDeploymentRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<AppModuleDCDeployment> getActiveCloudDeployemntByArtifactId(
			@RequestParam(name = "artifactId", required = false) String artifactId,
			@RequestParam(name = "countryCode", required = false) String countryCode,
			@RequestParam(name = "dc", required = false) String dc) {
		LOG.info("Getting all");
		return appModuleDeploymentService.getDCdDeployemntByArtifactIdCCAndDC(true, artifactId, countryCode, dc);
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public List<AppModuleDCDeployment> getCloudDeployemntByArtifactId(
			@RequestParam(name = "artifactId", required = false) String artifactId,
			@RequestParam(name = "countryCode", required = false) String countryCode,
			@RequestParam(name = "dc", required = false) String dc) {
		LOG.info("Getting all");
		return appModuleDeploymentService.getDCdDeployemntByArtifactIdCCAndDC(false, artifactId, countryCode, dc);
	}
	
	@RequestMapping(value = "/allCountryCodeAndDc", method = RequestMethod.GET)
	public List<CountryCodeAndDC> getAllCountryCodeAndDc() {
		LOG.info("Getting getAllCountryCodeAndDc");
		return appModuleDeploymentService.getAllCCAndDC();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public AppModuleDCDeployment addAppModuleDCDeployment(@RequestBody AppModuleDCDeployment entity) {
		LOG.info("Saving..");
		AuditValueUpdateUtils.updateAuditDateValue(entity);
		return appModuleDeploymentService.saveDCDeployment(entity);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public AppModuleDCDeployment updateAppModuleDCDeployment(@RequestBody AppModuleDCDeployment entity) {
		LOG.info("updating..");
		AuditValueUpdateUtils.updateAuditDateValue(entity);
		return appModuleDeploymentService.saveDCDeployment(entity);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void deleteAppModuleDCDeployment(@RequestBody AppModuleDCDeployment entity) {
		LOG.info("deleting..");
		appModuleDCDeploymentRepo.delete(entity);
	}
}
