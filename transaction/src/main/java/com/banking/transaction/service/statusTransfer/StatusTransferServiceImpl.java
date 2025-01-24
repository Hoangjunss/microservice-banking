package com.banking.transaction.service.statusTransfer;

import com.banking.transaction.dto.StatusTransferDTO;
import com.banking.transaction.entity.StatusTransfer;
import com.banking.transaction.mapper.StatusTransferMapper;
import com.banking.transaction.repository.StatusTransferRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusTransferServiceImpl implements  StatusTransferService {
    @Autowired
    private StatusTransferRepository statusTransferRepository;

    /**
     * Lấy danh sách tất cả các trạng thái chuyển khoản (`StatusTransfer`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusTransferRepository.findAll()` để truy vấn tất cả các trạng thái chuyển khoản (`StatusTransfer`) có trong cơ sở dữ liệu.
     *  - Truy vấn không có điều kiện, trả về toàn bộ dữ liệu trong bảng `StatusTransfer`.
     *  - Trả về danh sách các `StatusTransfer` từ repository.
     *  2. Sử dụng Java Stream API để xử lý danh sách:
     *  - Tạo một luồng dữ liệu từ danh sách các StatusTransfer bằng `stream()`.
     *  - Chuyển từng đối tượng StatusTransfer thành StatusTransferDTO bằng cách sử dụng `map()` và gọi `StatusTransferMapper.toDTO()`.
     *  - Kết quả là một luồng chứa các StatusTransferDTO.
     *  3. Gom các StatusTransferDTO từ luồng thành một danh sách mới bằng `collect(Collectors.toList())`.
     *  @param `Không` có.
     *  @return Danh sách tất cả các trạng thái chuyển khoản (`StatusTransfer`) trong cơ sở dữ liệu.
     */
    @Override
    public List<StatusTransferDTO> getAllStatusTransfers() {
        return statusTransferRepository.findAll().stream()
                .map(StatusTransferMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy trạng thái chuyển khoản (`StatusTransfer`) theo ID
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusTransferRepository.findById(id)` để truy vấn trạng thái chuyển khoản (`StatusTransfer`) theo ID.
     *  - Trả về một `Optional<StatusTransfer>`.
     * 2. Sử dụng `map()` để chuyển đổi từ `Optional<StatusTransfer>` sang `StatusTransferDTO`.
     * 3. Nếu không tìm thấy trạng thái chuyển khoản, ném một ngoại lệ.
     *
     * @param id ID của trạng thái chuyển khoản cần tìm.
     * @return `StatusTransferDTO` Đối tượng trạng thái chuyển khoản tìm thấy.
     * @throws `EntityNotFoundException` nếu không tìm thấy trạng thái chuyển khoản với ID đã cho.
     */
    @Override
    public StatusTransferDTO getStatusTransferById(Integer id) {
        statusTransferRepository.findById(id)
                .map(StatusTransferMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("StatusTransfer not found with id " + id));
        return null;
    }

    /**
     * Tạo một trạng thái chuyển khoản (`StatusTransfer`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Chuyển đổi từ `StatusTransferDTO` sang `StatusTransfer` bằng cách sử dụng `StatusTransferMapper.toEntity()`.
     * 2. Gọi `statusTransferRepository.save(statusTransfer)` để lưu trạng thái chuyển khoản vào cơ sở dữ liệu.
     * 3. Chuyển đổi từ `StatusTransfer` đã lưu sang `StatusTransferDTO` bằng cách sử dụng `StatusTransferMapper.toDTO()`.
     * 4. Trả về `StatusTransferDTO` đã lưu.
     *
     * @param `statusTransferDTO` Đối tượng trạng thái chuyển khoản cần tạo.
     * @return `StatusTransferDTO` Đối tượng trạng thái chuyển khoản đã tạo.
     */
    @Override
    public StatusTransferDTO createStatusTransfer(StatusTransferDTO statusTransferDTO) {
        StatusTransfer statusTransfer = StatusTransferMapper.toEntity(statusTransferDTO);
        StatusTransfer savedStatusTransfer = statusTransferRepository.save(statusTransfer);
        return StatusTransferMapper.toDTO(savedStatusTransfer);
    }


    /**
     * Cập nhật trạng thái chuyển khoản (`StatusTransfer`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra tính hợp lệ của `statusTransferDTO`:
     *   - Nếu `id` là null, trả về `null`.
     *   - Nếu `name` là null, trả về `null`.
     * 2. Gọi `statusTransferRepository.findById(statusTransferDTO.getId())` để tìm trạng thái chuyển khoản trong cơ sở dữ liệu.
     * 3. Cập nhật trạng thái chuyển khoản với thông tin mới từ `statusTransferDTO`.
     * 4. Lưu trạng thái chuyển khoản đã cập nhật vào cơ sở dữ liệu bằng `statusTransferRepository.save(statusTransfer)`.
     * 5. Chuyển đổi từ `StatusTransfer` đã lưu sang `StatusTransferDTO` bằng cách sử dụng `StatusTransferMapper.toDTO()`.
     * 6. Trả về `StatusTransferDTO` đã cập nhật.
     *
     * @param `statusTransferDTO` Đối tượng trạng thái chuyển khoản cần cập nhật.
     * @return `StatusTransferDTO` Đối tượng trạng thái chuyển khoản đã cập nhật.
     */
    @Override
    public StatusTransferDTO updateStatusTransfer(StatusTransferDTO statusTransferDTO) {
        if(statusTransferDTO.getId() == null) {
            return null;
        }
        if(statusTransferDTO.getName() == null) {
            return null;
        }
        StatusTransfer statusTransfer = statusTransferRepository.findById(statusTransferDTO.getId())
                .orElseThrow();

        statusTransfer.setName(statusTransferDTO.getName());
        StatusTransfer updatedStatusTransfer = statusTransferRepository.save(statusTransfer);
        return StatusTransferMapper.toDTO(updatedStatusTransfer);
    }

    /**
     * Xóa trạng thái chuyển khoản (`StatusTransfer`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusTransferRepository.deleteById(id)` để xóa trạng thái chuyển khoản trong cơ sở dữ liệu.
     *
     * @param id ID của trạng thái chuyển khoản cần xóa.
     */
    @Override
    public void deleteStatusTransfer(Integer id) {
        statusTransferRepository.deleteById(id);
    }

}
