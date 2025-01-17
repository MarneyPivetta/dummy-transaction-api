package com.fiserv.dummy_transaction_api.core.ports.user;

import com.fiserv.dummy_transaction_api.core.domain.UserDTO;

public interface IUserService {

	UserDTO getUser(String login);

}
