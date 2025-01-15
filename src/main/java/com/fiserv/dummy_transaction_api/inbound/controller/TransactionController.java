package com.fiserv.dummy_transaction_api.inbound.controller;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionService;
import com.fiserv.dummy_transaction_api.core.domain.UserTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final ITransactionService service;

    public TransactionController(ITransactionService service) {
        this.service = service;
    }

    @GetMapping("/findbydate")
    public ResponseEntity<List<TransactionTO>> getAllTransactionByDate(@RequestAttribute(name = "authUser") UserTO authUser,
                                                                       @RequestParam(name = "date") String date) {
        try {
            long start = System.currentTimeMillis();
            List<TransactionTO> transactions = service.getAllByDate(date, authUser);
            long end = System.currentTimeMillis();
            System.out.println("[findbydate="+date+"] Transacoes encontradas: " + transactions.size() + " em " + (end - start) + "ms");
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
