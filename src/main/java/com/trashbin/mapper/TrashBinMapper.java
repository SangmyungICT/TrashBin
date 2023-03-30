package com.trashbin.mapper;

import com.trashbin.domain.Address;
import com.trashbin.domain.ReportEntity;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.domain.TrashCategory;
import com.trashbin.dto.TrashBinDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TrashBinMapper {

    default TrashBinEntity trashBinRequestPostDtoToTrashBinEntity(TrashBinDto.PostDto postDto) {
        if (postDto == null) {
            return null;
        } else {
            Address address = Address.builder()
                    .gu(postDto.getGu())
                    .roadName(postDto.getRoadName())
                    .detailAddress(postDto.getDetailAddress())
                    .installPoint(postDto.getInstallPoint())
                    .coordinateX(postDto.getCoordinateX())
                    .coordinateY(postDto.getCoordinateY())
                    .build();

            TrashCategory trashCategory;
            if (postDto.getTrashCategory().equals("CIGARETTE")) trashCategory = TrashCategory.CIGARETTE;
            else if (postDto.getTrashCategory().equals("RECYCLE")) trashCategory = TrashCategory.RECYCLE;
            else trashCategory = TrashCategory.GENERAL;

            return TrashBinEntity.builder()
                    .trashCategory(trashCategory)
                    .address(address)
                    .build();


        }
    }

    default TrashBinDto.ResponseDtos<TrashBinDto.TempTrashBinDto>
    TrashBinEntitiesToTrashBinResponseDtos(List<TrashBinEntity> trashBinEntities) {
        if (trashBinEntities.isEmpty()) {
            return null;
        } else {
            List<TrashBinDto.TempTrashBinDto> dtos = trashBinEntities.stream()
                    .map(TrashBinDto.TempTrashBinDto::new).collect(Collectors.toList());
            return TrashBinDto.ResponseDtos.<TrashBinDto.TempTrashBinDto>builder().trashBins(dtos).build();
        }
    }

    default TrashBinDto.ResponseDto TrashBinEntityToTrashBinResponseDto(TrashBinEntity trashBinEntity){
        if (trashBinEntity == null) {
            return null;
        } else {
            return TrashBinDto.ResponseDto.builder()
                    .trashBinId(trashBinEntity.getId())
                    .trashCategory(trashBinEntity.getTrashCategory())
                    .address(trashBinEntity.getAddress())
                    .build();
        }
    }

    default TrashBinEntity reportEntityToTrashBinEntity(ReportEntity reportEntity){
        if (reportEntity == null) {
            return null;
        } else {
            return TrashBinEntity.builder()
                    .address(reportEntity.getAddress())
                    .trashCategory(reportEntity.getTrashCategory())
                    .build();
        }
    }
}
