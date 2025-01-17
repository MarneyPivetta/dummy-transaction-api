package com.fiserv.dummy_transaction_api.adapters.inbound.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fiserv.dummy_transaction_api.core.domain.TransactionDTO;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionListResponse {

	private Integer page;
	private Integer status;
	private String timestamp;
	private List<TransactionDTO> transactions;

}
