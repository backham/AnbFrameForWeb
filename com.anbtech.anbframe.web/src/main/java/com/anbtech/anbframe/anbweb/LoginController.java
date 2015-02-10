package com.anbtech.anbframe.anbweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbtech.anbframe.security.CustomAuthenticationProvider;
import com.anbtech.anbframe.security.service.LoginService;
import com.anbtech.anbframe.security.service.domain.User;



@Controller
@RequestMapping(value="/security")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserMngController.class);
	
	@Autowired
	LoginService loginService;
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void login(HttpSession session){
		logger.info("Welcome login! {}", session.getId());
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(HttpSession session){
		User user = (User)session.getAttribute("loginUser");
		logger.info("Welcome logout! {}, {}", session.getId(), user.getUsername());
		session.invalidate();
	}
	
	@RequestMapping(value="/loginprocess", method=RequestMethod.POST)
	public void loginProcess(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		ModelMap map = new ModelMap();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
			username, password);

		try {
			// 로그인
			Authentication auth = customAuthenticationProvider.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			map.put("success", true);
			
		} catch (BadCredentialsException e) {
			map.put("success", false);
			map.put("message", e.getMessage());
		}

	}
	
	@RequestMapping(value = "login_duplicate", method = RequestMethod.GET)
	public void login_duplicate() {		
		logger.info("Welcome login_duplicate!");
	}
	
}
