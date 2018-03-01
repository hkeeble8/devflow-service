package com.henri.devflow.core.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.henri.devflow.core.resource.bean.UserBean;
import com.henri.devflow.core.service.UserDetailsService;
import com.henri.devflow.util.JsonTools;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(JsonTools.toJson(new UserBean(userDetailsService.getByEmail(authentication.getName()))));
		clearAuthenticationAttributes(request);
	}
}