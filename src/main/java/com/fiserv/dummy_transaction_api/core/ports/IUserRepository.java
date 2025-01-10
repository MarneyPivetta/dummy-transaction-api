package com.fiserv.dummy_transaction_api.core.ports;

import com.fiserv.dummy_transaction_api.core.user.UserTO;

public interface IUserRepository {

	UserTO findUserByLogin(String login);

}
