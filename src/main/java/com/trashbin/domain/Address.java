package com.trashbin.domain;

import com.trashbin.dto.ReportDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Address {
    private String gu; //자치구명
    private String roadName; //도로명
    private String detailAddress; //상세주소
    private String installPoint; //설치지점

    private double latitude; //위도

    private double longitude; //경도

    //==생성 메서드==//
    public static Address createAddress(ReportDto.PostDto postDto) {
        return Address.builder()
                .gu(postDto.getGu())
                .roadName(postDto.getRoadName())
                .detailAddress(postDto.getDetailAddress())
                .installPoint(postDto.getInstallPoint())
                .latitude(postDto.getLatitude())
                .longitude(postDto.getLongitude())
                .build();
    }
}
