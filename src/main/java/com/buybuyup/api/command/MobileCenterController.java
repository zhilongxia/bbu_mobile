package com.buybuyup.api.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buybuyup.api.utils.Command;
import com.buybuyup.api.utils.CommandConst;

@Controller
@RequestMapping({ "/gateway" })
public class MobileCenterController extends TemplateDispatch {

	@Autowired
	private CommandMapping mapping;

	@Override
	public List<Command<Object>> gateWay(HttpServletRequest req, String command) {

		if (CommandConst.productsCmd.equals(command)) {

			ArrayList<String> list = new ArrayList<String>(Arrays.asList("tokenId", "id", "type", "pageNum"));
			return mapping.productsCmd(this.getParamMap(list));
		}
		return new ArrayList<Command<Object>>();
	}

	@Override
	public Map<String, Object> output(HttpServletRequest req, Map<String, Object> result, Map<String, Object> extra) {

		Map<String, Object> output = new HashMap<String, Object>();
		output.put("code", "0");
		output.put("msg", "");
		output.put("data", result);
		output.putAll(extra);

		return output;
	}
}
