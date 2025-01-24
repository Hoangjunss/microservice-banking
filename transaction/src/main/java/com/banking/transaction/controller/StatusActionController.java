package com.banking.transaction.controller;

import com.banking.transaction.dto.ApiResponse;
import com.banking.transaction.dto.StatusActionDTO;
import com.banking.transaction.service.statusAction.StatusActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status-action")
public class StatusActionController {

        @Autowired
        private StatusActionService statusActionService;

        /**
         * Get all status actions
         * @return ResponseEntity<ApiResponse<List<StatusActionDTO>>>
         */
        @GetMapping()
        public ResponseEntity<ApiResponse<List<StatusActionDTO>>> getAllStatusActions() {
            List<StatusActionDTO> statusActions = statusActionService.getAllStatusActions();
            ApiResponse<List<StatusActionDTO>> response = new ApiResponse<>(true, "Lấy danh sách thành công", statusActions, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Get status action by id
         * @param id
         * @return ResponseEntity<ApiResponse<StatusActionDTO>>
         */
        @GetMapping("/details")
        public ResponseEntity<ApiResponse<StatusActionDTO>> getStatusActionById(@RequestParam Integer id) {
            StatusActionDTO statusAction = statusActionService.getStatusActionById(id);
            ApiResponse<StatusActionDTO> response = new ApiResponse<>(true, "Lấy thông tin thành công", statusAction, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Create status action
         * @param statusActionDTO
         * @return ResponseEntity<ApiResponse<StatusActionDTO>>
         */
        @PostMapping()
        public ResponseEntity<ApiResponse<StatusActionDTO>> createStatusAction(@RequestBody StatusActionDTO statusActionDTO) {
            StatusActionDTO statusAction = statusActionService.createStatusAction(statusActionDTO);
            ApiResponse<StatusActionDTO> response = new ApiResponse<>(true, "Tạo mới thành công", statusAction, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Update status action
         * @param statusActionDTO
         * @return ResponseEntity<ApiResponse<StatusActionDTO>>
         */
        @PatchMapping()
        public ResponseEntity<ApiResponse<StatusActionDTO>> updateStatusAction(@RequestBody StatusActionDTO statusActionDTO) {
            StatusActionDTO statusAction = statusActionService.updateStatusAction(statusActionDTO);
            ApiResponse<StatusActionDTO> response = new ApiResponse<>(true, "Cập nhật thành công", statusAction, null);
            return ResponseEntity.ok(response);
        }

        /**
         * Delete status action
         * @param id
         * @return ResponseEntity<ApiResponse<Boolean>>
         */
        @DeleteMapping()
        public ResponseEntity<ApiResponse<Boolean>> deleteStatusAction(@RequestParam Integer id) {
            statusActionService.deleteStatusAction(id);
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa thành công", Boolean.TRUE, null);
            return ResponseEntity.ok(response);
        }
}
