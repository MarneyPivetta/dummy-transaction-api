package com.fiserv.dummy_transaction_api.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionFilterDTO {

	private String date;
	private String startTime;
	private String endTime;
	private String posNumber;
	private String sitefStoreCode;
	private int page;
	private int itemsPerPage;

	@JsonIgnore
	private FilterDateType dateType;
	@JsonIgnore
	private String seClient;

}
