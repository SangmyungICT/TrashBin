package com.trashbin.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

public class ClientDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GPSDto {
        private double latitude; //위도
        private double longitude; //경도
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GPSDistanceDto {
        private double latitude; //위도
        private double longitude; //경도
        private int distance; //조회할 범위
    }

}
