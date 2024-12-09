package study.board.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.domain.entity.Board;

@Repository
@Transactional
public interface BoardRepository extends JpaRepository<Board, String> {
}
