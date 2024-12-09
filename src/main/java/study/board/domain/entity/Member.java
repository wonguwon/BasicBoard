package study.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "memberRoleList")
public class Member {

    @Id
    private String email;

    private String password;

    private String nickname;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    //    하이버네이트는 엔티티 필드에서 컬렉션을 초기화하는 것이 안전합니다.
    //    메서드에서 임의로 컬렉션을 생성하면 하이버네이트가 변경 추적을 제대로 하지 못할 수 있습니다.
    //    따라서 필드 레벨에서 컬렉션을 생성하면 코드도 간결하고 관리가 용이합니다.

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addMemberRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void clearMemberRole(MemberRole memberRole) {
        memberRoleList.clear();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
