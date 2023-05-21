package com.trashbin.dto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.context.annotation.Bean;

public class ClientDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
////    @ApiOperation(value = "사용자 GPS정보")
    public static class GPSDto {
//        @ApiParam(value = "위도")
        private double latitude; //위도
//        @ApiParam(value = "경도")
        private double longitude; //경도
    }

////    @ApiOperation(value = "사용자 GPS 정보 + 쓰레기통 조회할 범위")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GPSDistanceDto {
        @ApiParam(value = "위도")
        private double latitude; //위도
        @ApiParam(value = "경도")
        private double longitude; //경도
        @ApiParam(value = "조회할 범위. 단위는 미터 단위 정수")
        private int distance; //조회할 범위
    }

}
