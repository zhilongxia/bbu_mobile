package com.buybuyup.api.dao.mysql;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buybuyup.api.mapper.ProductXtzgMapper;

@Repository
public class ProductXtzgDao {

	@Autowired
	private ProductXtzgMapper mapper;

	public List<HashMap<String, Object>> getProductMainByParam(HashMap<String, Object> conditionMap) {
		return mapper.getProductMainByParam(conditionMap);
	}

	public HashMap<String, Object> getProductDetail(HashMap<String, Object> conditionMap) {
		return mapper.getProductDetail(conditionMap);
	}

}
