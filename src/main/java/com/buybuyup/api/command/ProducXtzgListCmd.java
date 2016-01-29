package com.buybuyup.api.command;

import java.util.HashMap;
import java.util.List;

import com.buybuyup.api.service.impl.ProductXtzgServiceImpl;
import com.buybuyup.api.utils.Command;
import com.buybuyup.api.utils.ExtraMapAware;
import com.buybuyup.common.SpringContextUtil;

public class ProducXtzgListCmd extends ExtraMapAware implements Command<Object> {

	private HashMap<String, Object> paramMap;

	public ProducXtzgListCmd(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	@Override
	public List<HashMap<String, Object>> execute() {

		HashMap<String, Object> extra = new HashMap<String, Object>();
		setExtra(extra);
		List<HashMap<String, Object>> resultList = getBean().getXtzgProductList(extra, paramMap);
		return resultList;
	}

	private ProductXtzgServiceImpl getBean() {
		return SpringContextUtil.getBean(ProductXtzgServiceImpl.class);
	}

	@Override
	public String getKey() {

		return "productList";
	}
}
