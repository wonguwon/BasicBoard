package study.board.mapper;

import study.board.domain.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    int save(@Param("board") Board board);
    List<Board> findAll();
    Board findOne(@Param("boardId") Long id);
    int delete(@Param("boardId") Long id);
    int update(@Param("board") Board board);
}
