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

import com.vincent.demo.controller.vo.JsonResponse;
import com.vincent.demo.controller.vo.SysResourceVo;
import com.vincent.demo.controller.vo.UserVo;
import com.vincent.demo.entity.SysResource;
import com.vincent.demo.entity.User;
import com.vincent.demo.service.SysResourceService;
import com.vincent.demo.util.PaginationModel;
import com.vincent.demo.util.ServerUtil;

@Controller
@RequestMapping("/sysresource")
public class ResourceController {
	
	@Autowired
	private SysResourceService sysResourceService;
	
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> list(HttpServletRequest request, HttpServletResponse resp,SysResourceVo vo){
		JsonResponse<?> jr = null;
		List<SysResource> list = sysResourceService.listAll(vo.getUrl(),vo.getUrl());
		List l = new ArrayList<SysResourceVo>();
        for(SysResource resource:list){
        	SysResourceVo svo = new SysResourceVo(resource);
        	l.add(svo);
        }
        PaginationModel<UserVo> pData = new PaginationModel<UserVo>();
        pData.setData(l);
        pData.setRecordTotal(l.size());
        pData.setPageSize(10);
        pData.setPageIndex(1);
		jr = ServerUtil.buildSuccessResponse(pData, null);
		return jr;
	}
	
	@RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> get(String id){
		JsonResponse<?> jr = null;
		SysResource sysResource = sysResourceService.findById(id);
		jr = ServerUtil.buildSuccessResponse(sysResource, null);
		return jr;
	}
	
	@RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> save(HttpServletRequest request, HttpServletResponse resp,SysResource vo){
		JsonResponse<?> jr = null;
		SysResource sysResource = sysResourceService.save(vo);
		jr = ServerUtil.buildSuccessResponse(null, null);
		return jr;
	}
	
	@RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> delete(HttpServletRequest request, HttpServletResponse resp,String id){
		JsonResponse<?> jr = null;
		SysResource sysResource = sysResourceService.delete(id);
		jr = ServerUtil.buildSuccessResponse(null, null);
		return jr;
	}
	

	
	

}
