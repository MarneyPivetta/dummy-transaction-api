package com.fiserv.dummy_transaction_api.core.ports.user;

import com.fiserv.dummy_transaction_api.core.domain.UserTO;

public interface IUserService {

	UserTO getUser(String login);

}
