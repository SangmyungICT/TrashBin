package com.trashbin.service;

import com.trashbin.domain.ReportEntity;
import com.trashbin.dto.ReportDto;
import com.trashbin.mapper.ReportMapper;
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

    @Transactional
    public ReportDto.ReportResponseDto createReportAndSaveTrashBin(ReportDto.ReportPostDto reportPostDto) {
        ReportEntity reportEntity = reportMapper.reportRequestPostDtoToReportEntity(reportPostDto);
        reportRepository.save(reportEntity);

        log.info("Entity Id: {} is saved", reportEntity.getId());
        return reportMapper.reportEntityToReportResponseDto(reportRepository.findById(reportEntity.getId()).orElseThrow());
    }

    public ReportDto.ReportResponseDto getReport(Long reportId) {
        return reportMapper.reportEntityToReportResponseDto(reportRepository.findById(reportId).orElseThrow());
    }

    @Transactional
    public ReportDto.ReportResponseDto updateReport(ReportDto.ReportPatchDto reportPatchDto) {
        ReportEntity reportEntity = reportRepository.findById(reportPatchDto.getReportId()).orElseThrow();
        reportEntity.patchEntity(reportPatchDto);

        log.info("Entity Id: {} is patched", reportEntity.getId());
        return reportMapper.reportEntityToReportResponseDto(reportRepository.findById(reportPatchDto.getReportId()).orElseThrow());
    }

    @Transactional
    public void deleteReport(ReportDto.ReportDeleteDto reportDeleteDto) {
        reportRepository.deleteById(reportDeleteDto.getReportId());
        log.info("Entity Id: {} is deleted", reportDeleteDto.getReportId());
    }
}
