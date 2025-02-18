package com.banking.transaction.controller;

import com.banking.transaction.dto.ApiResponse;
import com.banking.transaction.dto.StatusTransactionDTO;
import com.banking.transaction.service.statusTransaction.StatusTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-transaction")
public class StatusTransactionController {

        @Autowired
        private StatusTransactionService statusTransactionService;

        /**
         * Get all status transactions
         * @return ResponseEntity<ApiResponse<?>>
         */
        @GetMapping()
        public ResponseEntity<ApiResponse<List<StatusTransactionDTO>>> getAllStatusTransactions() {
            List<StatusTransactionDTO> statusTransactions = statusTransactionService.getAllStatusTransactions();
            ApiResponse<List<StatusTransactionDTO>> response = new ApiResponse<>(true, "Lấy danh sách thành công", statusTransactions, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Get status transaction by id
         * @param id
         * @return ResponseEntity<ApiResponse<?>>
         */
        @GetMapping("/details")
        public ResponseEntity<ApiResponse<StatusTransactionDTO>> getStatusTransactionById(@RequestParam Integer id) {
            StatusTransactionDTO statusTransaction = statusTransactionService.getStatusTransactionDTOById(id);
            ApiResponse<StatusTransactionDTO> response = new ApiResponse<>(true, "Lấy thông tin thành công", statusTransaction, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Create status transaction
         * @param statusTransactionDTO
         * @return ResponseEntity<ApiResponse<StatusTransactionDTO>>
         */
        @PostMapping()
        public ResponseEntity<ApiResponse<StatusTransactionDTO>> createStatusTransaction(@RequestBody StatusTransactionDTO statusTransactionDTO) {
            StatusTransactionDTO statusTransaction = statusTransactionService.createStatusTransaction(statusTransactionDTO);
            ApiResponse<StatusTransactionDTO> response = new ApiResponse<>(true, "Tạo mới thành công", statusTransaction, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Update status transaction
         * @param statusTransactionDTO
         * @return ResponseEntity<ApiResponse<StatusTransactionDTO>>
         */
        @PatchMapping()
        public ResponseEntity<ApiResponse<StatusTransactionDTO>> updateStatusTransaction(@RequestBody StatusTransactionDTO statusTransactionDTO) {
            StatusTransactionDTO statusTransaction = statusTransactionService.updateStatusTransaction(statusTransactionDTO);
            ApiResponse<StatusTransactionDTO> response = new ApiResponse<>(true, "Cập nhật thành công", statusTransaction, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Delete status transaction
         * @param id
         * @return ResponseEntity<ApiResponse<Boolean>>
         */
        @DeleteMapping()
        public ResponseEntity<ApiResponse<Boolean>> deleteStatusTransaction(@RequestParam Integer id) {
            statusTransactionService.deleteStatusTransaction(id);
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa thành công", Boolean.TRUE, null);
            return ResponseEntity.ok(response);
        }
}
