package com.fiserv.dummy_transaction_api.configuration;

import com.fiserv.dummy_transaction_api.core.user.UserService;
import com.fiserv.dummy_transaction_api.core.user.UserTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	private final UserService userService;

	public WebConfiguration(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor());
	}

	class RequestInterceptor implements HandlerInterceptor {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
			Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getCredentials();

			UserTO user = userService.getUser(jwt.getClaimAsString("preferred_username"));
			request.setAttribute("authUser", user);

			return true;
		}
	}

}