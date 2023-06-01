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
        private String address_name;
        private String building_name;
        private String main_building_no;
        private String region_1depth_name;
        private String region_2depth_name;
        private String region_3depth_name;
        private String road_name;

        private double latitude; //위도
        private double longitude; //경도
        private int nullCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {
        private Long reportId;
        private TrashBinDto.ResponseDto trashBinInfo;
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

        private String trashCategory;
        private String address_name;
        private String building_name;
        private String main_building_no;
        private String region_1depth_name;
        private String region_2depth_name;
        private String region_3depth_name;
        private String road_name;

        private Double latitude; //위도
        private Double longitude; //경도
        private int nullCount;
    }
}
