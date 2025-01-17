package com.fiserv.dummy_transaction_api.core.application;

import com.fiserv.dummy_transaction_api.core.domain.UserDTO;
import com.fiserv.dummy_transaction_api.core.ports.user.IUserRepository;
import com.fiserv.dummy_transaction_api.core.ports.user.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDTO getUser(String login) {
		return userRepository.findUserByLogin(login);
	}
}
