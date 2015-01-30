package com.vincent.demo.controller.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vincent.demo.controller.vo.JsonResponse;
import com.vincent.demo.controller.vo.UserVo;
import com.vincent.demo.entity.Document;
import com.vincent.demo.entity.User;
import com.vincent.demo.service.DocumentService;
import com.vincent.demo.util.PaginationModel;
import com.vincent.demo.util.ServerUtil;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	
	@RequestMapping(value = "/upload",  method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> list(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model){
		JsonResponse<?> jr = null;
		
		    System.out.println("开始");  
	        String path = request.getSession().getServletContext().getRealPath("upload");  
	        String fileName = file.getOriginalFilename();  
//	        String fileName = new Date().getTime()+".jpg";  
	        System.out.println(path);  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        Document doc = new Document();
	        doc.setCreateTime(new Date());
	        doc.setName(fileName);
	        doc.setPath(path);
		    documentService.createDocument(doc);
		List l = new ArrayList<User>();
        PaginationModel<UserVo> pData = new PaginationModel<UserVo>();
        pData.setData(l);
        pData.setRecordTotal(l.size());
        pData.setPageSize(10);
        pData.setPageIndex(1);
		jr = ServerUtil.buildSuccessResponse(pData, null);
		return jr;
	}
	
	@RequestMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> list(HttpServletRequest request, ModelMap model,String filename){
		JsonResponse<?> jr = null;
		List<Document> list =  documentService.listAll();
        PaginationModel<Document> pData = new PaginationModel<Document>();
        pData.setData(list);
        pData.setRecordTotal(list.size());
        pData.setPageSize(10);
        pData.setPageIndex(1);
		jr = ServerUtil.buildSuccessResponse(pData, null);
		return jr;
	}
	
	
	@RequestMapping(value = "/download",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<? extends Object> download(HttpServletRequest request,HttpServletResponse response, ModelMap model,String id) throws Exception{
		JsonResponse<?> jr = null;
		Document doc =  documentService.findById(id);
		String path = doc.getPath();
		String fileName = doc.getName();
		 request.setCharacterEncoding("UTF-8"); 
		 response.setContentType("utf-8");
		 response.setCharacterEncoding("UTF-8"); 
		    BufferedInputStream bis = null;  
		    BufferedOutputStream bos = null;  
		    
		    //获取项目根目录
		    String ctxPath = request.getSession().getServletContext()  
		        .getRealPath("");  
		    
		    //获取下载文件露肩
		    String downLoadPath = path+"/" +fileName;  
		   
		    //获取文件的长度
		    long fileLength = new File(downLoadPath).length(); 
		    fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
            //设置文件输出类型
		    response.setContentType("application/octet-stream");  
		    response.setHeader("Content-disposition", "attachment; filename=\""  
		        + fileName +"\""); 
		    //设置输出长度s
		    response.setHeader("Content-Length", String.valueOf(fileLength));  
		    //获取输入流
		    bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
		    //输出流
		    bos = new BufferedOutputStream(response.getOutputStream());  
		    byte[] buff = new byte[2048];  
		    int bytesRead;  
		    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		      bos.write(buff, 0, bytesRead);  
		    }  
		    //关闭流
		    bis.close();  
		    bos.close();  
      
		return jr;
	}
	
	

}
