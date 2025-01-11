package com.fiserv.dummy_transaction_api.outbound.database.user;

import com.fiserv.dummy_transaction_api.core.ports.user.IUserRepository;
import com.fiserv.dummy_transaction_api.core.application.user.UserSQL;
import com.fiserv.dummy_transaction_api.core.application.user.UserTO;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcTemplateRepository implements IUserRepository {

	private final NamedParameterJdbcTemplate namedParametersJdbcTemplate;
	private final UserSQL userSQL;

	public UserJdbcTemplateRepository(NamedParameterJdbcTemplate namedParametersJdbcTemplate, UserSQL userSQL) {
		this.namedParametersJdbcTemplate = namedParametersJdbcTemplate;
		this.userSQL = userSQL;
	}

	@Override
	public UserTO findUserByLogin(String login) {
		SqlQuery query = userSQL.getUserByLogin(login);

		return namedParametersJdbcTemplate.queryForObject(query.sql, query.params, (rs, numRow) -> {
			String userLogin = rs.getString("login");
			String seClient = rs.getString("se_cliente");

			return new UserTO(userLogin, seClient);
		});
	}
}
