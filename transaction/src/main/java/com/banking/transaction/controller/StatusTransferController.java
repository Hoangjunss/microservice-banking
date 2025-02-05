package com.banking.transaction.controller;

import com.banking.transaction.dto.ApiResponse;
import com.banking.transaction.dto.StatusTransferDTO;
import com.banking.transaction.service.statusTransfer.StatusTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-transfer")
public class StatusTransferController {
    @Autowired
    private StatusTransferService statusTransferService;

    /**
     * Get all status transfers
     * @return ResponseEntity<ApiResponse<?>>
     */
    @GetMapping()
    public ResponseEntity<ApiResponse<List<StatusTransferDTO>>> getAllStatusTransfers() {
        List<StatusTransferDTO> statusTransfers = statusTransferService.getAllStatusTransfers();
        ApiResponse<List<StatusTransferDTO>> response = new ApiResponse<>(true, "Lấy danh sách thành công", statusTransfers, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Get status transfer by id
     * @param id
     * @return ResponseEntity<ApiResponse<?>>
     */
    @GetMapping("/details")
    public ResponseEntity<ApiResponse<StatusTransferDTO>> getStatusTransferById(@RequestParam Integer id) {
        StatusTransferDTO statusTransfer = statusTransferService.getStatusTransferDTOById(id);
        ApiResponse<StatusTransferDTO> response = new ApiResponse<>(true, "Lấy thông tin thành công", statusTransfer, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Create status transfer
     * @param statusTransferDTO
     * @return ResponseEntity<ApiResponse<StatusTransferDTO>>
     */
    @PostMapping()
    public ResponseEntity<ApiResponse<StatusTransferDTO>> createStatusTransfer(@RequestBody StatusTransferDTO statusTransferDTO) {
        StatusTransferDTO statusTransfer = statusTransferService.createStatusTransfer(statusTransferDTO);
        ApiResponse<StatusTransferDTO> response = new ApiResponse<>(true, "Tạo mới thành công", statusTransfer, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Update status transfer
     * @param statusTransferDTO
     * @return ResponseEntity<ApiResponse<StatusTransferDTO>>
     */
    @PatchMapping()
    public ResponseEntity<ApiResponse<StatusTransferDTO>> updateStatusTransfer(@RequestBody StatusTransferDTO statusTransferDTO) {
        StatusTransferDTO statusTransfer = statusTransferService.updateStatusTransfer(statusTransferDTO);
        ApiResponse<StatusTransferDTO> response = new ApiResponse<>(true, "Cập nhật thành công", statusTransfer, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete status transfer
     * @param id
     * @return ResponseEntity<ApiResponse<Boolean>>
     */
    @DeleteMapping()
    public ResponseEntity<ApiResponse<Boolean>> deleteStatusTransfer(@RequestParam Integer id) {
        statusTransferService.deleteStatusTransfer(id);
        ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa thành công", Boolean.TRUE, null);
        return ResponseEntity.ok(response);
    }

}
