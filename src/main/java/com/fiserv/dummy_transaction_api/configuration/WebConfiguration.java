package com.fiserv.dummy_transaction_api.configuration;

import com.fiserv.dummy_transaction_api.core.domain.UserDTO;
import com.fiserv.dummy_transaction_api.core.ports.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	private final IUserService userService;

	public WebConfiguration(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor());
	}

	static class RequestInterceptor implements HandlerInterceptor {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
			//Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getCredentials();

			//UserTO user = userService.getUser(jwt.getClaimAsString("preferred_username"));
			UserDTO user = new UserDTO("supervisor", "12345678");
			request.setAttribute("authUser", user);

			return true;
		}
	}

}
