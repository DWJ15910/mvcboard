package com.goodee.mvcboard.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class LoginController {
	@PostMapping("/login")
	public String login(HttpSession session,
						@RequestParam(name="memberId") String memberId,
						@RequestParam(name="memberPw") String memberPw) {
			// service(memberId,memberPw)->mapper->로그인 성공유무
		
			// 로그인 성공시
			session.setAttribute("memberId",memberId);
		return "redirect:/home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
	}
}
