package com.buybuyup.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class HashMapUtils {

	/**
	 * formatMap.put("habitSmoking", "smoke"); {"habitSmoking" : 0}
	 * 
	 * @param toMap
	 * @param json
	 * @param formatMap
	 */
	public void conver_Json_map_save(HashMap<String, Object> toMap, JSONObject json,
			HashMap<String, String> formatMap) {

		for (Map.Entry<String, String> it : formatMap.entrySet()) {
			String key = it.getKey();
			String dbKey = it.getValue();
			if (json.get(key) == null || "null".equals(json.getString(key))) {
				System.out.println("key==" + key);
				continue;
			}
			toMap.put(dbKey, json.get(key));
		}
	}

	/**
	 * 
	 * formatMap.put("habitSmoking", "smoke"); dbMap.put("smoke", "1");
	 * 
	 * @param toMap
	 * @param fromMap
	 * @param formatMap
	 */
	public void conver_userJson_map_view(HashMap<String, Object> toMap, HashMap<String, Object> dbMap,
			HashMap<String, String> formatMap) {

		for (Map.Entry<String, String> it : formatMap.entrySet()) {
			String key = it.getKey();
			String dbKey = it.getValue();
			if (dbMap.containsKey(dbKey)) {
				toMap.put(key, dbMap.get(dbKey));
			}
		}
	}
}
