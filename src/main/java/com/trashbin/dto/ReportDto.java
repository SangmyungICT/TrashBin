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
        private String trashCategory;
        private String gu; //자치구명
        private String roadName; //도로명
        private String detailAddress; //상세주소
        private String installPoint; //설치지점
        private double coordinateX; //X좌표
        private double coordinateY; //Y좌표
        private int nullCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {
        private Long reportId;
        private TrashBinDto.ResponseDto trashBinResponseDto;
        private int nullCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DeleteDto {
        private Long reportId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PatchDto {
        private Long reportId;
        private TrashBinDto.PostObjectDto trashBinPostObjectDto;
        private int nullCount;
    }
}
