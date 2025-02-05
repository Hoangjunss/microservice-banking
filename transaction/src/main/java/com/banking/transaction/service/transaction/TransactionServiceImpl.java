package com.banking.transaction.service.transaction;

import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.entity.*;
import com.banking.transaction.mapper.TransactionMapper;
import com.banking.transaction.repository.*;
import com.banking.transaction.service.balance.BalanceService;
import com.banking.transaction.service.statusAction.StatusActionService;
import com.banking.transaction.service.statusTransaction.StatusTransactionService;
import com.banking.transaction.service.transfer.TransferService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements  TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private StatusActionService statusActionService;
    @Autowired
    private StatusTransactionService statusTransactionService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private TransferService transferService;

    /**
     * Lấy danh sách tất cả các giao dịch (`Transaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `transactionRepository.findAll()` để truy vấn tất cả các giao dịch (`Transaction`) có trong cơ sở dữ liệu.
     *   - Truy vấn không có điều kiện, trả về toàn bộ dữ liệu trong bảng `Transaction`.
     *   - Trả về danh sách các `Transaction` từ repository.
     * 2. Sử dụng Java Stream API để xử lý danh sách:
     *   - Tạo một luồng dữ liệu từ danh sách các Transaction bằng `stream()`.
     *   - Chuyển từng đối tượng Transaction thành TransactionDTO bằng cách sử dụng `map()` và gọi `TransactionMapper.toDTO()`.
     *   - Kết quả là một luồng chứa các TransactionDTO.
     *  3. Gom các TransactionDTO từ luồng thành một danh sách mới bằng `collect(Collectors.toList())`.
     *
     * @param ``Không`` có.
     * @return Danh sách tất cả các giao dịch (`Transaction`) trong cơ sở dữ liệu.
     */
    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy giao dịch (`Transaction`) theo ID
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `transactionRepository.findById(id)` để truy vấn giao dịch (`Transaction`) theo ID.
     *   - Trả về một `Optional<Transaction>`.
     * 2. Sử dụng `map()` để chuyển đổi từ `Optional<Transaction>` sang `TransactionDTO`.
     * 3. Nếu không tìm thấy giao dịch, ném một ngoại lệ.
     *
     * @param `id` ID của giao dịch cần tìm.
     * @return `TransactionDTO` Đối tượng giao dịch tìm thấy.
     * @throws `EntityNotFoundException` nếu không tìm thấy giao dịch với ID đã cho.
     */
    @Override
    public TransactionDTO getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionMapper::toDTO)
                .orElseThrow();
    }

    /**
     * Tạo giao dịch (`Transaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Chuyển đổi từ `TransactionDTO` sang `Transaction` bằng cách sử dụng `TransactionMapper.toEntity()`.
     * 2. Gọi `transactionRepository.save(transaction)` để lưu giao dịch vào cơ sở dữ liệu.
     * 3. Chuyển đổi từ `Transaction` đã lưu sang `TransactionDTO` bằng cách sử dụng `TransactionMapper.toDTO()`.
     *
     * @param `transactionDTO` Đối tượng giao dịch cần tạo.
     * @return `TransactionDTO` Đối tượng giao dịch đã tạo.
     */
    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        if(transactionDTO.getId() == null) {
            transactionDTO.setId(getGenerationId());
        }
        Transaction transaction = TransactionMapper.toEntity(transactionDTO);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toDTO(savedTransaction);
    }

    /**
     * Cập nhật thông tin giao dịch (`Transaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra xem `transactionDTO` có `id`, `balanceId`, `createAt`, `idStatusAction`, `idStatusTransaction` hay không.
     * 2. Kiểm tra xem `statusActionRepository` có tồn tại `idStatusAction` hay không.
     * 3. Kiểm tra xem `statusTransactionRepository` có tồn tại `idStatusTransaction` hay không.
     * 4. Kiểm tra xem `transactionRepository` có tồn tại `id` hay không.
     * 5. Tìm giao dịch theo ID.
     * 6. Cập nhật thời gian tạo.
     * 7. Cập nhật trạng thái hành động.
     * 8. Cập nhật trạng thái giao dịch.
     * 9. Cập nhật số dư tài khoản liên quan đến giao dịch (nếu có) và thực hiện các bước chuyển tiền:
     *      - Nếu giao dịch có thông tin chuyển tiền (Transfer), thực hiện các bước sau:
     *          1. Lấy thông tin giao dịch chuyển tiền từ `Transfer` bằng cách tìm theo `transferId`.
     *          2. Kiểm tra và cập nhật số dư tài khoản gửi:
     *          - Lấy số dư tài khoản gửi từ cơ sở dữ liệu bằng `accountId` của tài khoản gửi.
     *          - Trừ số tiền chuyển đi (sử dụng `getBalance()` của `transfer`).
     *          - Lưu lại số dư tài khoản gửi sau khi đã trừ số tiền chuyển.
     *          3. Kiểm tra và cập nhật số dư tài khoản nhận:
     *          - Lấy số dư tài khoản nhận từ cơ sở dữ liệu bằng `accountId` của tài khoản nhận.
     *          - Cộng số tiền chuyển vào tài khoản nhận (sử dụng `getBalance()` của `transfer`).
     *          - Lưu lại số dư tài khoản nhận sau khi đã cộng số tiền chuyển vào.
     * 10. Lưu giao dịch đã cập nhật.
     * 11. Chuyển đổi từ `Transaction` đã cập nhật sang `TransactionDTO` bằng cách sử dụng `TransactionMapper.toDTO()`.
     *
     * @param `transactionDTO` Đối tượng giao dịch cần cập nhật.
     * @return `TransactionDTO` Đối tượng giao dịch đã cập nhật.
     */
    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        if(transactionDTO.getId() == null) {
            return null;
        }
        if(transactionDTO.getBalanceId() == null) {
            return null;
        }
        if(transactionDTO.getCreateAt() == null) {
            return null;
        }
        if(transactionDTO.getIdStatusAction() == null) {
            return null;
        }
        if(transactionDTO.getIdStatusTransaction() == null) {
            return null;
        }
        if(!transactionRepository.existsById(transactionDTO.getId())) {
            return null;
        }

        // Tìm giao dịch theo ID
        Transaction transaction = transactionRepository.findById(transactionDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id " + transactionDTO.getId()));

        transaction.setCreateAt(transactionDTO.getCreateAt());  // Cập nhật thời gian tạo

        // Cập nhật trạng thái hành động
        if (transactionDTO.getIdStatusAction() != null) {
            StatusAction statusAction = statusActionService.getStatusActionById(transactionDTO.getIdStatusAction());
            transaction.setStatusAction(statusAction);
        }

        // Cập nhật trạng thái giao dịch
        if (transactionDTO.getIdStatusTransaction() != null) {
            StatusTransaction statusTransaction = statusTransactionService.getStatusTransactionById(transactionDTO.getIdStatusTransaction());
            transaction.setStatusTransaction(statusTransaction);
        }

        // Cập nhật số dư
        if(transactionDTO.getBalanceId() != null) {

            Balance balance = balanceService.getBalanceById(transactionDTO.getBalanceId());

            // lấy số dư hiện tại
            BigDecimal currentBalance = balance.getBalance();

            if(transactionDTO.getTransferId() != null) {
                Transfer transfer = transferService.getTransferById(transactionDTO.getTransferId());

                Balance senderBalance = balanceService.getBalanceByAccountId(transfer.getIdAccountSend());
                senderBalance.setBalance(senderBalance.getBalance().subtract(transfer.getBalance()));
                balanceService.updateBalance(senderBalance);

                Balance receiverBalance = balanceService.getBalanceByAccountId(transfer.getIdAccountReceive());
                receiverBalance.setBalance(receiverBalance.getBalance().add(transfer.getBalance()));
                balanceService.updateBalance(receiverBalance);
            }
        }
        Transaction updatedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toDTO(updatedTransaction);
    }

    /**
     * Xóa giao dịch (`Transaction`) theo ID
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `transactionRepository.deleteById(id)` để xóa giao dịch theo ID.
     *
     * @param `id` ID của giao dịch cần xóa.
     * @return `Không` có.
     */
    @Override
    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    /**
     * Tạo ID ngẫu nhiên.
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Tạo một UUID ngẫu nhiên (Universally Unique Identifier) có độ dài 128 bit, được chia thành hai phần:
     *    - Most Significant Bits (MSB): 64 bit đầu tiên.
     *    - Least Significant Bits (LSB): 64 bit còn lại.
     * 2. Lấy 64 bit quan trọng nhất từ UUID, hay còn gọi là Most Significant Bits (MSB).
     *    - Để lấy 64 bit đầu tiên, chúng ta sử dụng phương thức `getMostSignificantBits()` từ đối tượng UUID.
     *    - Để lấy 64 bit còn lại, chúng ta sử dụng phương thức `getLeastSignificantBits()` từ đối tượng UUID.
     * 3. Dùng phép toán AND với giá trị `0xFFFFFFFFL` để giữ lại 32 bit thấp nhất từ 64 bit đầu tiên của UUID (MSB).
     * 4. Chuyển đổi kết quả từ phép toán AND thành một số nguyên (Integer) 32 bit.
     *
     * Phương pháp này đảm bảo rằng ID được tạo ra là duy nhất và nằm trong phạm vi của kiểu dữ liệu `Integer`.
     *
     * @return ID ngẫu nhiên cho số dư, là một số nguyên (Integer).
     */
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
