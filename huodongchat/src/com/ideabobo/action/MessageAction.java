package com.ideabobo.action;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.ideabobo.model.Message;
import com.ideabobo.service.MessageService;
import com.ideabobo.util.GetNowTime;
import com.ideabobo.util.IdeaAction;
import com.ideabobo.util.Page;

@Controller
public class MessageAction extends IdeaAction {
	@Resource
	private MessageService messageService;
	private static final long serialVersionUID = -3218238026025256103L;
	private Message message;
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String message(){
		return SUCCESS;
	}
	
	public void getList(){
		String messagename = request.getParameter("smessagename");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		Page page = new Page();
		Map paramsMap = new HashMap();
		paramsMap.put("messagename", messagename);
		paramsMap.put("sort", "order by "+sort+" "+order);
		String pageNo = (String) this.request.getParameter("page");
		String pageSizes = (String) this.request.getParameter("rows");
		if (pageNo == null) {
			page.setPageSize(10);
			page.setPageNo(1);
		} else {
			page.setPageSize(Integer.parseInt(pageSizes));
			page.setPageNo(Integer.parseInt(pageNo));
		}
		page = messageService.findByPage(page, paramsMap);
		Gson json = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("rows", page.getList());
		render(json.toJson(map));		
	}
	
	public void add(){
		String action = request.getParameter("action");
		if(message != null){
			if(action.equals("add")){
				messageService.save(message);
				render("操作成功!");
			}else {
				String id = request.getParameter("id");
				message.setId(Integer.parseInt(id));
				messageService.update(message);
				render("操作成功!");
			}
		}		
	}
	
	public void deleteItem(){
		String id = request.getParameter("id");
		messageService.delete(Integer.parseInt(id));
		render("操作成功");
	}
	

}
