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

    default TrashBinEntity trashBinRequestPostDtoToTrashBinEntity(TrashBinDto.TrashBinPostDto trashBinPostDto) {
        if (trashBinPostDto == null) {
            return null;
        } else {
            Address address = Address.builder()
                    .gu(trashBinPostDto.getGu())
                    .roadName(trashBinPostDto.getRoadName())
                    .detailAddress(trashBinPostDto.getDetailAddress())
                    .installPoint(trashBinPostDto.getInstallPoint())
                    .latitude(trashBinPostDto.getLatitude())
                    .longitude(trashBinPostDto.getLongitude())
                    .build();

            TrashCategory trashCategory;
            if (trashBinPostDto.getTrashCategory().equals("CIGARETTE")) trashCategory = TrashCategory.CIGARETTE;
            else if (trashBinPostDto.getTrashCategory().equals("RECYCLE")) trashCategory = TrashCategory.RECYCLE;
            else trashCategory = TrashCategory.GENERAL;

            return TrashBinEntity.builder()
                    .trashCategory(trashCategory)
                    .address(address)
                    .build();


        }
    }

    default List<TrashBinDto.TrashBinResponseDto>
    TrashBinEntitiesToTrashBinResponseDtos(List<TrashBinEntity> trashBinEntities) {
        if (trashBinEntities.isEmpty()) {
            return null;
        } else {
            return trashBinEntities.stream()
                    .map(TrashBinDto.TrashBinResponseDto::new).collect(Collectors.toList());
        }
    }

    default TrashBinDto.TrashBinResponseDto TrashBinEntityToTrashBinResponseDto(TrashBinEntity trashBinEntity) {
        if (trashBinEntity == null) {
            return null;
        } else {
            return TrashBinDto.TrashBinResponseDto.builder()
                    .trashBinId(trashBinEntity.getId())
                    .trashCategory(trashBinEntity.getTrashCategory())
                    .address(trashBinEntity.getAddress())
                    .build();
        }
    }
}
