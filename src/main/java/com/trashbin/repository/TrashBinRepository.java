package com.trashbin.repository;

import com.trashbin.domain.TrashBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashBinRepository extends JpaRepository<TrashBinEntity,Long> {

}
