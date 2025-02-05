package com.banking.transaction.service.statusTransaction;

import com.banking.transaction.dto.StatusTransactionDTO;
import com.banking.transaction.entity.StatusTransaction;
import com.banking.transaction.mapper.StatusTransactionMapper;
import com.banking.transaction.repository.StatusTransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusTransactionServiceImpl implements StatusTransactionService {
    @Autowired
    private StatusTransactionRepository statusTransactionRepository;

    /**
     * Lấy danh sách tất cả các trạng thái giao dịch (`StatusTransaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusTransactionRepository.findAll()` để truy vấn tất cả các trạng thái giao dịch (`StatusTransaction`) có trong cơ sở dữ liệu.
     *   - Truy vấn không có điều kiện, trả về toàn bộ dữ liệu trong bảng `StatusTransaction`.
     *   - Trả về danh sách các `StatusTransaction` từ repository.
     * 2. Sử dụng Java Stream API để xử lý danh sách:
     *   - Tạo một luồng dữ liệu từ danh sách các StatusTransaction bằng `stream()`.
     *   - Chuyển từng đối tượng StatusTransaction thành StatusTransactionDTO bằng cách sử dụng `map()` và gọi `StatusTransactionMapper.toDTO()`.
     *   - Kết quả là một luồng chứa các StatusTransactionDTO.
     *  3. Gom các StatusTransactionDTO từ luồng thành một danh sách mới bằng `collect(Collectors.toList())`.
     *
     * @param `Không` có.
     * @return Danh sách tất cả các trạng thái giao dịch (`StatusTransaction`) trong cơ sở dữ liệu.
     */
    @Override
    public List<StatusTransactionDTO> getAllStatusTransactions() {
        return statusTransactionRepository.findAll().stream()
                .map(StatusTransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy trạng thái giao dịch (`StatusTransaction`) theo ID
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusTransactionRepository.findById(id)` để truy vấn trạng thái giao dịch (`StatusTransaction`) theo ID.
     *   - Trả về một `Optional<StatusTransaction>`.
     * 2. Sử dụng `map()` để chuyển đổi từ `Optional<StatusTransaction>` sang `StatusTransactionDTO`.
     * 3. Nếu không tìm thấy trạng thái giao dịch, ném một ngoại lệ.
     *
     * @param id ID của trạng thái giao dịch cần tìm.
     * @return `StatusTransactionDTO` Đối tượng trạng thái giao dịch tìm thấy.
     * @throws `EntityNotFoundException` nếu không tìm thấy trạng thái giao dịch với ID đã cho.
     */
    @Override
    public StatusTransactionDTO getStatusTransactionDTOById(Integer id) {
        return statusTransactionRepository.findById(id)
                .map(StatusTransactionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("StatusTransaction not found with id " + id));
    }

    @Override
    public StatusTransaction getStatusTransactionById(Integer id) {
        return statusTransactionRepository.findById(id)
                .orElseThrow();
    }


    /**
     * Tạo mới trạng thái giao dịch (`StatusTransaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Chuyển đổi từ `StatusTransactionDTO` sang `StatusTransaction` bằng cách sử dụng `StatusTransactionMapper.toEntity()`.
     * 2. Gọi `statusTransactionRepository.save()` để lưu trạng thái giao dịch mới vào cơ sở dữ liệu.
     * 3. Chuyển đổi từ `StatusTransaction` đã lưu sang `StatusTransactionDTO` bằng cách sử dụng `StatusTransactionMapper.toDTO()`.
     * 4. Trả về `StatusTransactionDTO` đã lưu.
     *
     * @param `statusTransactionDTO` Đối tượng trạng thái giao dịch cần tạo mới.
     * @return `StatusTransactionDTO` Đối tượng trạng thái giao dịch đã tạo mới.
     */
    @Override
    public StatusTransactionDTO createStatusTransaction(StatusTransactionDTO statusTransactionDTO) {
        StatusTransaction statusTransaction = StatusTransactionMapper.toEntity(statusTransactionDTO);
        StatusTransaction savedStatusTransaction = statusTransactionRepository.save(statusTransaction);
        return StatusTransactionMapper.toDTO(savedStatusTransaction);
    }


    /**
     * Cập nhật trạng thái giao dịch (`StatusTransaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra tính hợp lệ của `statusTransactionDTO`:
     *    - Nếu `id` là null, trả về null (không tiếp tục xử lý).
     *    - Nếu `name` là null, trả về null (không tiếp tục xử lý).
     * 2. Gọi `statusTransactionRepository.findById(statusTransactionDTO.getId())` để tìm trạng thái giao dịch trong cơ sở dữ liệu.
     *    - Nếu không tìm thấy, ném ngoại lệ `EntityNotFoundException`.
     * 3. Cập nhật `name` của trạng thái giao dịch từ `statusTransactionDTO`.
     * 4. Lưu trạng thái giao dịch đã cập nhật vào cơ sở dữ liệu bằng `statusTransactionRepository.save(statusTransaction)`.
     * 5. Chuyển đổi từ `StatusTransaction` đã lưu sang `StatusTransactionDTO` bằng cách sử dụng `StatusTransactionMapper.toDTO()`.
     * 6. Trả về `StatusTransactionDTO` đã cập nhật.
     *
     * @param `statusTransactionDTO` Đối tượng trạng thái giao dịch cần cập nhật.
     * @return `StatusTransactionDTO` Đối tượng trạng thái giao dịch đã cập nhật.
     */
    @Override
    public StatusTransactionDTO updateStatusTransaction(StatusTransactionDTO statusTransactionDTO) {
        if(statusTransactionDTO.getId() == null) {
            return null;
        }
        if(statusTransactionDTO.getName() == null) {
            return null;
        }
        StatusTransaction statusTransaction = statusTransactionRepository.findById(statusTransactionDTO.getId())
                .orElseThrow();

        statusTransaction.setName(statusTransactionDTO.getName());
        StatusTransaction updatedStatusTransaction = statusTransactionRepository.save(statusTransaction);
        return StatusTransactionMapper.toDTO(updatedStatusTransaction);
    }

    /**
     * Xóa trạng thái giao dịch (`StatusTransaction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusTransactionRepository.deleteById(id)` để xóa trạng thái giao dịch theo ID.
     *
     * @param `id` ID của trạng thái giao dịch cần xóa.
     * @return `Không` có.
     */
    @Override
    public void deleteStatusTransaction(Integer id) {
        statusTransactionRepository.deleteById(id);
    }
}
