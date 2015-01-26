package com.vincent.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vincent.common.Util;
import com.vincent.demo.controller.vo.JsonResponse;
import com.vincent.demo.controller.vo.UserVo;
import com.vincent.demo.entity.User;
import com.vincent.demo.entity.UserInfo;
import com.vincent.demo.service.UserInfoService;
import com.vincent.demo.service.UserService;
import com.vincent.demo.util.PaginationModel;
import com.vincent.demo.util.ServerUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> list(){
		JsonResponse<?> jr = null;
		List<User> list = userService.listAll();
		List l = new ArrayList<User>();
        for(User user:list){
        	UserVo vo = new UserVo(user);
        	l.add(vo);
        }
        PaginationModel<UserVo> pData = new PaginationModel<UserVo>();
        pData.setData(l);
        pData.setRecordTotal(l.size());
        pData.setPageSize(10);
        pData.setPageIndex(1);
		jr = ServerUtil.buildSuccessResponse(pData, null);
		return jr;
	}
	
	@RequestMapping(value = "/getUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> getUserInfo(String userid){
		JsonResponse<?> jr = null;
        UserInfo userInfo = userInfoService.findById(userid);
        UserVo vo = new UserVo(userInfo);
		jr = ServerUtil.buildSuccessResponse(vo, null);
		return jr;
	}
	
	@RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> save(HttpServletRequest request, HttpServletResponse resp,UserVo vo){
		JsonResponse<?> jr = null;
		UserInfo userInfo= userInfoService.findById(vo.getId());
		userInfo.setEmail(vo.getEmail());
		userInfo.setTelephone(vo.getTelephone());
        userInfoService.save(userInfo);
		jr = ServerUtil.buildSuccessResponse(null, null);
		return jr;
	}
	
	@RequestMapping(value = "/changePwd", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> changePwd(HttpServletRequest request, HttpServletResponse resp,String name,String newPassword,String password){
		JsonResponse<?> jr = null;
	    name = ServerUtil.getUserName();
		User user= userService.findByName(name);
		if(!user.getPassword().equalsIgnoreCase(Util.getMD5Str(password))){
			jr = ServerUtil.buildErrorResponse(null, "密码错误");
		}else{
			user.setPassword(Util.getMD5Str(newPassword));
			userService.save(user);
			jr = ServerUtil.buildSuccessResponse(null, null);
		}
		return jr;
	}
	
	

}
