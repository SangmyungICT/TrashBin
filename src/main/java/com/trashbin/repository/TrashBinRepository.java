package com.trashbin.repository;

import com.trashbin.domain.TrashBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrashBinRepository extends JpaRepository<TrashBinEntity, Long> {

    @Query("select t from TrashBinEntity t where t.address.longitude " +
            "between :minLongitude and :maxLongitude " +
            "and t.address.latitude between :minLatitude and :maxLatitude")
    List<TrashBinEntity> getNearTrashBinEntities(
            @Param("minLatitude") double minLatitude, @Param("maxLatitude") double maxLatitude,
            @Param("minLongitude") double minLongitude, @Param("maxLongitude") double maxLongitude);
}
