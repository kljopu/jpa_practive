package jpastudy.datajpa.repotitory;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import jpastudy.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberJPARepositoryTest {

    @Autowired
    MemberJPARepository memberJPARepository;

    @Test
    public void testMember() {
        Member member = new Member("Kyle");
        Member savedMember = memberJPARepository.save(member);

        Member foundMember = memberJPARepository.find(member.getId());

        assertThat(foundMember.getId()).isEqualTo(savedMember.getId());
        assertThat(foundMember.getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberJPARepository.save(member1);
        memberJPARepository.save(member2);

        //단건 조회 검증
        Member foundMember1 = memberJPARepository.findById(member1.getId()).get();
        Member foundMember2 = memberJPARepository.findById(member1.getId()).get();

        assertThat(foundMember1).isEqualTo(foundMember2);
        assertThat(foundMember2).isEqualTo(foundMember1);

        //리스트 조회 검증
        List<Member> all = memberJPARepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberJPARepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberJPARepository.delete(member1);
        memberJPARepository.delete(member2);

        long deletedCount = memberJPARepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}