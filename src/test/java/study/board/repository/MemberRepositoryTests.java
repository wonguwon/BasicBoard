package study.board.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import study.board.domain.entity.Member;
import study.board.domain.entity.MemberRole;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Rollback(value = false)
    public void testInsertMember(){
        for (int i = 0; i < 10 ; i++) {
            Member member = Member.builder()
                    .email("user"+i+"@aaa.com")
                    .password(passwordEncoder.encode("1111"))
                    .nickname("USER"+i)
                    .build();
            member.addMemberRole(MemberRole.USER);
            if(i >= 5)
                member.addMemberRole(MemberRole.MANAGER);
            if(i >=8)
                member.addMemberRole(MemberRole.ADMIN);
            memberRepository.save(member);
        }
    }

    @Test
    public void testRead() {
        String email = "user9@aaa.com";
        Member member = memberRepository.getWithRoles(email);
        log.info("-----------------");
        log.info(member);
    }
}