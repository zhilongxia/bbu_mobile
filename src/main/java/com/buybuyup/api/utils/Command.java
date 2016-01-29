package com.buybuyup.api.utils;

public interface Command<T> {
	
	public T execute();

	public String getKey();
}
