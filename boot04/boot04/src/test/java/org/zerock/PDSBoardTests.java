package org.zerock;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.PDSBoard;
import org.zerock.domain.PDSFile;
import org.zerock.persistence.PDSBoardRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

    @Autowired
    PDSBoardRepository repo;

    @Test
    public void testInsertPDS() {

        PDSBoard pds = new PDSBoard();
        pds.setPname("DOCUMNET 1 - 2");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1, file2));

        log.info("try to save pds");

        repo.save(pds);
    }

    @Transactional
    @Test
    public void testUpdateFileName1() {

        Long fno = 1L;
        String newName = "updatedFile1.doc";

        int count = repo.updatePDSFile(fno, newName);

        // @Log 설정된 이후 사용 가능
        log.info("update count: " + count);
    }

    @Transactional
    @Test
    public void testUpdateFileName2() {

        String newName = "updatedFile2.doc";

        // 반드시 번호가 존재하는지 확인할 것
        Optional<PDSBoard> result = repo.findById(2L);

        result.ifPresent(pds -> {
            log.info("데이터가 존재하므로 update 시도");

            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);

            if (idx > -1) {

                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
                pds.setFiles(list);
            }
            repo.save(pds);
        });
    }
}
