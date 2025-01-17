package com.fiserv.dummy_transaction_api.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class CustomRequestLogging extends AbstractRequestLoggingFilter {

	@Override
	protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
		var msg = new StringBuilder();
		msg.append(prefix);
		msg.append(request.getMethod()).append(" ");
		msg.append(request.getRequestURI());

		var queryString = request.getQueryString();
		HttpSession httpSession = request.getSession(false);
		Principal userPrincipal = request.getUserPrincipal();

		Optional.ofNullable(queryString).ifPresent(requestQueryString -> msg.append('?').append(requestQueryString));
		writeClient(request, msg);

		Optional.ofNullable(httpSession).ifPresent(session -> writeSession(msg, session));
		Optional.ofNullable(userPrincipal).ifPresent(principal -> writeUser(msg, principal));

		msg.append(suffix);
		return msg.toString();
	}

	private void writeUser(StringBuilder msg, Principal userPrincipal) {
		var token = (Jwt) ((JwtAuthenticationToken) userPrincipal).getPrincipal();
		Map<String, Object> claims = token.getClaims();
		String username = Optional.ofNullable(claims.get("preferred_username")).map(Object::toString).orElse(Strings.EMPTY);
		msg.append(", user=").append(username);
	}

	private void writeSession(StringBuilder msg, HttpSession session) {
		msg.append(", session=").append(session.getId());
	}

	private void writeClient(HttpServletRequest request, StringBuilder msg) {
		HttpHeaders headers = new ServletServerHttpRequest(request).getHeaders();
		List<String> ips = headers.get("x-forwarded-for");

		if (CollectionUtils.isEmpty(ips)) {
			String client = request.getRemoteAddr();
			if (StringUtils.hasLength(client)) {
				msg.append(", client=").append(client);
			}
		} else {
			msg.append(", client=").append(ips.stream().map(Object::toString).collect(Collectors.joining(",")));
		}
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		logger.debug(message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		logger.debug(message);
	}
}
