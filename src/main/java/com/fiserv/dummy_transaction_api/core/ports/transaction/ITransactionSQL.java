package com.fiserv.dummy_transaction_api.core.ports.transaction;

import com.fiserv.dummy_transaction_api.core.application.user.UserTO;
import com.fiserv.dummy_transaction_api.util.sql.SqlQuery;

public interface ITransactionSQL {

	SqlQuery getAllByDate(String date, UserTO user);

	SqlQuery getAllByStore(String sitefStoreCode, UserTO user);

}
