package com.trashbin.dto;

import lombok.*;

public class ReportDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReportPostDto {
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
    public static class ReportResponseDto {
        private Long reportId;
        private TrashBinDto.TrashBinResponseDto trashBinInfo;
        private int nullCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReportDeleteDto {
        private Long reportId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReportPatchDto {
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
