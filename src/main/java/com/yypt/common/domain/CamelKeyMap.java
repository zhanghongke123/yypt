package com.yypt.common.domain;

import com.google.common.base.CaseFormat;
import java.util.HashMap;

public class CamelKeyMap extends HashMap {

	@Override
	public Object put(Object key, Object value) {
		key = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,String.valueOf(key));
		return super.put(key, value);
	}
}