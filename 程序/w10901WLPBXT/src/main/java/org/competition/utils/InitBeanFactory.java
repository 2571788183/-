package org.competition.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitBeanFactory extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// 修改log4j.properties的存储路径
		String webInfPath = this.getServletContext().getRealPath("/WEB-INF");
		String webRootPath = this.getServletContext().getRealPath("/log.log");

		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(webInfPath+ "/classes/log4j.properties"));
			pro.load(in);
			// 重新写入配置文件
			FileOutputStream file = new FileOutputStream(webInfPath+ "/classes/log4j.properties");

			webRootPath=webRootPath.replace("\\:", ":");
			pro.put("log4j.appender.loginAppender.File", webRootPath);

			System.out.println("得到属性key:"+ pro.getProperty("log4j.appender.loginAppender.File"));
			pro.store(file, "系统配置修改"); // 这句话表示重新写入配置文件
			
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(webInfPath+ "/classes/log4j.properties")));
			String temp=null;
			StringBuffer sb=new StringBuffer();
			temp=br.readLine();
			while(temp!=null){
				if(temp.startsWith("#") || temp.equals("")){// 忽略#开头的注释  
				} else{
					temp = temp.replace("\\:", ":");
				} 
				sb.append(temp+"\r\n");
				temp=br.readLine();
			}
			FileOutputStream out = new FileOutputStream(webInfPath+ "/classes/log4j.properties");
			out.write(sb.toString().getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
