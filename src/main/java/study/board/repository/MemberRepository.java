package study.board.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.board.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("select m from Member m where m.email=:email")
    Member getWithRoles(@Param("email") String email);
}
