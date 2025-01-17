package com.fiserv.dummy_transaction_api.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 5698726765918161543L;

	private String login;
	private String seClient;

	public UserDTO(String login, String seClient) {
		this.login = login;
		this.seClient = seClient;
	}

	public String getLogin() {
		return login;
	}

	public String getSeClient() {
		return seClient;
	}
}
