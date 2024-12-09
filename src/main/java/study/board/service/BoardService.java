package study.board.service;


import study.board.domain.entity.Board;

public interface BoardService {
    Boolean create(Board board) throws Exception;
}
