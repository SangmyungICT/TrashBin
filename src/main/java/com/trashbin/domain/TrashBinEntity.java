package com.trashbin.domain;

import com.trashbin.dto.TrashBinDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
