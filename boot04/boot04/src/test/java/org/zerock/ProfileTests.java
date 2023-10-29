package org.zerock;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Member;
import org.zerock.domain.Profile;
import org.zerock.persistence.MemberRepository;
import org.zerock.persistence.ProfileRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit // 테스트 결과 commit
public class ProfileTests {

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    ProfileRepository profileRepo;

    @Test
    public void testInsertMembers() {

        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUwd("pw" + i);
            member.setUname("사용자" + i);

            memberRepo.save(member);
        });
    } // end method

    @Test
    public void InsertProfile() {

        Member member = new Member();
        member.setUid("user1");

        for (int i = 1; i < 5; i++) {

            Profile profile = new Profile();
            profile.setFname("face" + i + ".jpg");

            if (i == 1) {
                profile.setCurrent(true);
            }
            profile.setMember(member);
            profileRepo.save(profile);
        }
    }

    @Test
    public void testFetchJoin1() {

        List<Object[]> result = memberRepo.getMemberWithProfileCount("user1");

        result.forEach(arr ->
                System.out.println(Arrays.toString(arr)));

    }

    @Test
    public void testFetchJoin2() {

        List<Object[]> result = memberRepo.getMemberWithProfile("user1");

        result.forEach(arr ->
                System.out.println(Arrays.toString(arr)));
    }
}
