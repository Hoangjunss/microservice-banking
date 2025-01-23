package com.banking.transaction.controller;

import com.banking.transaction.dto.ApiResponse;
import com.banking.transaction.dto.BalanceDTO;
import com.banking.transaction.service.balance.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<BalanceDTO>>> getAllBalances() {
        List<BalanceDTO> balances = balanceService.getAllBalances();
        ApiResponse<List<BalanceDTO>> response = new ApiResponse<>(true, "Lấy danh sách thành công", balances, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<BalanceDTO>> getBalanceById(@RequestParam Integer id) {
        BalanceDTO balance = balanceService.getBalanceById(id);
        ApiResponse<BalanceDTO> response = new ApiResponse<>(true, "Lấy thông tin thành công", balance, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<BalanceDTO>> createBalance(@RequestBody BalanceDTO balanceDTO) {
        BalanceDTO balance = balanceService.createBalance(balanceDTO);
        ApiResponse<BalanceDTO> response = new ApiResponse<>(true, "Tạo mới thành công", balance, null);
        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<ApiResponse<BalanceDTO>> updateBalance(@RequestBody BalanceDTO balanceDTO) {
        BalanceDTO balance = balanceService.updateBalance(balanceDTO);
        ApiResponse<BalanceDTO> response = new ApiResponse<>(true, "Cập nhật thành công", balance, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse<Boolean>> deleteBalance(@RequestParam Integer id) {
        balanceService.deleteBalance(id);
        ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa thành công", Boolean.TRUE, null);
        return ResponseEntity.ok(response);
    }

}
