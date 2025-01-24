package com.banking.transaction.service.transfer;

import com.banking.transaction.dto.TransferDTO;
import com.banking.transaction.entity.Balance;
import com.banking.transaction.entity.StatusTransfer;
import com.banking.transaction.entity.Transfer;
import com.banking.transaction.mapper.TransferMapper;
import com.banking.transaction.repository.BalanceRepository;
import com.banking.transaction.repository.StatusTransferRepository;
import com.banking.transaction.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;
    private StatusTransferRepository statusTransferRepository;
    private BalanceRepository balanceRepository;

    /**
     * Lấy danh sách tất cả các giao dịch chuyển tiền.
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `transferRepository.findAll()` để lấy danh sách tất cả các giao dịch chuyển tiền từ cơ sở dữ liệu :
     *   - Truy vấn không có điều kiện, trả về toàn bộ dữ liệu trong bảng `Transfer`.
     *   - Kết quả trả về là một danh sách `List<Transfer>`.
     * 2. Sử dụng Java Stream API để xử lý danh sách:
     * - Tạo một luồng dữ liệu từ danh sách các Transfers bằng `stream()`.
     *  - Sử dụng phương thức `map` để chuyển đổi từ `Transfer` sang `TransferDTO` bằng `TransferMapper.toDTO`.
     *  - Sử dụng phương thức `collect` để chuyển đổi luồng dữ liệu thành danh sách bằng `Collectors.toList()`.
     * 3. Trả về danh sách `List<TransferDTO>`.
     *
     * @param `không có`
     * @return Danh sách tất cả các giao dịch chuyển tiền trong cơ sở dữ liệu.
     */
    @Override
    public List<TransferDTO> getAllTransfers() {
        return transferRepository.findAll().stream()
                .map(TransferMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin một giao dịch chuyển tiền theo ID.
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `transferRepository.findById(id)` để tìm kiếm giao dịch chuyển tiền trong cơ sở dữ liệu bằng ID.
     *   - Kết quả trả về là một `Optional<Transfer>`.
     * 2. Sử dụng `orElseThrow()` để:
     *   - Trả về giao dịch chuyển tiền nếu tìm thấy.
     *   - Ném ngoại lệ `NoSuchElementException` nếu không tìm thấy giao dịch chuyển tiền.
     *
     * @param id ID của giao dịch chuyển tiền cần tìm.
     * @return `TransferDTO` Đối tượng giao dịch chuyển tiền tìm thấy.
     * @throws ``NoSuchElementException`` nếu không tìm thấy giao dịch chuyển tiền với ID đã cho.
     */
    @Override
    public TransferDTO getTransferById(Integer id) {
        return transferRepository.findById(id)
                .map(TransferMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Transfer not found with id " + id));
    }

    /**
     * Tạo một giao dịch chuyển tiền mới.
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra `transferDTO`:
     *  - Nếu `transferDTO.getId()` là null, gọi `getGenerationId()` để tạo ID mới.
     * 2. Chuyển đổi `transferDTO` sang `Transfer` bằng `TransferMapper.toEntity`.
     * 3. Lưu giao dịch chuyển tiền mới vào cơ sở dữ liệu bằng `transferRepository.save`.
     * 4. Chuyển đổi từ `Transfer` sang `TransferDTO` bằng `TransferMapper.toDTO`.
     *
     * @param `transferDTO` Thông tin giao dịch chuyển tiền cần tạo.
     * @return `TransferDTO` Đối tượng giao dịch chuyển tiền đã tạo.
     */
    @Override
    public TransferDTO createTransfer(TransferDTO transferDTO) {
        if(transferDTO.getId() == null) {
            transferDTO.setId(getGenerationId());
        }
        Transfer transfer  = TransferMapper.toEntity(transferDTO);
        Transfer savedTransfer = transferRepository.save(transfer);
        return TransferMapper.toDTO(savedTransfer);
    }

    /**
     * Cập nhật thông tin một giao dịch chuyển tiền.
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra `transferDTO`:
     *  - Nếu `transferDTO.getId()` là null, trả về null.
     *  - Nếu `transferDTO.getIdAccountSend()` là null, trả về null.
     *  - Nếu `transferDTO.getIdAccountReceive()` là null, trả về null.
     *  - Nếu `transferDTO.getCreateAt()` là null, trả về null.
     *  - Nếu `transferDTO.getIdStatusTransfer()` là null, trả về null.
     *  - Nếu `transferDTO.getBalance()` là null, trả về null.
     * 2. Kiểm tra xem giao dịch chuyển tiền có tồn tại không.
     * 3. Kiểm tra trạng thái giao dịch có tồn tại không.
     * 4. Kiểm tra tài khoản gửi có tồn tại không.
     * 5. Kiểm tra xem số dư có được truyền vào không.
     *  - Kiểm tra số dư của tài khoản gửi có đủ không.
     *  - Cập nhật số dư tài khoản gửi.
     * 6. Kiểm tra tài khoản nhận có tồn tại không.
     *  - Cập nhật số dư tài khoản nhận.
     * 7. Cập nhật thông tin giao dịch chuyển tiền.
     * 8. Lưu thông tin giao dịch chuyển tiền đã cập nhật vào cơ sở dữ liệu bằng `transferRepository.save`.
     * 9. Chuyển đổi từ `Transfer` sang `TransferDTO` bằng `TransferMapper.toDTO`.
     *
     * @param `transferDTO` Thông tin giao dịch chuyển tiền cần cập nhật.
     * @return `TransferDTO` Đối tượng giao dịch chuyển tiền đã cập nhật.
     */
    @Override
    public TransferDTO updateTransfer(TransferDTO transferDTO) {
        if(transferDTO.getId() == null) {
            return null;
        }
        if(transferDTO.getIdAccountSend() == null) {
            return null;
        }
        if(transferDTO.getIdAccountReceive() == null) {
            return null;
        }
        if(transferDTO.getCreateAt() == null) {
            return null;
        }
        if(transferDTO.getIdStatusTransfer() == null) {
            return null;
        }
        if(transferDTO.getBalance() == null) {
            return null;
        }

        // Kiểm tra xem giao dịch chuyển tiền có tồn tại không
        Transfer transfer = transferRepository.findById(transferDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Transfer not found with id " + transferDTO.getId()));

        // Kiểm tra trạng thái giao dịch có tồn tại không
        if(transferDTO.getIdStatusTransfer() != null) {
            StatusTransfer statusTransfer = statusTransferRepository.findById(transferDTO.getIdStatusTransfer())
                    .orElseThrow(() -> new NoSuchElementException("Status Transfer not found with id " + transferDTO.getIdStatusTransfer()));

        }

        // Kiểm tra tài khoản gửi có tồn tại không
        if (transferDTO.getIdAccountSend() != null) {
            Balance balanceSender = balanceRepository.findByAccountId(transferDTO.getIdAccountSend())
                    .orElseThrow(() -> new NoSuchElementException("Sender account not found with id " + transferDTO.getIdAccountSend()));

            // Kiểm tra xem số dư có được truyền vào không
            if (transferDTO.getBalance() != null) {
                // Kiểm tra số dư của tài khoản gửi có đủ không
                if (balanceSender.getBalance().compareTo(transferDTO.getBalance()) < 0) {
                    return null;
                }

                // Cập nhật số dư tài khoản gửi
                balanceSender.setBalance(balanceSender.getBalance().subtract(transferDTO.getBalance())); // Trừ số dư
                balanceRepository.save(balanceSender);
            }
        }

        // Kiểm tra tài khoản nhận có tồn tại không
        if (transferDTO.getIdAccountReceive() != null) {
            Balance balanceReceiver = balanceRepository.findByAccountId(transferDTO.getIdAccountReceive())
                    .orElseThrow(() -> new NoSuchElementException("Receiver account not found with id " + transferDTO.getIdAccountReceive()));

            // Kiểm tra xem số dư có được truyền vào không
            if (transferDTO.getBalance() != null) {
                // Cập nhật số dư tài khoản nhận
                balanceReceiver.setBalance(balanceReceiver.getBalance().add(transferDTO.getBalance())); // Cộng số dư
                balanceRepository.save(balanceReceiver);
            }
        }

        // Cập nhật thông tin giao dịch chuyển tiền
        transfer.setCreateAt(transferDTO.getCreateAt());
        transfer.setBalance(transferDTO.getBalance());

        Transfer updatedTransfer = transferRepository.save(transfer);

        return TransferMapper.toDTO(updatedTransfer);
    }

    /**
     * Xóa một giao dịch chuyển tiền theo ID.
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `transferRepository.deleteById(id)` để xóa giao dịch chuyển tiền trong cơ sở dữ liệu bằng ID.
     *
     * @param id ID của giao dịch chuyển tiền cần xóa.
     */
    @Override
    public void deleteTransfer(Integer id) {
        transferRepository.deleteById(id);
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
