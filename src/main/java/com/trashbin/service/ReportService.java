package com.trashbin.service;

import com.trashbin.domain.ReportEntity;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.ReportDto;
import com.trashbin.mapper.ReportMapper;
import com.trashbin.mapper.TrashBinMapper;
import com.trashbin.repository.ReportRepository;
import com.trashbin.repository.TrashBinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;
    private final TrashBinRepository trashBinRepository;
    private final ReportMapper reportMapper;
    private final TrashBinMapper trashBinMapper;

    @Transactional
    public ReportDto.ResponseDto createReportAndSaveTrashBin(ReportDto.PostDto postDto){

        TrashBinEntity trashBinEntity = trashBinRepository.save(TrashBinEntity.createTrashBinEntity(postDto));

        ReportEntity reportEntity = reportMapper.reportRequestPostDtoToReportEntity(postDto,trashBinEntity);
        reportRepository.save(reportEntity);

        log.info("Entity Id: {} is saved",reportEntity.getId());
        return reportMapper.reportEntityToReportResponseDto(reportRepository.findById(reportEntity.getId()).orElseThrow());
    }

    public ReportDto.ResponseDto getReport(Long reportId){
        return reportMapper.reportEntityToReportResponseDto(reportRepository.findById(reportId).orElseThrow());
    }

    @Transactional
    public ReportDto.ResponseDto updateReport(ReportDto.PatchDto patchDto){
        ReportEntity reportEntity = reportRepository.findById(patchDto.getReportId()).orElseThrow();
        reportEntity.patchEntity(patchDto);
        TrashBinEntity trashBinEntity = trashBinRepository.findById(patchDto.getTrashBinEntity().getId()).orElseThrow();
        trashBinEntity.patchEntity(patchDto);
        log.info("Entity Id: {} is patched",reportEntity.getId());
        return reportMapper.reportEntityToReportResponseDto(reportRepository.findById(patchDto.getReportId()).orElseThrow());
    }
    @Transactional
    public void deleteReport(ReportDto.DeleteDto deleteDto){
        reportRepository.deleteById(deleteDto.getReportId());
        log.info("Entity Id: {} is deleted",deleteDto.getReportId());
    }
}
