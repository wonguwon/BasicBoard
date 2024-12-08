package study.board.mapper;

import study.board.domain.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
    void create(@Param("board") Board board);
}
