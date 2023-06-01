package com.trashbin.mapper;

import com.trashbin.domain.Address;
import com.trashbin.domain.ReportEntity;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.ReportDto;
import com.trashbin.dto.TrashBinDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface ReportMapper {

    default ReportEntity reportRequestPostDtoToReportEntity(ReportDto.PostDto postDto) {

        if (postDto == null) {
            return null;
        } else {
            return ReportEntity.builder()
                    .trashBinEntity(TrashBinEntity.createTrashBinEntity(postDto))
                    .nullCount(postDto.getNullCount())
                    .build();
        }
    }

    default ReportDto.ResponseDto reportEntityToReportResponseDto(ReportEntity reportEntity) {
        if (reportEntity == null) {
            return null;
        } else {
            TrashBinDto.ResponseDto trashBinResponseDto = TrashBinDto.ResponseDto.builder()
                    .trashBinId(reportEntity.getTrashBinEntity().getId())
                    .address(reportEntity.getTrashBinEntity().getAddress())
                    .trashCategory(reportEntity.getTrashBinEntity().getTrashCategory())
                    .build();
            return ReportDto.ResponseDto.builder()
                    .reportId(reportEntity.getId())
                    .trashBinInfo(trashBinResponseDto)
                    .nullCount(reportEntity.getNullCount())
                    .build();
        }
    }
}
