package study.board.service;

import lombok.RequiredArgsConstructor;
import study.board.domain.dto.BoardRequest;
import study.board.domain.entity.Board;
import study.board.mapper.BoardMapper;
import study.board.repository.BoardRepository;

//Mybatis사용
@RequiredArgsConstructor
public class BoardServiceImpl1 implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public Boolean create(Board board) throws Exception{
        try {
            //Mybatis Mapper사용해서 insert하기
            boardMapper.create(board);

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("BoardCreateError");
        }

        return true;
    }
}
