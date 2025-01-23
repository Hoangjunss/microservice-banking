package com.banking.transaction.controller;

import com.banking.transaction.dto.ApiResponse;
import com.banking.transaction.dto.TransferDTO;
import com.banking.transaction.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

        @Autowired
        private TransferService transferService;

        @GetMapping()
        public ResponseEntity<ApiResponse<List<TransferDTO>>> getAllTransfers() {
            List<TransferDTO> transfers = transferService.getAllTransfers();
            ApiResponse<List<TransferDTO>> response = new ApiResponse<>(true, "Lấy danh sách thành công", transfers, null);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/details")
        public ResponseEntity<ApiResponse<TransferDTO>> getTransferById(@RequestParam Integer id) {
            TransferDTO transfer = transferService.getTransferById(id);
            ApiResponse<TransferDTO> response = new ApiResponse<>(true, "Lấy thông tin thành công", transfer, null);
            return ResponseEntity.ok(response);
        }

        @PostMapping()
        public ResponseEntity<ApiResponse<TransferDTO>> createTransfer(@RequestBody TransferDTO transferDTO) {
            TransferDTO transfer = transferService.createTransfer(transferDTO);
            ApiResponse<TransferDTO> response = new ApiResponse<>(true, "Tạo mới thành công", transfer, null);
            return ResponseEntity.ok(response);
        }

        @PatchMapping()
        public ResponseEntity<ApiResponse<TransferDTO>> updateTransfer(@RequestBody TransferDTO transferDTO) {
            TransferDTO transfer = transferService.updateTransfer(transferDTO);
            ApiResponse<TransferDTO> response = new ApiResponse<>(true, "Cập nhật thành công", transfer, null);
            return ResponseEntity.ok(response);
        }

        @DeleteMapping()
        public ResponseEntity<ApiResponse<Boolean>> deleteTransfer(@RequestParam Integer id) {
            transferService.deleteTransfer(id);
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa thành công", Boolean.TRUE, null);
            return ResponseEntity.ok(response);
        }
}
