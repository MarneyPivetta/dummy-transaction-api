package com.fiserv.dummy_transaction_api.adapters.inbound.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fiserv.dummy_transaction_api.basic.util.Messages;
import com.fiserv.dummy_transaction_api.basic.validation.ValidDate;
import com.fiserv.dummy_transaction_api.basic.validation.ValidTime;
import com.fiserv.dummy_transaction_api.core.domain.TransactionFilterDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionDateFilterRequest {

	@NotNull(message = Messages.DATE_PARAMETER_NOT_NULL)
	@ValidDate
	private String date;

	@ValidTime
	private String startTime;

	@ValidTime
	private String endTime;

	@Size(min = 1, max = 8)
	private String posNumber;

	@Min(1)
	private int page;

	@Min(1)
	private int itemsPerPage;

	public TransactionFilterDTO toTransactionFilter() {
		return TransactionFilterDTO.builder()
				.date(date)
				.startTime(startTime)
				.endTime(endTime)
				.posNumber(posNumber)
				.page(page)
				.itemsPerPage(itemsPerPage)
				.build();
	}
}
