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

    public void patchEntity(TrashBinDto.PatchDto patchDto) {
        this.address = patchDto.getAddress();
        this.trashCategory = patchDto.getTrashCategory();
    }

    public void patchEntity(ReportDto.PatchDto patchDto) {
        if (!patchDto.getAddress_name().equals("")) {
            this.address.patchAddress(patchDto);
        }
        this.trashCategory = stringToTrashCategory(patchDto.getTrashCategory());
    }

    public TrashCategory stringToTrashCategory(String stringCategory) {
        if (stringCategory.equals("CIGARETTE")) return TrashCategory.CIGARETTE;
        else if (stringCategory.equals("RECYCLE")) return TrashCategory.RECYCLE;
        else return TrashCategory.GENERAL;
    }

    //==생성 메서드==//
    public static TrashBinEntity createTrashBinEntity(ReportDto.PostDto postDto) {
        TrashCategory trashCategory;
        if (postDto.getTrashCategory() != null) {
            if (postDto.getTrashCategory().equals("CIGARETTE")) trashCategory = TrashCategory.CIGARETTE;
            else if (postDto.getTrashCategory().equals("RECYCLE")) trashCategory = TrashCategory.RECYCLE;
            else trashCategory = TrashCategory.GENERAL;
        } else trashCategory = TrashCategory.GENERAL;


        Address address = Address.createAddress(postDto);

        return TrashBinEntity.builder()
                .trashCategory(trashCategory)
                .address(address)
                .build();
    }
}
