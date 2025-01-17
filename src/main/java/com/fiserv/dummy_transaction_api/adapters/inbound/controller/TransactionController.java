package com.fiserv.dummy_transaction_api.adapters.inbound.controller;

import com.fiserv.dummy_transaction_api.adapters.inbound.request.TransactionDateFilterRequest;
import com.fiserv.dummy_transaction_api.adapters.inbound.response.TransactionListResponse;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionService;
import com.fiserv.dummy_transaction_api.core.domain.UserDTO;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TransactionController {

    private final ITransactionService service;

    public TransactionController(ITransactionService service) {
        this.service = service;
    }

    @PostMapping("/searchByDate")
    public TransactionListResponse getAllTransactionByDate(@RequestAttribute(name = "authUser") UserDTO authUser,
                                                           @RequestBody @Valid TransactionDateFilterRequest request) {

        return TransactionListResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK.value())
                .page(request.getPage())
                .transactions(service.getAllByDate(request.toTransactionFilter(), authUser))
                .build();
    }

    @PostMapping("/searchByExportationDate")
    public TransactionListResponse getAllTransactionByExportationDate(@RequestAttribute(name = "authUser") UserDTO authUser,
                                                                      @RequestBody @Valid TransactionDateFilterRequest request) {

        return TransactionListResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK.value())
                .page(request.getPage())
                .transactions(service.getAllByDate(request.toTransactionFilter(), authUser))
                .build();
    }

}
