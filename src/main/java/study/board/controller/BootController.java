package study.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.board.domain.dto.BoardRequest;
import study.board.domain.entity.Board;
import study.board.service.BoardService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BootController {


//    @GetMapping("/dummy")
//    public String dummy(){
//      log.info("dummy성공");
//      return "ok";
//    }
//
//    @GetMapping("/member")
//    public String getMember(@RequestParam(value = "memberKey", defaultValue = "111") String memberKey,
//                            @RequestParam(value = "age") int age){
//        log.info("memberKey : {}", memberKey);
//        log.info("age : {}", age);
//        return "ok";
//    }

    private final BoardService boardService;

    //ResponseEntity : Http응답을 나타내는 클래스이다.
    @PostMapping
    public ResponseEntity<Boolean> create(BoardRequest.CreateDTO request) throws Exception{

        //Board객체를 생성하기위함
        // userId, pwd, title, contents

         /*
            @Builder를 객체에 추가하면 빌더 클래스가 자동으로 생성되어 객체 생성을 보다 편리하게 할 수 있다.
            객체가 가질 필드들을 설정하는 메서드들을 체이닝방식으로 연결하여 객체를 생성하는 패턴이다.
         */

        if(request == null || request.getUserId() == null || request.getPwd() == null) {
            throw new RuntimeException("check value");
        }

        Board board = Board.builder()
                .title(request.getTitle())
                .pwd(request.getPwd())
                .contents(request.getContents())
                .userId(request.getUserId())
                .build();

        boolean isCreated = boardService.create(board);


        return new ResponseEntity<>(isCreated, HttpStatus.OK);
    }

    @GetMapping
    public void getBoardList(){

    }
}
