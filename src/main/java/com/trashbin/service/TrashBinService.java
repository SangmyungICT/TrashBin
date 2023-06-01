package com.trashbin.service;

import com.trashbin.GetNearPin;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.ClientDto;
import com.trashbin.dto.TrashBinDto;
import com.trashbin.mapper.TrashBinMapper;
import com.trashbin.repository.TrashBinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrashBinService {

    private final TrashBinRepository trashBinRepository;
    private final TrashBinMapper trashBinMapper;
    private final GetNearPin getNearPin;

    @Transactional
    public TrashBinDto.TrashBinResponseDto createTrashBin(TrashBinDto.TrashBinPostDto trashBinPostDto) {
        TrashBinEntity trashBinEntity = trashBinMapper.trashBinRequestPostDtoToTrashBinEntity(trashBinPostDto);
        log.info("Entity Id: {} is saved", trashBinEntity.getId());
        return trashBinMapper.TrashBinEntityToTrashBinResponseDto(trashBinRepository.save(trashBinEntity));
    }

    /**
     * gps 좌표에 따라 근처 쓰레기통을 리턴하는 로직을 만들어야 할 듯.
     */
    public List<TrashBinDto.TrashBinResponseDto> getAllTrashBin() {
        return trashBinMapper.TrashBinEntitiesToTrashBinResponseDtos(trashBinRepository.findAll());
    }

    public List<TrashBinDto.TrashBinResponseDto> getNearTrashBin(ClientDto.ClientGPSDistanceDto clientGpsDistanceDto) {
        return trashBinMapper.TrashBinEntitiesToTrashBinResponseDtos(getNearPin.nearTrashBinList(clientGpsDistanceDto));
    }

    public TrashBinDto.TrashBinResponseDto getTrashBin(Long trashBinId) {
        return trashBinMapper.TrashBinEntityToTrashBinResponseDto(trashBinRepository.findById(trashBinId).orElseThrow());
    }


    @Transactional
    public TrashBinDto.TrashBinResponseDto updateTrashBin(TrashBinDto.TrashBinPatchDto trashBinPatchDto) {
        TrashBinEntity trashBinEntity = trashBinRepository.findById(trashBinPatchDto.getTrashBinId()).orElseThrow();
        trashBinEntity.patchEntity(trashBinPatchDto);
        log.info("Entity Id: {} is patched", trashBinEntity.getId());
        return trashBinMapper.TrashBinEntityToTrashBinResponseDto
                (trashBinRepository.findById(trashBinPatchDto.getTrashBinId()).orElseThrow());
    }

    /**
     * report로 등록된 쓰레기통은 삭제가 안됨.
     */
    @Transactional
    public void deleteTrashBin(TrashBinDto.TrashBinDeleteDto trashBinDeleteDto) {
        trashBinRepository.deleteById(trashBinDeleteDto.getTrashBinId());
        log.info("Entity Id: {} is deleted", trashBinDeleteDto.getTrashBinId());
    }
}
