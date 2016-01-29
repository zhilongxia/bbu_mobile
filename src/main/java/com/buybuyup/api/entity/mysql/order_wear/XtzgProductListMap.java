package com.buybuyup.api.entity.mysql.order_wear;

import java.util.HashMap;

import com.buybuyup.api.constants.SystemConstantInit;

public class XtzgProductListMap {

	public HashMap<String, String> getXtzgProductListMap() {

		HashMap<String, String> xtzgProductMap = new HashMap<String, String>();
		xtzgProductMap.put("type", "type");
		xtzgProductMap.put("contextPath", SystemConstantInit.host_config + "bbu_files/");
		xtzgProductMap.put("id", "id");
		xtzgProductMap.put("soNo", "soNo");
		xtzgProductMap.put("orderTime", "orderTime");
		xtzgProductMap.put("companyLogo", "companyLogo");
		xtzgProductMap.put("companyName", "companyName");
		xtzgProductMap.put("productId", "productId");
		xtzgProductMap.put("productName", "productName");
		xtzgProductMap.put("frontUserId", "frontUserId");
		xtzgProductMap.put("frontUserName", "frontUserName");
		xtzgProductMap.put("frontendOrderStatus", "frontendOrderStatus");
		xtzgProductMap.put("businessId", "businessId");
		xtzgProductMap.put("establish_flag", "establish_flag");
		xtzgProductMap.put("bookingAmount", "bookingAmount");
		xtzgProductMap.put("backRate", "backRate");
		xtzgProductMap.put("yearIncome", "yearIncome");
		return xtzgProductMap;
	}
}
