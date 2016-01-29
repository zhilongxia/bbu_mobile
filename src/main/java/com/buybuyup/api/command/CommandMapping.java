package com.buybuyup.api.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.buybuyup.api.utils.Command;

@Service
public class CommandMapping {

	// 产品列表 1信托产品 2资管产品 3 保险产品4私募基金
	public List<Command<Object>> productsCmd(HashMap<String, Object> paramMap) {

		List<Command<Object>> cmList = new ArrayList<Command<Object>>();
		cmList.add(new ProducXtzgListCmd(paramMap));

		return cmList;
	}

}
