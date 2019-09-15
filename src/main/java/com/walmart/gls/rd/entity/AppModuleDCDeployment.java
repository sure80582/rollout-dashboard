package com.walmart.gls.rd.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.walmart.gls.rd.dataobj.IAuditDate;
import com.walmart.gls.rd.dataobj.ICompositeKey;

@Document(value = "app_module_dc_deployment")
public class AppModuleDCDeployment  implements IAuditDate, ICompositeKey {
	@Id 
	private String id;
	@NotNull	
	private String artifactId;
	@NotNull
	private String envType;
	@NotNull
	private String dc;
	@NotNull
	private String countryCode;
	
	@NotNull
	private String version;
	
	@NotNull
	private Boolean activeVersion;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant lastModifiedDate;

	private LocalDate deployedDate;
	
	public AppModuleDCDeployment() {
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getId() {
		return id;
	}

	public LocalDate getDeployedDate() {
		return deployedDate;
	}
	public void setDeployedDate(LocalDate deployedDate) {
		this.deployedDate = deployedDate;
	}
	
	public Instant getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getArtifactId() {
		return artifactId;
	}	
	public String getEnvType() {
		return envType;
	}
	public void setEnvType(String envType) {
		this.envType = envType;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Boolean getActiveVersion() {
		return activeVersion;
	}
	public void setActiveVersion(Boolean activeVersion) {
		this.activeVersion = activeVersion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppModuleDCDeployment other = (AppModuleDCDeployment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public String generateCompositeKey() {
		if(id == null) {
			id = artifactId.concat("_").concat(envType)
					.concat("_").concat(version)
					.concat("_").concat(dc)
					.concat("_").concat(countryCode)
					.concat("_").concat(deployedDate.toString());
		}
		return id;
	}
}
