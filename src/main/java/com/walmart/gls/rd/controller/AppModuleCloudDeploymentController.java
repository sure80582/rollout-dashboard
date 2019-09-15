package com.walmart.gls.rd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.gls.rd.entity.AppModuleCloudDeployment;
import com.walmart.gls.rd.repo.AppModuleCloudDeploymentRepo;
import com.walmart.gls.rd.utils.AuditValueUpdateUtils;

@RestController
@RequestMapping(value = "/appModuleCloudDeployment")
public class AppModuleCloudDeploymentController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppModuleCloudDeploymentRepo appModuleCloudDeploymentRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<AppModuleCloudDeployment> getAllUsersAppModuleCloudDeployment() {
		LOG.info("Getting all");
		return appModuleCloudDeploymentRepo.findAll();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public AppModuleCloudDeployment addAppModuleCloudDeployment(@RequestBody AppModuleCloudDeployment entity) {
		LOG.info("Saving..");
		AuditValueUpdateUtils.updateAuditDateValue(entity);
		return appModuleCloudDeploymentRepo.save(entity);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public AppModuleCloudDeployment updateAppModuleCloudDeployment(@RequestBody AppModuleCloudDeployment entity) {
		LOG.info("updating..");
		AuditValueUpdateUtils.updateAuditDateValue(entity);
		return appModuleCloudDeploymentRepo.save(entity);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void deleteAppModuleCloudDeployment(@RequestBody AppModuleCloudDeployment entity) {
		LOG.info("deleting..");
		appModuleCloudDeploymentRepo.delete(entity);
	}
}
