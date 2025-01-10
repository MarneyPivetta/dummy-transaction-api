package com.fiserv.dummy_transaction_api.core.user;

import com.fiserv.dummy_transaction_api.core.ports.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserTO getUser(String login) {
		return userRepository.findUserByLogin(login);
	}
}
