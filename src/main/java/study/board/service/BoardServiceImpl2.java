package study.board.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.dto.BoardRequest;
import study.board.domain.entity.Board;
import study.board.mapper.BoardMapper;
import study.board.repository.BoardRepository;

//JPA사용
@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl2 implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Boolean create(Board board) throws Exception{

        try {
            boardRepository.save(board);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("BoardCreateError");
        }

        return true;
    }
}
