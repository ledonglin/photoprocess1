package com.dj.dy.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.dy.ffmepg.DJShow;

@Controller
@RequestMapping("/")
public class PhotoProcess {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	public static final String TOMACT_PATH = "D:\\apache-tomcat-web\\webapps\\photoprocess\\Video";
	public static String originalImagePath = "D:\\Video\\pic";
	public static String imagePath = "D:\\Video\\pict";
	public static String pcmPath = "D:\\Video\\pcm";
	public static String soundPath = "D:\\Video\\sound";
	public static String singleVideoPath = "D:\\Video\\single";
	public static String mergeVideoPath = TOMACT_PATH + "\\merge";
	public static String ffmpegPath = "C:\\ffmpeg\\ffmpeg-20141225-git-1515bfb-win64-shared\\bin";
	
	
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message",
				"Maven Web Project + Spring 3 MVC - welcome()");

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";

	}
	
	@RequestMapping(value = "process", method = RequestMethod.POST)
	public @ResponseBody String processImg(HttpServletRequest request, HttpServletResponse response) throws IOException{

		String text0 = (String) request.getParameter("text0");
		String text1 = (String) request.getParameter("text1");
		String text2 = (String) request.getParameter("text2");
		String id = UUID.randomUUID().toString();
		String speechText[] = new String[]{text0, text1, text2};

		try {
			DJShow.convert(id, originalImagePath, imagePath, pcmPath, soundPath, singleVideoPath, mergeVideoPath, ffmpegPath, speechText);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}		
		
		String outputFileURL = "Video/merge/" + id + "/" + id + ".mp4";

		return outputFileURL;

	}
}
