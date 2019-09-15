package com.walmart.gls.rd.utils;

import java.time.Instant;

import com.walmart.gls.rd.dataobj.IAuditDate;
import com.walmart.gls.rd.dataobj.ICompositeKey;

public class AuditValueUpdateUtils {
	
	public static Object updateAuditDateValue(Object obj) {
		if(obj instanceof IAuditDate) {
			IAuditDate entity = (IAuditDate) obj;
			if(entity.getCreatedDate() == null) {
				entity.setCreatedDate(Instant.now());
			}
			entity.setLastModifiedDate(Instant.now());
		}
		if(obj instanceof ICompositeKey) {
			((ICompositeKey) obj).generateCompositeKey();
		}
		return obj;
	}
}
