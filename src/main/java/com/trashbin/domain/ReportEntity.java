package com.trashbin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString
public class ReportEntity {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trashbin_id")
    @JsonIgnore
    private TrashBinEntity trashBinEntity;

    private int nullCount; //3회가 넘어가면 허위신고로 간주
    private LocalDateTime reportTime;


    public void patchEntity(ReportDto.PatchDto patchDto) {
        this.trashBinEntity.setTrashCategory(patchDto.getTrashBinPostObjectDto().getTrashCategory());
        this.trashBinEntity.setAddress(patchDto.getTrashBinPostObjectDto().getAddress());
        this.nullCount = patchDto.getNullCount();
    }
}
