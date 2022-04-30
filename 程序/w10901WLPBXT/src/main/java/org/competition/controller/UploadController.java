package org.competition.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/upload","/upload"})
public class UploadController extends BaseController {
	private MultipartFile[] uploadFile;
	private String[] fileName;
	private String[] contentType;

	@ResponseBody
	@RequestMapping("/list")
	public String execute() {
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		
		JSONObject json = new JSONObject();
		json.put("nums", uploadFile.length);
		JSONArray contentTypes=new JSONArray();
		JSONArray filenames=new JSONArray();
		
		for(int i=0;i<uploadFile.length;i++) {  
			String realPath = null;
			if (contentType[i] != null) {
				if (contentType[i].toLowerCase().indexOf("image") > -1) {
					realPath = application.getRealPath("/upload_image");
				} else {
					realPath = application.getRealPath("/upload_file");
				}
			}
			String ext = fileName[i].substring(fileName[i].lastIndexOf("."));
			String finalFilename = new SimpleDateFormat("yyyyMMddHHmmssSSS"+i).format(new Date()) + ext;
			try {
				uploadFile[i].transferTo(new File(realPath + "/"+ finalFilename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			contentTypes.add(contentType[i]);
			filenames.add(finalFilename);
        }  
		
		json.put("contentTypes", contentTypes);
		json.put("filenames", filenames);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(json.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@ResponseBody
	@RequestMapping("/paste")
	public String paste() {
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		
		for(int i=0;i<uploadFile.length;i++) {  
			String realPath = null;
			if (contentType[i] != null) {
				if (contentType[i].toLowerCase().indexOf("image") > -1) {
					realPath = application.getRealPath("/upload_image");
				} else {
					realPath = application.getRealPath("/upload_file");
				}
			}
			String ext = fileName[i].substring(fileName[i].lastIndexOf("."));
			String finalFilename = new SimpleDateFormat("yyyyMMddHHmmssSSS"+i).format(new Date()) + ext;
			try {
				uploadFile[i].transferTo(new File(realPath + "/"+ finalFilename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Map<String,String> msg = new HashMap<String,String>();
			msg.put("uploaded", "1");
			msg.put("fileName", finalFilename);
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			if (contentType[i] != null) {
				if (contentType[i].toLowerCase().indexOf("image") > -1) {
					msg.put("url", basePath+"\\upload_image\\"+ finalFilename);
				} else {
					msg.put("url", basePath+"\\upload_file\\"+ finalFilename);
				}
			}
			PrintWriter out;
			try {
				out = response.getWriter();
				out.write(JSONObject.toJSONString(msg));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
		
		return null;
		
	}


	@ModelAttribute
	public void getUploadFile(MultipartFile[] uploadFile,MultipartFile[] upload) throws Exception {
		this.uploadFile = uploadFile==null?upload:uploadFile;
		if(this.uploadFile!=null) {
			fileName = new String[this.uploadFile.length];
			contentType = new String[this.uploadFile.length];
			for(int i=0;i<this.uploadFile.length;i++) {
				fileName[i]=this.uploadFile[i].getOriginalFilename();
				contentType[i]=this.uploadFile[i].getContentType();
			}
		}else {
			throw new RuntimeException("无法接收文件！");
		}
	}

	
}
