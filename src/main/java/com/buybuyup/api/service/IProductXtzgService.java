package com.buybuyup.api.service;

import java.util.HashMap;
import java.util.List;

public interface IProductXtzgService {

	public List<HashMap<String, Object>> getXtzgProductList(HashMap<String, Object> extra,
			HashMap<String, Object> paramMap);
}
