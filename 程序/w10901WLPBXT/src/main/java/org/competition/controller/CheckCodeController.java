package org.competition.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.competition.model.User;
import org.competition.service.UserService;
import org.competition.utils.PageContext;
import org.springframework.context.annotation.Scope;
import org.competition.model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/checkCode","/checkCode"})
public class CheckCodeController extends BaseController{
	@ResponseBody
	@RequestMapping("/list")
	public String execute(){
		response.setContentType("image/jpeg");
		final int width = 40;
		final int height = 20;
		// 创建一张图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		// 背景
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 画1000个点
		Random r = new Random();
		for (int i = 0; i < 250 * (width / 75); i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.fillOval(r.nextInt(width), r.nextInt(height), 2, 2);
		}
		// 画随机旋转，随机选取的字符串
		String charSet = "abcdefghijklmnopqrstuvwxyz0123456789";
		String code = "";
		g.setFont(new Font("Courier", Font.BOLD, width / 3));
		double theta = 0;// 旋转角度
		for (int i = 0; i < 4; i++) {
			// 选取字符串
			String c = "" + charSet.charAt(r.nextInt(charSet.length()));
			code += c;
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			// 字符旋转
			g.rotate(theta, width / 2 + (i - 3) * width / 4 + width / 8,
					height / 2);
			theta = r.nextInt((int) (Math.PI * 25)) / 100.0 - Math.PI / 8.0;
			g.rotate(theta, width / 2 + (i - 2) * width / 4 + width / 8,
					height / 2);
			theta = -theta;

			g.drawString(c, width / 2 + (i - 2) * width / 4, height / 2
					+ height / 4);
		}

		request.getSession().setAttribute("check_code", code);

		try {
			OutputStream out = response.getOutputStream();
			// 将图片转换为JPEG类型
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.flush();
			out.close();
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
