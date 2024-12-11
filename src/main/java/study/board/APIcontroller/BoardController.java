package study.board.APIcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.board.domain.dto.BoardRequest;
import study.board.domain.dto.BoardResponse;
import study.board.domain.entity.Board;
import study.board.service.BoardService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
    REST API는 REST(Representational State Transfer)라는 아키텍처 스타일을 따르는 API입니다.
    REST는 자원을 기반으로 한 네트워크 아키텍처 설계 방식으로, 주로 HTTP를 사용하여 클라이언트와 서버 간의 데이터 통신을 지원합니다.
    REST API는 REST의 원칙에 따라 자원을 정의하고 자원에 대한 표준 HTTP 메서드(GET, POST, PUT, DELETE 등)를 사용하여 조작합니다.

    무상태성(Statelessness)
    서버는 클라이언트의 상태를 저장하지 않습니다. 각 요청은 완전한 정보를 포함하며 독립적으로 처리됩니다. 이를 통해 확장성과 유지보수성이 높아집니다.

    url에는 접근할 자원의 경로를 지정하고
    작업은 HTTP 메서드를 사용하여 표현합니다:
    GET: 자원의 조회 (읽기)
    POST: 자원의 생성
    PUT: 자원의 수정 (전체 업데이트)
    PATCH: 자원의 일부 수정
    DELETE: 자원의 삭제

    REST는 아키텍처 스타일이지 공식 표준이 아닙니다. 따라서 설계와 구현 방식이 개발자마다 다를 수 있으며,
    RESTful하지 않은 방식으로 설계되는 경우도 많습니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    //ResponseEntity : Http응답을 나타내는 클래스이다.
    @PostMapping
    public ResponseEntity<String> saveBoard(BoardRequest.CreateDTO request, MultipartFile upfile) throws Exception{
        //Board객체를 생성하기위함
        // userId, pwd, title, contents

         /*
            @Builder를 객체에 추가하면 빌더 클래스가 자동으로 생성되어 객체 생성을 보다 편리하게 할 수 있다.
            객체가 가질 필드들을 설정하는 메서드들을 체이닝방식으로 연결하여 객체를 생성하는 패턴이다.
         */

        if(request == null || request.getUserId() == null) {
            throw new RuntimeException("check value");
        }

        Board board = request.toEntity();

        if (!upfile.isEmpty()) {
            // 파일 저장 경로 생성
            File realObj = new File("C:\\workspace\\boot\\basicBoard\\src\\main\\resources\\static\\uploads", upfile.getOriginalFilename());
            upfile.transferTo(realObj);

            board.changeFileName("/uploads/" + upfile.getOriginalFilename());
        }

        int result = boardService.save(board);

        if (result > 0) {
            return new ResponseEntity<>("게시글 등록 성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("게시글 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse.SimpleDTO>> getBoardList(){

       List<Board> boardList = boardService.findAll();

       List<BoardResponse.SimpleDTO> boardArrayList = new ArrayList<>();
       for (Board board : boardList) {
           boardArrayList.add(BoardResponse.SimpleDTO.fromEntity(board));
       }

       return new ResponseEntity<>(boardArrayList, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse.DetailDTO> getBoard(@PathVariable Long boardId){
        Board board = boardService.findOne(boardId);
        BoardResponse.DetailDTO resultBoard = BoardResponse.DetailDTO.fromEntity(board);
        return new ResponseEntity<>(resultBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId){
        int result = boardService.delete(boardId);
        return new ResponseEntity<>(result + "개 게시글 삭제완료", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> updateBoard(BoardRequest.UpdateDTO request, MultipartFile upfile) throws Exception{
        log.info("request : {}", request.getOriginFile());
        log.info("upfile : {}", upfile);
        Board board = request.toEntity();

        if (upfile != null && !upfile.isEmpty()) {
            // 파일 저장 경로 생성
            File realObj = new File("C:\\workspace\\boot\\basicBoard\\src\\main\\resources\\static\\uploads", upfile.getOriginalFilename());
            upfile.transferTo(realObj);

            board.changeFileName("/uploads/" + upfile.getOriginalFilename());
        }
        log.info("upfile : {}", board.getFileName());
        Long boardId = boardService.update(board);
        return new ResponseEntity<>(boardId, HttpStatus.OK);
    }
}
