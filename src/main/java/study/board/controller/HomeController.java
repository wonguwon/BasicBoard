package study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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

    @GetMapping("/write")
    public String boardWrite() {
        return "/boardEnrollForm.html";
    }

    @GetMapping("/boardDetail/{boardId}")
    public String boardDetail() {
        return "/boardDetail.html";
    }
}
