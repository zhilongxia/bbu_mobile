package com.buybuyup.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buybuyup.api.dao.mysql.ProductXtzgDao;
import com.buybuyup.api.entity.mysql.order_wear.XtzgProductListMap;
import com.buybuyup.api.service.IProductXtzgService;
import com.buybuyup.api.utils.HashMapUtils;

@Service
public class ProductXtzgServiceImpl implements IProductXtzgService {

	@Autowired
	private ProductXtzgDao xtzgDao;

	public HashMapUtils hashMapUtils = new HashMapUtils();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<HashMap<String, Object>> getXtzgProductList(HashMap<String, Object> extra,
			HashMap<String, Object> paramMap) {

		List<HashMap<String, Object>> xtzgList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.putAll(paramMap);
		if (!paramMap.containsKey("id")) {
			conditionMap.put("id", 0);
		}
		if (!paramMap.containsKey("pageNum")) {
			conditionMap.put("pageNum", 5);
		}
		logger.info("首页信托资管产品列表，查询参数：" + conditionMap);
		List<HashMap<String, Object>> entityList = this.xtzgDao.getProductMainByParam(conditionMap);
		for (HashMap<String, Object> productMap : entityList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			hashMapUtils.conver_userJson_map_view(map, productMap, new XtzgProductListMap().getXtzgProductListMap());
			xtzgList.add(map);
		}
		return xtzgList;
	}
}
