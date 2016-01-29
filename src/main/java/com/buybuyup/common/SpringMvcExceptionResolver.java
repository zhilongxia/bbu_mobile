package com.buybuyup.common;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class SpringMvcExceptionResolver implements HandlerExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public @ResponseBody ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception e) {

		this.logger.info("e= SpringMvcExceptionResolver: " + e.getMessage());
		Map<String, Object> result = new HashMap<String, Object>();
		if ((e instanceof IOException)) {
			result.put("code", "1");
			result.put("msg", "文件操作出错!");
		} else if ((e instanceof SQLException)) {
			result.put("code", "2");
			result.put("msg", "数据库错误(SQL异常)!");
		} else if ((e instanceof RuntimeException)) {
			result.put("code", "3");
			result.put("msg", "运行程序异常! e=" + e.getMessage());
		} else if ((e instanceof RemoteException)) {
			result.put("code", Integer.valueOf(999));
			result.put("msg", "远程端口调用(RMI)错误!");
		} else if ((e instanceof Exception)) {
			result.put("code", "4");
			result.put("msg", "系统错误!");
		} else {
			result.put("code", "5");
			result.put("msg", "未知错误!");
		}
		e.printStackTrace();
		return beforeActionEndResolve(result);
	}

	public ModelAndView beforeActionEndResolve(Map<String, Object> result) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("forward:/jsp/common/json_result.jsp");
		conversionToJson(mav, result);
		return mav;
	}

	private void conversionToJson(ModelAndView mav, Map<String, Object> result) {

		try {
			mav.addObject("json_result", result);
		} catch (Exception e) {
			result = new HashMap<String, Object>();
			result.put("code", "0");
			result.put("app_result_key", "999");
			result.put("app_result_message_key", "JSON转换出错!");
			try {
				mav.addObject("json_result", result);
			} catch (Exception localException1) {
			}
		}
	}
}
