package com.ideabobo.action;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.*;

import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ideabobo.model.Message;
import com.ideabobo.model.Posts;
import com.ideabobo.model.Qunzu;
import com.ideabobo.model.Replay;
import com.ideabobo.model.User;
import com.ideabobo.model.Yzmessage;
import com.ideabobo.service.BaseService;
import com.ideabobo.service.PostsService;
import com.ideabobo.service.ReplayService;
import com.ideabobo.service.UserService;
import com.ideabobo.util.GetNowTime;
import com.ideabobo.util.HttpClientTools;
import com.ideabobo.util.IdeaAction;

@Controller
public class WehallAction extends IdeaAction {
	@Resource
    private BaseService baseService;

    @Resource
    private UserService userService;
    @Resource
    private PostsService postsService;
    @Resource
    private ReplayService replayService;
    public Gson gson = new Gson();
    private static final long serialVersionUID = -3218238026025256103L;

    public String wehall(){
//		String openid = request.getParameter("openid");
//		session.put("openid", openid);
        return SUCCESS;
    }

    public void login(){
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        User user = new User();
        user.setPasswd(passwd);
        user.setUsername(encodeGet(username));
        User r = userService.find(user);
        if(r!=null){
            renderJsonpObj(r);
        }else{
            renderJsonpString("fail");
        }
    }
    
    public void resetPasswd(){
    	String username = request.getParameter("username");
    	username = encodeGet(username);
    	List<User> users = baseService.list("from User t where t.username='"+username+"'");
    	if(users!=null){
    		User user = users.get(0);
    		user.setPasswd(request.getParameter("passwd"));
    		baseService.update(user);
    		renderJsonpObj(user);
    	}else{
    		renderJsonpString("");
    	}
    	
    }

    public void checkUser(){
        User u = new User();
        String username = request.getParameter("username");
        u.setUsername(username);
        User r = userService.find(u);
        if(r!=null){
            renderJsonpString("fail");
        }else{
            renderJsonpString("success");
        }
    }

    public void updateUser(){
        String tel = request.getParameter("tel");
        String qq = request.getParameter("qq");
        String wechat = request.getParameter("wechat");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String fname = request.getParameter("fname");
        String yuanxi = request.getParameter("yuanxi");
        String zhuanye = request.getParameter("zhuanye");
        String nianji = request.getParameter("nianji");
        String xuehao = request.getParameter("xuehao");
        String id = request.getParameter("id");

        User user = userService.find(id);

        user.setId(Integer.parseInt(id));
        user.setTel(tel);
        user.setWechat(wechat);
        user.setQq(qq);
        user.setEmail(email);
        user.setBirth(birth);
        user.setSex(encodeGet(sex));
        user.setFname(encodeGet(fname));
        user.setYuanxi(encodeGet(yuanxi));
        user.setXuehao(xuehao);
        
        user.setNianji(encodeGet(nianji));
        user.setZhuanye(encodeGet(zhuanye));

        userService.update(user);
        renderJsonpString("success");
    }

    public void changePasswd(){
        String passwd = request.getParameter("passwd");
        String id = request.getParameter("id");
        User user = userService.find(id);
        user.setPasswd(passwd);
        userService.update(user);
        renderJsonpString("success");
    }

    public void register(){
        User user = (User) getByRequest(new User(), true);

        userService.save(user);

        renderJsonpString("success");
    }

    
    
    public void getUser(){
    	String id = request.getParameter("id");
    	User u = (User) baseService.find(Integer.parseInt(id), User.class);
    	renderJsonpObj(u);
    }

   

    
    public void addPosts(){
    	String uid = request.getParameter("uid");
    	String title = encodeGet(request.getParameter("title"));
    	String note = encodeGet(request.getParameter("note"));
    	String username = encodeGet(request.getParameter("username"));
    	String img = request.getParameter("img");
    	String ndate = GetNowTime.getNowTimeEn();
    	Posts p = new Posts();
    	p.setUid(uid);
    	p.setTitle(title);
    	p.setUsername(username);
    	p.setNote(note);
    	p.setNdate(ndate);
    	p.setImg(img);
    	postsService.save(p);
    	renderJsonpString("success");
    }
    public void listPosts(){
        renderJsonpObj(postsService.list());
    }
    public void listReplay(){
    	String pid = request.getParameter("pid");
    	Replay r = new Replay();
    	r.setPid(pid);
        renderJsonpObj(replayService.list(r));
    }
    public void deletePosts(){
    	String id = request.getParameter("id");
    	postsService.delete(Integer.parseInt(id));    	
    	renderJsonpString("success");
    }
    public void addReplay(){
    	String uid = request.getParameter("uid");
    	String pid = request.getParameter("pid");
    	String note = encodeGet(request.getParameter("note"));
    	String username = encodeGet(request.getParameter("username"));
    	String ndate = GetNowTime.getNowTimeEn();
    	Replay m = new Replay();
    	m.setUid(uid);
    	m.setPid(pid);
    	m.setUsername(username);
    	m.setNote(note);
    	m.setNdate(ndate);
    	replayService.save(m);
    	renderJsonpString("success");
    }
    

    
    public void delPosts(){
    	String id = request.getParameter("id");
    	baseService.delete(Integer.parseInt(id), Posts.class);
    	renderJsonpString("success");
    }
    
    public void listQunzu(){
    	String hql = "from Qunzu t where 1=1";
    	String type = request.getParameter("type");
    	if(type!=null && !type.equals("")){
    		type = encodeGet(type);
    		hql+=" and t.type like '%"+type+"%'";
    	}
        renderJsonpObj(baseService.list(hql));
    }
    
    
    
    public void listMyQunzu(){
    	String uid = request.getParameter("uid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	if(u.getQids()==null || u.getQids().equals("")){
    		renderJsonpObj(null);
    	}else{
    		List<Qunzu> list = baseService.list("from Qunzu t where t.id in ("+u.getQids()+")");
        	renderJsonpObj(list);
    	}
    	
    }
    
    public void checkIsMyQunzu(){
    	String uid = request.getParameter("uid");
    	String qid = request.getParameter("qid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	String qids = u.getQids();
    	String info = "0";
    	if(qids!=null && !qids.equals("")){
    		String[] qidsarray = qids.split(",");
        	
        	for(int i=0;i<qidsarray.length;i++){
        		if(qidsarray[i].equals(qid)){
        			info = "1";
        			break;
        		}
        	}
        	renderJsonpString(info);
    	}else{
    		renderJsonpString(info);
    	}
    	
    }
    
   
    
    public void saveQunzu(){
    	Qunzu info = (Qunzu) getByRequest(new Qunzu(), true);
    	baseService.save(info);
        renderJsonpObj(info);
    }
    
    public void delQunzu(){
    	String id = request.getParameter("id");
    	baseService.delete(Integer.parseInt(id), Qunzu.class);
    	renderJsonpString("success");
    }
    
    
    
    
    
    public void addQunzu(){
    	String uid = request.getParameter("uid");
    	String qid = request.getParameter("qid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	String qids = u.getQids();
    	if(qids==null||qids.equals("")){
    		qids = qid;
    	}else{
    		qids+=","+qid;
    	}
    	u.setQids(qids);
    	baseService.update(u);
    	renderJsonpObj(u);
    }
 //退出群   
    public void delQunzus(){
    	String uid = request.getParameter("uid");
    	String qid = request.getParameter("qid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	String qids = u.getQids();
    	String[] qidsarray = qids.split(",");
    	String nqids = "";
    	for(int i=0;i<qidsarray.length;i++){
    		String oid = qidsarray[i];
    		if(oid.equals(qid)){
    			continue;
    		}else{
    			if(nqids.equals("")){
    				nqids = oid;
    			}else{
    				nqids+=","+oid;
    			}
    		}
    	}
    	
    	u.setQids(nqids);
    	baseService.update(u);
    	renderJsonpObj(u);
    }
    
    /*******************************************好友管理*******************************/
    
    
    
    public void listMyFriend(){
    	String uid = request.getParameter("uid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	if(u.getFids()==null || u.getFids().equals("")){
    		renderJsonpObj(null);
    	}else{
    		List<User> list = baseService.list("from User t where t.id in ("+u.getFids()+")");
        	renderJsonpObj(list);
    	}
    	
    }

    public void listUser(){
    	renderJsonpObj(baseService.list("from User"));
    }
    
    public void checkIsMyFriend(){
    	String uid = request.getParameter("uid");
    	String fid = request.getParameter("fid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	String fids = u.getFids();
    	String info = "0";
    	if(fids!=null && !fids.equals("")){
    		String[] fidsarray = fids.split(",");
    		for(int i=0;i<fidsarray.length;i++){
        		if(fidsarray[i].equals(fid)){
        			info = "1";
        			break;
        		}
        	}
    	}
    	
    	renderJsonpString(info);
    }
    
    public void addFriend(){
    	String uid = request.getParameter("uid");
    	String fid = request.getParameter("fid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	String fids = u.getFids();
    	if(fids==null||fids.equals("")){
    		fids = fid;
    	}else{
    		fids+=","+fid;
    	}
    	u.setFids(fids);
    	baseService.update(u);
    	renderJsonpObj(u);
    }
    
    
    public void delFriend(){
    	String uid = request.getParameter("uid");
    	String fid = request.getParameter("fid");
    	User u = (User) baseService.find(Integer.parseInt(uid), User.class);
    	String fids = u.getFids();
    	String[] fidsarray = fids.split(",");
    	String nfids = "";
    	for(int i=0;i<fidsarray.length;i++){
    		String oid = fidsarray[i];
    		if(oid.equals(fid)){
    			continue;
    		}else{
    			if(nfids.equals("")){
    				nfids = oid;
    			}else{
    				nfids+=","+oid;
    			}
    		}
    	}
    	
    	u.setFids(nfids);
    	baseService.update(u);
    	renderJsonpObj(u);
    }
    
    public void sendAddMessage(){
    	Yzmessage msg = (Yzmessage) getByRequest(new Yzmessage(), true);
    	msg.setNdate(GetNowTime.getNowTimeEn());
    	baseService.save(msg);
    	renderJsonpString("0");
    }
    
    public void listMyAddMessage(){
 	   String uid = request.getParameter("uid");
 	   List<Message> mlist = baseService.list("from Yzmessage t where t.tid = "+uid);
 	   renderJsonpObj(mlist);
    }
    
    public void updateYzMsgStatus(){
    	String id = request.getParameter("id");
    	String status = request.getParameter("status");
    	status = encodeGet(status);
    	Yzmessage msg = (Yzmessage) baseService.find(Integer.parseInt(id), Yzmessage.class);
    	msg.setStatus(status);
    	baseService.update(msg);
    	renderJsonpString("0");
    }
    
    
    /*******************************************好友管理*******************************/
    
    public void addMessage(){
    	Message msg = (Message) getByRequest(new Message(), true);
        String ndate = GetNowTime.getNowTimeEn();
        msg.setNdate(ndate);
        baseService.save(msg);
        renderJsonpObj(msg);
    }
    
    public void addQunMessage(){
    	Message msg = (Message) getByRequest(new Message(), true);
        String ndate = GetNowTime.getNowTimeEn();
        msg.setNdate(ndate);
        msg.setZan(0);
        baseService.save(msg);
        renderJsonpObj(msg);
    }
    
    public void listQunMessage(){
    	String uid = request.getParameter("uid");
    	String qid = request.getParameter("qid");
    	List<Message> list = null;
    	if(uid!=null){
    		int id = Integer.parseInt(uid);
    		int iqid = Integer.parseInt(qid);
    		list = listAboutMeQun(id,iqid);
    	}
    	renderJsonpObj(list);
    }
    
    
    public List<Message> listAboutMeQun(int uid,int fid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from Message u where 1=1");
		sb.append(" and u.qid="+fid);
	
		System.out.println(sb.toString());
		List teaList = baseService.list(sb.toString());
		return teaList;
	}
    
    public void listMyMessage(){
    	String uid = request.getParameter("uid");
    	String fid = request.getParameter("fid");
    	List<Message> list = null;
    	if(uid!=null){
    		int id = Integer.parseInt(uid);
    		int fiid = Integer.parseInt(fid);
    		list = listAboutMe(id,fiid);
    	}
    	renderJsonpObj(list);
    }
    
    public List<Message> listAboutMe(int uid,int fid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from Message u where 1=1");
		sb.append(" and (u.uid="+uid+" or u.fid="+uid+") and (u.uid="+fid+" or u.fid="+fid+")");
	
		System.out.println(sb.toString());
		List teaList = baseService.list(sb.toString());
		return teaList;
	}
    
    public void zan(){
    	String id = request.getParameter("id");
    	Message msg = (Message) baseService.find(Integer.parseInt(id), Message.class);
    	if(msg!=null){
    		Integer zan = msg.getZan();
    		if(zan==null){
    			zan = 0;
    		}
    		zan++;
    		msg.setZan(zan);
    		baseService.update(msg);
    		renderJsonpString(zan+"");
    	}
    	renderJsonpString("0");
    }
    
    public void getTypes(){
    	renderJsonpObj(baseService.list("from Type"));
    }
    
    public void getWeather(){
    	try {
    		String city = request.getParameter("city");
    		city = encodeGet(city);
			city = URLEncoder.encode(city,"utf-8");
			String url = "http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=W6eQXHTZjNf7QTG9k9Mpboez";		
			String r = HttpClientTools.get(url);
		
			renderJsonpString(r);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
