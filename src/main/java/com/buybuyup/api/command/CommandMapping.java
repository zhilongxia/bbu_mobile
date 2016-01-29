package com.buybuyup.api.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.buybuyup.api.utils.Command;

@Service
public class CommandMapping {

	public List<Command<Object>> productsCmd(HashMap<String, Object> paramMap) {

		List<Command<Object>> cmList = new ArrayList<Command<Object>>();
		cmList.add(new ProducXtzgListCmd(paramMap));

		return cmList;
	}

}
