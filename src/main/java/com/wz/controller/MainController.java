package com.wz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/main")
public class MainController {

	@RequestMapping(value = "toMain.do", method = RequestMethod.GET)
	public ModelAndView toMain(
			HttpServletRequest request,
			HttpServletResponse response
			) {
		System.out.println("tomain......");
		ModelAndView view = new ModelAndView();
		view.setViewName("view/main/main");
		return view;
	}
	
}
