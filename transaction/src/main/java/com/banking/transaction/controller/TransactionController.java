package com.banking.transaction.controller;

import com.banking.transaction.dto.ApiResponse;
import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

        @Autowired
        private TransactionService transactionService;

        @GetMapping()
        public ResponseEntity<ApiResponse<List<TransactionDTO>>> getAllTransactions() {
            List<TransactionDTO> transactions = transactionService.getAllTransactions();
            ApiResponse<List<TransactionDTO>> response = new ApiResponse<>(true, "Lấy danh sách thành công", transactions, null);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/details")
        public ResponseEntity<ApiResponse<TransactionDTO>> getTransactionById(@RequestParam Integer id) {
            TransactionDTO transaction = transactionService.getTransactionById(id);
            ApiResponse<TransactionDTO> response = new ApiResponse<>(true, "Lấy thông tin thành công", transaction, null);
            return ResponseEntity.ok(response);
        }

        @PostMapping()
        public ResponseEntity<ApiResponse<TransactionDTO>> createTransaction(@RequestBody TransactionDTO transactionDTO) {
            TransactionDTO transaction = transactionService.createTransaction(transactionDTO);
            ApiResponse<TransactionDTO> response = new ApiResponse<>(true, "Tạo mới thành công", transaction, null);
            return ResponseEntity.ok(response);
        }

        @PatchMapping()
        public ResponseEntity<ApiResponse<TransactionDTO>> updateTransaction(@RequestBody TransactionDTO transactionDTO) {
            TransactionDTO transaction = transactionService.updateTransaction(transactionDTO);
            ApiResponse<TransactionDTO> response = new ApiResponse<>(true, "Cập nhật thành công", transaction, null);
            return ResponseEntity.ok(response);
        }

        @DeleteMapping()
        public ResponseEntity<ApiResponse<Boolean>> deleteTransaction(@RequestParam Integer id) {
            transactionService.deleteTransaction(id);
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa thành công", Boolean.TRUE, null);
            return ResponseEntity.ok(response);
        }
}
