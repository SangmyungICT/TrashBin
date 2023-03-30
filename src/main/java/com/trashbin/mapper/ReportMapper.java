package com.trashbin.mapper;

import com.trashbin.domain.Address;
import com.trashbin.domain.ReportEntity;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.domain.TrashCategory;
import com.trashbin.dto.ReportDto;
import org.mapstruct.Mapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Mapper(componentModel = "spring")

public interface ReportMapper {

    default ReportEntity reportRequestPostDtoToReportEntity(ReportDto.PostDto postDto,TrashBinEntity trashBinEntity) {

        if (postDto == null) {
            return null;
        } else {

            return ReportEntity.builder()
                    .trashBinEntity(trashBinEntity)
                    .nullCount(postDto.getNullCount())
                    .build();
        }
    }

    default ReportDto.ResponseDto reportEntityToReportResponseDto(ReportEntity reportEntity){
        if (reportEntity == null) {
            return null;
        } else {
            return ReportDto.ResponseDto.builder()
                    .reportId(reportEntity.getId())
                    .trashBinEntity(reportEntity.getTrashBinEntity())
                    .nullCount(reportEntity.getNullCount())
                    .build();
        }
    }
}
