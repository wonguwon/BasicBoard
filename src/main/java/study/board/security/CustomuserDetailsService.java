package study.board.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.board.domain.dto.MemberDTO;
import study.board.domain.entity.Member;
import study.board.repository.MemberRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomuserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername:{}", username);

        Member m = memberRepository.getWithRoles(username);

        if (m == null) {
            throw new UsernameNotFoundException(username);
        }

        MemberDTO memberDTO = new MemberDTO(
                m.getEmail(),
                m.getPassword(),
                m.getNickname(),
                m.getMemberRoleList()
                        .stream()
                        .map(memberRole -> memberRole.name()).collect(Collectors.toList())
        );

        return memberDTO;
    }
}
