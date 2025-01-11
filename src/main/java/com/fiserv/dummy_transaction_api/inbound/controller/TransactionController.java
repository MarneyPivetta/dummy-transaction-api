package com.fiserv.dummy_transaction_api.inbound.controller;

import com.fiserv.dummy_transaction_api.core.application.transaction.TransactionTO;
import com.fiserv.dummy_transaction_api.core.ports.transaction.ITransactionService;
import com.fiserv.dummy_transaction_api.core.application.user.UserTO;
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
            List<TransactionTO> transactions = service.getAllByDate(date, authUser);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/findbystore")
    public ResponseEntity<List<TransactionTO>> getAllTransactionByStore(@RequestAttribute(name = "authUser") UserTO authUser,
                                                                        @RequestParam(name = "store") String store) {
        try {
            List<TransactionTO> transactions = service.getAllByStore(store, authUser);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
