package org.zerock.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

    @Modifying
    @Query("update from PDSFile f set f.pdsfile = ?2 where f.fno = ?1 ")
    public int updatePDSFile(Long fo, String newFileName);
}
