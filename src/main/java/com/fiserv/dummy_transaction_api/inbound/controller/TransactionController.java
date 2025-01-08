package com.fiserv.dummy_transaction_api.inbound.controller;

import com.fiserv.dummy_transaction_api.core.domain.TransactionTO;
import com.fiserv.dummy_transaction_api.core.ports.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/findbydate")
    public ResponseEntity<List<TransactionTO>> getAllTransactionByDate(@RequestParam(name = "date") String date) {
        try {
            return ResponseEntity.ok(service.getAllByDate(date));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/findbystore")
    public ResponseEntity<List<TransactionTO>> getAllTransactionByStore(@RequestParam(name = "store") String store) {
        try {
            return ResponseEntity.ok(service.getAllByStore(store));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
