package com.trashbin.dto;

import com.trashbin.domain.Address;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.domain.TrashCategory;
import io.swagger.annotations.ApiParam;
import lombok.*;


public class TrashBinDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostDto {
//        @ApiParam(value = "종류")
        private String trashCategory;
//        @ApiParam(value = "자치구명")
        private String gu; //자치구명
//        @ApiParam(value = "도로명")
        private String roadName; //도로명
//        @ApiParam(value = "상세주소")
        private String detailAddress; //상세주소
//        @ApiParam(value = "설치지점")
        private String installPoint; //설치지점
//        @ApiParam(value = "위도")
        private double latitude; //위도
//        @ApiParam(value = "경도")
        private double longitude; //경도
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostObjectDto {
//        @ApiParam(value = "쓰레기통 ID")
        private Long trashBinId;
//        @ApiParam(value = "")
        private TrashCategory trashCategory;
//        @ApiParam(value = "")
        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {
//        @ApiParam(value = "쓰레기통 ID")
        private Long trashBinId;
//        @ApiParam(value = "종류")
        private TrashCategory trashCategory;
//        @ApiParam(value = "주소")
        private Address address;


        public ResponseDto(TrashBinEntity trashBinEntity) {
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
    public static class DeleteDto {
//        @ApiParam(value = "쓰레기통 ID")
        private Long trashBinId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PatchDto {
//        @ApiParam(value = "쓰레기통 ID")
        private Long trashBinId;
//        @ApiParam(value = "종류")
        private TrashCategory trashCategory;
//        @ApiParam(value = "주소")
        private Address address;
    }

}
