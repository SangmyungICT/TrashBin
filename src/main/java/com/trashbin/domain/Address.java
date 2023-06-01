package com.trashbin.domain;

import com.trashbin.dto.ReportDto;
import lombok.*;

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
    public static Address createAddress(ReportDto.ReportPostDto reportPostDto) {
        return Address.builder()
                .gu(reportPostDto.getRegion_2depth_name())
                .roadName(reportPostDto.getRoad_name())
                .detailAddress(reportPostDto.getRoad_name()+ reportPostDto.getMain_building_no())
                .installPoint(reportPostDto.getBuilding_name())
                .latitude(reportPostDto.getLatitude())
                .longitude(reportPostDto.getLongitude())
                .build();
    }

    public void patchAddress(ReportDto.ReportPatchDto reportPatchDto) {
            this.gu = reportPatchDto.getRegion_2depth_name();
            this.roadName = reportPatchDto.getRoad_name();
            this.detailAddress = reportPatchDto.getRoad_name() + reportPatchDto.getMain_building_no();
            this.installPoint = reportPatchDto.getBuilding_name();
            this.latitude = reportPatchDto.getLatitude();
            this.longitude = reportPatchDto.getLongitude();
    }
}
