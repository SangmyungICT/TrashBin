package com.trashbin.domain;

import com.trashbin.dto.ReportDto;
import com.trashbin.dto.TrashBinDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
                .gu(postDto.getRegion_2depth_name())
                .roadName(postDto.getRoad_name())
                .detailAddress(postDto.getRoad_name()+postDto.getMain_building_no())
                .installPoint(postDto.getBuilding_name())
                .latitude(postDto.getLatitude())
                .longitude(postDto.getLongitude())
                .build();
    }

    public void patchAddress(ReportDto.PatchDto patchDto) {
            this.gu = patchDto.getRegion_2depth_name();
            this.roadName = patchDto.getRoad_name();
            this.detailAddress = patchDto.getRoad_name() + patchDto.getMain_building_no();
            this.installPoint = patchDto.getBuilding_name();
            this.latitude = patchDto.getLatitude();
            this.longitude = patchDto.getLongitude();
    }
}
