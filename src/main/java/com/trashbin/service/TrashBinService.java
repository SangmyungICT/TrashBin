package com.trashbin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trashbin.domain.TrashBinEntity;
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

    @Transactional
    public TrashBinDto.ResponseDto createTrashBin(TrashBinDto.PostDto postDto) {
        TrashBinEntity trashBinEntity = trashBinMapper.trashBinRequestPostDtoToTrashBinEntity(postDto);
        log.info("Entity Id: {} is saved", trashBinEntity.getId());
        return trashBinMapper.TrashBinEntityToTrashBinResponseDto(trashBinRepository.save(trashBinEntity));
    }

    /**
     * gps 좌표에 따라 근처 쓰레기통을 리턴하는 로직을 만들어야 할 듯.
     */
    public List<TrashBinEntity> getTrashBins() {
        List<TrashBinEntity> all = trashBinRepository.findAll();
        return all;
    }

    public TrashBinDto.ResponseDto getTrashBin(Long trashBinId) {
        return trashBinMapper.TrashBinEntityToTrashBinResponseDto(trashBinRepository.findById(trashBinId).orElseThrow());
    }


    @Transactional
    public TrashBinDto.ResponseDto updateTrashBin(TrashBinDto.PatchDto patchDto) {
        TrashBinEntity trashBinEntity = trashBinRepository.findById(patchDto.getTrashBinId()).orElseThrow();
        trashBinEntity.patchEntity(patchDto);
        log.info("Entity Id: {} is patched", trashBinEntity.getId());
        return trashBinMapper.TrashBinEntityToTrashBinResponseDto
                (trashBinRepository.findById(patchDto.getTrashBinId()).orElseThrow());
    }

    @Transactional
    public void deleteTrashBin(TrashBinDto.DeleteDto deleteDto) {
        trashBinRepository.deleteById(deleteDto.getTrashBinId());
        log.info("Entity Id: {} is deleted", deleteDto.getTrashBinId());
    }
}
