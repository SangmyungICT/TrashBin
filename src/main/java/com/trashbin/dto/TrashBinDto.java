package com.trashbin.dto;

import com.trashbin.domain.Address;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.domain.TrashCategory;
import lombok.*;

import java.util.List;

public class TrashBinDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostDto {
        private String trashCategory;
        private String gu; //자치구명
        private String roadName; //도로명
        private String detailAddress; //상세주소
        private String installPoint; //설치지점
        private double coordinateX; //X좌표
        private double coordinateY; //Y좌표
    }

    public static class TempTrashBinDto{
        private Long id;
        private TrashCategory trashCategory;
        private Address address;

        public TempTrashBinDto(TrashBinEntity trashBinEntity) {
            this.id = trashBinEntity.getId();
            this.trashCategory = trashBinEntity.getTrashCategory();
            this.address = trashBinEntity.getAddress();
        }
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDtos<T> {
        private List<T> trashBins;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {
        private Long trashBinId;
        private TrashCategory trashCategory;
        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DeleteDto {
        private Long trashBinId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PatchDto {
        private Long trashBinId;
        private TrashCategory trashCategory;
        private Address address;
    }
}
