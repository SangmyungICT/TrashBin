package com.trashbin.mapper;

import com.trashbin.domain.Address;
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

    default List<TrashBinDto.ResponseDto>
    TrashBinEntitiesToTrashBinResponseDtos(List<TrashBinEntity> trashBinEntities) {
        if (trashBinEntities.isEmpty()) {
            return null;
        } else {
            return trashBinEntities.stream()
                    .map(TrashBinDto.ResponseDto::new).collect(Collectors.toList());
        }
    }

    default TrashBinDto.ResponseDto TrashBinEntityToTrashBinResponseDto(TrashBinEntity trashBinEntity) {
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
}
