package com.walmart.gls.rd.dataobj;

import java.time.Instant;

public interface IAuditDate {
	public void setCreatedDate(Instant createdDate);
	public Instant getCreatedDate();
	public Instant getLastModifiedDate();
	public void setLastModifiedDate(Instant lastModifiedDate);
}
