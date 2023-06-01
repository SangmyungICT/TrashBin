package com.trashbin.dto;

import com.trashbin.domain.Address;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.domain.TrashCategory;
import lombok.*;


public class TrashBinDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrashBinPostDto {
        private String trashCategory;
        private String gu; //자치구명
        private String roadName; //도로명
        private String detailAddress; //상세주소
        private String installPoint; //설치지점
        private double latitude; //위도
        private double longitude; //경도
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrashBinResponseDto {
        private Long trashBinId;
        private TrashCategory trashCategory;
        private Address address;


        public TrashBinResponseDto(TrashBinEntity trashBinEntity) {
            this.trashBinId = trashBinEntity.getId();
            this.trashCategory = trashBinEntity.getTrashCategory();
            this.address = trashBinEntity.getAddress();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrashBinDeleteDto {
        private Long trashBinId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrashBinPatchDto {
        private Long trashBinId;
        private TrashCategory trashCategory;
        private Address address;
    }

}
