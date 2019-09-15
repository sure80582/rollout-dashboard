package com.walmart.gls.rd.entity;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.walmart.gls.rd.dataobj.IAuditDate;
import com.walmart.gls.rd.dataobj.ICompositeKey;

@Document(value = "app_module")
public class AppModule implements IAuditDate, ICompositeKey {
	@Id
	private String id;
	@NotNull
	private String artifactId;
	
	@NotNull
	private String moduleName;
	
	@CreatedDate
	private Instant createdDate;
	
	@LastModifiedDate
	private Instant lastModifiedDate;


	public void setId(String id) {
		this.id = id;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Instant getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
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
		AppModule other = (AppModule) obj;
		if (artifactId == null) {
			if (other.artifactId != null)
				return false;
		} else if (!artifactId.equals(other.artifactId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AppModule [artifactId=" + artifactId + ", moduleName=" + moduleName + "]";
	}

	
	public String generateCompositeKey() {
		if(id == null) {
			id = artifactId;
		}
		return id;
	}
	
	@Override
	public String getId() {
		return id;
	}
}
