package com.buybuyup.api.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buybuyup.api.utils.Command;
import com.buybuyup.api.utils.ExtraMapAware;

public abstract class TemplateDispatch {

	private HashMap<String, Object> turnMap = new HashMap<String, Object>();

	@RequestMapping("/{command}")
	public @ResponseBody Map<String, Object> execute(@PathVariable String command, HttpServletRequest req) {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> extra = new HashMap<String, Object>();
		List<Command<Object>> commands = gateWay(req, command);
		for (Command<Object> c : commands) {
			result.put(c.getKey(), c.execute());
			if (c instanceof ExtraMapAware) {
				extra.putAll(((ExtraMapAware) c).getExtra());
			}
		}
		Map<String, Object> output = output(req, result, extra);
		return output;
	}

	public HashMap<String, Object> getParamMap(ArrayList<String> keyList) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		for (String key : keyList) {
			if (turnMap.get(key) != null && !"".equals(turnMap.get(key)))
				paramMap.put(key, turnMap.get(key));
		}
		return paramMap;
	}

	public abstract List<Command<Object>> gateWay(HttpServletRequest req, String index);

	public abstract Map<String, Object> output(HttpServletRequest req, Map<String, Object> result,
			Map<String, Object> extra);
}
