package com.ideabobo.service;

import java.util.List;
import java.util.Map;

import com.ideabobo.model.Message;
import com.ideabobo.util.Page;

public interface MessageService {
	public void save(Message model);
	public void update(Message model);
	public Message find(String uuid);
	public Message find(Message model);
	public void delete(Integer uuid);
	public List<Message> list();
	public Page findByPage(Page page,Map paramsMap);
}
