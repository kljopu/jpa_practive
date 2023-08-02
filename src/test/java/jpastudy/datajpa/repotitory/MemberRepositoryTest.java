package jpastudy.datajpa.repotitory;

import java.util.Optional;
import jpastudy.datajpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member member = new Member("Kyle");
        Member savedMember = memberRepository.save(member);

        Member foundMember = memberRepository.findById(member.getId()).get();

        assertThat(savedMember.getId()).isEqualTo(foundMember.getId());
        assertThat(savedMember.getUsername()).isEqualTo(foundMember.getUsername());

    }
}