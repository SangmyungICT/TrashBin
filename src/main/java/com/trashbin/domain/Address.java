package com.trashbin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String gu; //자치구명
    private String roadName; //도로명
    private String detailAddress; //상세주소
    private String installPoint; //설치지점
    @Column(nullable = true,name = "COORDINATE_X")
    private double coordinateX; //X좌표
    @Column(nullable = true,name = "COORDINATE_Y")
    private double coordinateY; //Y좌표
}
