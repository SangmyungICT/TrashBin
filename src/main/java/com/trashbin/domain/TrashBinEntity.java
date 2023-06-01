package com.trashbin.domain;

import com.trashbin.dto.ReportDto;
import com.trashbin.dto.TrashBinDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrashBinEntity {
    @Id
    @GeneratedValue
    @Column(name = "trashbin_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TrashCategory trashCategory;
    @Embedded
    private Address address;

    public void patchEntity(TrashBinDto.TrashBinPatchDto trashBinPatchDto) {
        this.address = trashBinPatchDto.getAddress();
        this.trashCategory = trashBinPatchDto.getTrashCategory();
    }

    public void patchEntity(ReportDto.ReportPatchDto reportPatchDto) {
        if (!reportPatchDto.getAddress_name().equals("")) {
            this.address.patchAddress(reportPatchDto);
        }
        this.trashCategory = stringToTrashCategory(reportPatchDto.getTrashCategory());
    }

    public TrashCategory stringToTrashCategory(String stringCategory) {
        if (stringCategory.equals("CIGARETTE")) return TrashCategory.CIGARETTE;
        else if (stringCategory.equals("RECYCLE")) return TrashCategory.RECYCLE;
        else return TrashCategory.GENERAL;
    }

    //==생성 메서드==//
    public static TrashBinEntity createTrashBinEntity(ReportDto.ReportPostDto reportPostDto) {
        TrashCategory trashCategory;
        if (reportPostDto.getTrashCategory() != null) {
            if (reportPostDto.getTrashCategory().equals("CIGARETTE")) trashCategory = TrashCategory.CIGARETTE;
            else if (reportPostDto.getTrashCategory().equals("RECYCLE")) trashCategory = TrashCategory.RECYCLE;
            else trashCategory = TrashCategory.GENERAL;
        } else trashCategory = TrashCategory.GENERAL;


        Address address = Address.createAddress(reportPostDto);

        return TrashBinEntity.builder()
                .trashCategory(trashCategory)
                .address(address)
                .build();
    }
}
