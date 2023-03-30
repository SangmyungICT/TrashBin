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

//    @Enumerated(EnumType.STRING)
//    private TrashCategory trashCategory;

    //    @Embedded
//    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trashbin_id",unique = true)
    private TrashBinEntity trashBinEntity;

    private int nullCount; //3회가 넘어가면 허위신고로 간주
    private LocalDateTime reportTime;


    public void patchEntity(ReportDto.PatchDto patchDto){
        this.trashBinEntity = patchDto.getTrashBinEntity();
        this.nullCount = patchDto.getNullCount();
    }
}
