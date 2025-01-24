package com.banking.transaction.service.balance;

import com.banking.transaction.dto.BalanceDTO;
import com.banking.transaction.entity.Balance;
import com.banking.transaction.mapper.BalanceMapper;
import com.banking.transaction.repository.BalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BalanceServiceImpl implements  BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;
    @Override

    /**
     * Lấy danh sách tất cả các số dư (`Banlance`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `balanceRepository.findAll()` để truy vấn tất cả các số dư (`Balance`) có trong cơ sở dữ liệu.
     *   - Truy vấn không có điều kiện, trả về toàn bộ dữ liệu trong bảng `Balance`.
     *   - Trả về danh sách các `Balance` từ repository.
     * 2. Sử dụng Java Stream API để xử lý danh sách:
     *   - Tạo một luồng dữ liệu từ danh sách các Balance bằng `stream()`.
     *   - Chuyển từng đối tượng Balance thành BalanceDTO bằng cách sử dụng `map()` và gọi `BalanceMapper.toDTO()`.
     *   - Kết quả là một luồng chứa các BalanceDTO.
     *  3. Gom các BalanceDTO từ luồng thành một danh sách mới bằng `collect(Collectors.toList())`.
     *
     * @param ``Không`` có.
     * @return Danh sách tất cả các số dư (`Balance`) trong cơ sở dữ liệu.
     */
    public List<BalanceDTO> getAllBalances() {
        return balanceRepository.findAll().stream()
                .map(BalanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy số dư (`Banlance`) theo ID
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `balanceRepository.findById(id)` để truy vấn số dư (`Balance`) theo ID.
     *   - Trả về một `Optional<Balance>`.
     * 2. Sử dụng `map()` để chuyển đổi từ `Optional<Balance>` sang `BalanceDTO`.
     * 3. Nếu không tìm thấy số dư, ném một ngoại lệ.
     *
     * @param id ID của số dư cần tìm.
     * @return `BalanceDTO` Đối tượng số dư tìm thấy.
     * @throws `EntityNotFoundException` nếu không tìm thấy số dư với ID đã cho.
     */
    @Override
    public BalanceDTO getBalanceById(Integer id) {
        return balanceRepository.findById(id)
                .map(BalanceMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Balance not found with id " + id));
    }

    /**
     * Tạo số dư (`Banlance`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra tính hợp lệ của `balanceDTO`:
     *   - Nếu `id` là null, tạo một ID mới bằng cách gọi `getGenerationId()`.
     * 2. Chuyển đổi `balanceDTO` thành `Balance` bằng cách sử dụng `BalanceMapper.toEntity(balanceDTO)`.
     * 3. Lưu đối tượng Balance vào cơ sở dữ liệu bằng `balanceRepository.save(balance)`.
     * 4. Chuyển đổi `Balance` đã lưu thành `BalanceDTO` và trả về.
     *
     * @param balanceDTO Đối tượng số dư (`BalanceDTO`) cần tạo.
     * @return `BalanceDTO` Đối tượng số dư đã tạo.
     */
    @Override
    public BalanceDTO createBalance(BalanceDTO balanceDTO) {
        if(balanceDTO.getId() == null) {
            balanceDTO.setId(getGenerationId());
        }
        Balance balance = BalanceMapper.toEntity(balanceDTO);
        Balance savedBalance = balanceRepository.save(balance);
        return BalanceMapper.toDTO(savedBalance);
    }

    /**
     * Cập nhật số dư (`Banlance`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra tính hợp lệ của `balanceDTO`:
     *   - Nếu `id`, `balance`, `accountId`, `createAt`, `status` là null, trả về null.
     *   - Nếu không tồn tại số dư với `id` đã cho, trả về null.
     * 2. Gọi `balanceRepository.findById(balanceDTO.getId())` để lấy số dư cần cập nhật :
     *  - Nếu không tìm thấy số dư, ném một ngoại lệ.
     *  - Nếu tìm thấy, trả về đối tượng Banlance.
     * 3.  Cập nhật thông tin trong đối tượng `balance` từ `balanceDTO` bằng `BalanceMapper.updateBalance(balance, balanceDTO)`.
     * 4. Lưu số dư đã cập nhật vào cơ sở dữ liệu bằng `balanceRepository.save(balance)`.
     * 5. Chuyển đổi `Balance` đã lưu thành `BalanceDTO` và trả về.
     *
     * @param balanceDTO Đối tượng số dư (`BalanceDTO`) cần cập nhật.
     * @return `BalanceDTO` Đối tượng số dư đã cập nhật.
     */
    @Override
    public BalanceDTO updateBalance(BalanceDTO balanceDTO) {
        if(balanceDTO.getId() == null) {
            return null;
        }
        if(balanceDTO.getBalance() == null) {
            return null;
        }
        if(balanceDTO.getAccountId() == null) {
            return null;
        }
        if(balanceDTO.getCreateAt() == null) {
            return null;
        }
        if(balanceDTO.getStatus() == null) {
            return null;
        }
        if(!balanceRepository.existsById(balanceDTO.getId())) {
            return null;
        }
        Balance balance = balanceRepository.findById(balanceDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Balance not found with id " + balanceDTO.getId()));

        BalanceMapper.updateBalance(balance, balanceDTO);
        Balance updatedBalance = balanceRepository.save(balance);
        return BalanceMapper.toDTO(updatedBalance);
    }

    /**
     * Xóa số dư (`Banlance`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra tính hợp lệ của `id`:
     *   - Nếu không tồn tại số dư với `id` đã cho, ném một ngoại lệ `EntityNotFoundException`.
     * 2. Gọi `balanceRepository.deleteById(id)` để xóa số dư.
     *
     * @param id ID của số dư cần xóa.
     */
    @Override
    public void deleteBalance(Integer id) {
        // Kiểm tra sự tồn tại của số dư với ID trước khi xóa
        if (!balanceRepository.existsById(id)) {
            throw new EntityNotFoundException("Balance not found with id " + id);
        }
        // Xóa số dư với ID đã cho
        balanceRepository.deleteById(id);
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
