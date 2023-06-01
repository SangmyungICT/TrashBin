package com.trashbin.mapper;

import com.trashbin.domain.ReportEntity;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.ReportDto;
import com.trashbin.dto.TrashBinDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface ReportMapper {

    default ReportEntity reportRequestPostDtoToReportEntity(ReportDto.ReportPostDto reportPostDto) {

        if (reportPostDto == null) {
            return null;
        } else {
            return ReportEntity.builder()
                    .trashBinEntity(TrashBinEntity.createTrashBinEntity(reportPostDto))
                    .nullCount(reportPostDto.getNullCount())
                    .build();
        }
    }

    default ReportDto.ReportResponseDto reportEntityToReportResponseDto(ReportEntity reportEntity) {
        if (reportEntity == null) {
            return null;
        } else {
            TrashBinDto.TrashBinResponseDto trashBinResponseDto = TrashBinDto.TrashBinResponseDto.builder()
                    .trashBinId(reportEntity.getTrashBinEntity().getId())
                    .address(reportEntity.getTrashBinEntity().getAddress())
                    .trashCategory(reportEntity.getTrashBinEntity().getTrashCategory())
                    .build();
            return ReportDto.ReportResponseDto.builder()
                    .reportId(reportEntity.getId())
                    .trashBinInfo(trashBinResponseDto)
                    .nullCount(reportEntity.getNullCount())
                    .build();
        }
    }
}
