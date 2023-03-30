package com.trashbin.domain;

import com.trashbin.dto.ReportDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportEntity {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TrashCategory trashCategory;

    private int nullCount; //3회가 넘어가면 허위신고로 간주
    private LocalDateTime reportTime;
    @Embedded
    private Address address;

    public void patchEntity(ReportDto.PatchDto patchDto){
        this.trashCategory = patchDto.getTrashCategory();
        this.address = patchDto.getAddress();
        this.nullCount = patchDto.getNullCount();
    }
}
