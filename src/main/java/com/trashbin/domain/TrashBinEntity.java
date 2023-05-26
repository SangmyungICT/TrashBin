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
        this.address = patchDto.getAfterTrashBinPatch().getAddress();
        this.trashCategory = patchDto.getAfterTrashBinPatch().getTrashCategory();
    }

    //==생성 메서드==//
    public static TrashBinEntity createTrashBinEntity(ReportDto.PostDto postDto) {
        TrashCategory trashCategory;
        if (postDto.getTrashCategory().equals("CIGARETTE")) trashCategory = TrashCategory.CIGARETTE;
        else if (postDto.getTrashCategory().equals("RECYCLE")) trashCategory = TrashCategory.RECYCLE;
        else trashCategory = TrashCategory.GENERAL;

        Address address = Address.createAddress(postDto);

        return TrashBinEntity.builder()
                .trashCategory(trashCategory)
                .address(address)
                .build();
    }
}
