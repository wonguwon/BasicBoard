package study.board.domain.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberDTO extends User {
    private String email;
    private String password;
    private String nickname;

    private List<String> roleNames = new ArrayList<>();

    public MemberDTO( String email, String password, String nickname,
                      List<String> roleNames) {
        super(email,password, roleNames.stream().map(str ->
                new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.roleNames = roleNames;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("pw",password);
        dataMap.put("nickname", nickname);
        dataMap.put("roleNames", roleNames);
        return dataMap;
    }
}
