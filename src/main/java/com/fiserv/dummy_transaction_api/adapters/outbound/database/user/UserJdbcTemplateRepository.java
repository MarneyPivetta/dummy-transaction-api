package com.fiserv.dummy_transaction_api.adapters.outbound.database.user;

import com.fiserv.dummy_transaction_api.core.ports.user.IUserRepository;
import com.fiserv.dummy_transaction_api.core.domain.UserDTO;
import com.fiserv.dummy_transaction_api.basic.sql.SqlQuery;
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
	public UserDTO findUserByLogin(String login) {
		SqlQuery query = userSQL.getUserByLogin(login);

		return namedParametersJdbcTemplate.queryForObject(query.sql, query.params, (rs, numRow) -> {
			String userLogin = rs.getString("login");
			String seClient = rs.getString("se_cliente");

			return new UserDTO(userLogin, seClient);
		});
	}
}
