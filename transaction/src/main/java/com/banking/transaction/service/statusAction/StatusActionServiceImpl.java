package com.banking.transaction.service.statusAction;

import com.banking.transaction.dto.StatusActionDTO;
import com.banking.transaction.entity.StatusAction;
import com.banking.transaction.mapper.StatusActionMapper;
import com.banking.transaction.repository.StatusActionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusActionServiceImpl implements StatusActionService {
    @Autowired
    private StatusActionRepository statusActionRepository;

    /**
     * Lấy danh sách tất cả các hành động trạng thái (`StatusAction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusActionRepository.findAll()` để truy vấn tất cả các hành động trạng thái (`StatusAction`) có trong cơ sở dữ liệu.
     *   - Truy vấn không có điều kiện, trả về toàn bộ dữ liệu trong bảng `StatusAction`.
     *   - Trả về danh sách các `StatusAction` từ repository.
     * 2. Sử dụng Java Stream API để xử lý danh sách:
     *   - Tạo một luồng dữ liệu từ danh sách các StatusAction bằng `stream()`.
     *   - Chuyển từng đối tượng StatusAction thành StatusActionDTO bằng cách sử dụng `map()` và gọi `StatusActionMapper.toDTO()`.
     *   - Kết quả là một luồng chứa các StatusActionDTO.
     *  3. Gom các StatusActionDTO từ luồng thành một danh sách mới bằng `collect(Collectors.toList())`.
     *
     * @param ``Không`` có.
     * @return Danh sách tất cả các hành động trạng thái (`StatusAction`) trong cơ sở dữ liệu.
     */
    @Override
    public List<StatusActionDTO> getAllStatusActions() {
        return statusActionRepository.findAll().stream()
                .map(StatusActionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lấy hành động trạng thái (`StatusAction`) theo ID
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusActionRepository.findById(id)` để truy vấn hành động trạng thái (`StatusAction`) theo ID.
     *   - Trả về một `Optional<StatusAction>`.
     * 2. Sử dụng `map()` để chuyển đổi từ `Optional<StatusAction>` sang `StatusActionDTO`.
     * 3. Nếu không tìm thấy hành động trạng thái, ném một ngoại lệ.
     *
     * @param id ID của hành động trạng thái cần tìm.
     * @return `StatusActionDTO` Đối tượng hành động trạng thái tìm thấy.
     * @throws ``EntityNotFoundException`` nếu không tìm thấy hành động trạng thái với ID đã cho.
     */
    @Override
    public StatusActionDTO getStatusActionDTOById(Integer id) {
        return statusActionRepository.findById(id)
                .map(StatusActionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("StatusAction not found with id " + id));
    }


    @Override
    public StatusAction getStatusActionById(Integer id) {
        return statusActionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StatusAction not found with id " + id));
    }

    /**
     * Tạo hành động trạng thái (`StatusAction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Chuyển đổi từ `StatusActionDTO` sang `StatusAction` bằng cách sử dụng `StatusActionMapper.toEntity()`.
     * 2. Lưu đối tượng StatusAction vào cơ sở dữ liệu bằng cách gọi `statusActionRepository.save(statusAction);`.
     * 3. Chuyển đổi từ `StatusAction` đã lưu sang `StatusActionDTO` bằng cách sử dụng `StatusActionMapper.toDTO()`.
     * 4. Trả về `StatusActionDTO` đã lưu.
     *
     * @param statusActionDTO Đối tượng hành động trạng thái cần tạo.
     * @return `StatusActionDTO` Đối tượng hành động trạng thái đã tạo.
     */
    @Override
    public StatusActionDTO createStatusAction(StatusActionDTO statusActionDTO) {
        StatusAction statusAction = StatusActionMapper.toEntity(statusActionDTO);
        StatusAction savedStatusAction = statusActionRepository.save(statusAction);
        return StatusActionMapper.toDTO(savedStatusAction);
    }

    /**
     * Cập nhật hành động trạng thái (`StatusAction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Kiểm tra tính hợp lệ của `statusActionDTO`:
     *   - Nếu `id` là null, trả về `null`.
     *   - Nếu `name` là null, trả về `null`.
     * 2. Gọi `statusActionRepository.findById(id)` để tìm hành động trạng thái theo ID.
     *   - Nếu không tìm thấy hành động trạng thái, ném một ngoại lệ.
     *   - Nếu tìm thấy hành động trạng thái, trả về đối tượng `StatusAction`.
     * 4. Lưu hành động trạng thái đã cập nhật vào cơ sở dữ liệu bằng cách gọi `statusActionRepository.save(statusAction)`.
     * 5. Chuyển đổi từ `StatusAction` đã cập nhật sang `StatusActionDTO` bằng cách sử dụng `StatusActionMapper.toDTO()`.
     * 6. Trả về `StatusActionDTO` đã cập nhật.
     *
     * @param statusActionDTO Đối tượng hành động trạng thái cần cập nhật.
     * @return `StatusActionDTO` Đối tượng hành động trạng thái đã cập nhật.
     */
    @Override
    public StatusActionDTO updateStatusAction(StatusActionDTO statusActionDTO) {
        if(statusActionDTO.getId() == null) {
           return null;
        }
        if(statusActionDTO.getName() == null) {
            return null;
        }
        StatusAction statusAction = statusActionRepository.findById(statusActionDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("StatusAction not found with id " + statusActionDTO.getId()));
        statusAction.setName(statusActionDTO.getName());
        StatusAction updatedStatusAction = statusActionRepository.save(statusAction);
        return StatusActionMapper.toDTO(updatedStatusAction);
    }

    /**
     * Xóa hành động trạng thái (`StatusAction`)
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Gọi `statusActionRepository.deleteById(id)` để xóa hành động trạng thái theo ID.
     *
     * @param id ID của hành động trạng thái cần xóa.
     * @return Không có.
     */
    @Override
    public void deleteStatusAction(Integer id) {
        statusActionRepository.deleteById(id);
    }

}
