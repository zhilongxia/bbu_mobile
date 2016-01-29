package com.buybuyup.api.mapper;

import java.util.HashMap;
import java.util.List;

public interface ProductXtzgMapper {

	public List<HashMap<String, Object>> getProductMainByParam(HashMap<String, Object> conditionMap);

	public HashMap<String, Object> getProductDetail(HashMap<String, Object> conditionMap);

}
