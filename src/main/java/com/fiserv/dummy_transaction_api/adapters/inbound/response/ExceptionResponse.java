package com.fiserv.dummy_transaction_api.adapters.inbound.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExceptionResponse {

	private String timestamp;
	private Integer status;
	private Object error;

}
