package com.trashbin.dto;

import lombok.*;

public class ReportDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class PostDto {
//        @ApiParam(value = "쓰레기통 종류")
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
//        @ApiParam(value = "쓰레기통이 없다고 보고된 횟수")
        private int nullCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {
//        @ApiParam(value = "report ID")
        private Long reportId;
//        @ApiParam(value = "쓰레기통 정보")
        private TrashBinDto.ResponseDto trashBinInfo;
//        @ApiParam(value = "쓰레기통이 없다고 보고된 횟수")
        private int nullCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DeleteDto {
//        @ApiParam(value = "report ID")
        private Long reportId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PatchDto {
//        @ApiParam(value = "report ID")
        private Long reportId;
//        @ApiParam(value = "수정할 데이터")
        private TrashBinDto.PostObjectDto afterTrashBinPatch;
//        @ApiParam(value = "쓰레기통이 없다고 보고된 횟수")
        private int nullCount;
    }
}
