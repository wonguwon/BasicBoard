package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.entity.Board;
import study.board.mapper.BoardMapper;

import java.util.List;

//Mybatis사용
@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl1 implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public int save(Board board){
        //Mybatis Mapper사용해서 insert하기
        int result = boardMapper.save(board);

        return result;
    }

    @Override
    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    @Override
    public Board findOne(Long boardId) {
        return boardMapper.findOne(boardId);
    }

    @Override
    public int delete(Long boardId) {
        return boardMapper.delete(boardId);
    }

    @Override
    public Long update(Board board) {
        boardMapper.update(board);
        return board.getBoardId();
    }
}
