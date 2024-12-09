package study.board.domain.entity;

//ORM : 어플리케이션의 객체를 RDB 테이블애 자동으로 영속성을 갖게 해주는 것
//JPA : java에서 ORM을 위한 표준 인터페이스및 규약을 정의한 API다.
//Hibernate : Java언어로 작성된 객체 관계 매핑(Object-Relational Mapping) 프레임워크
// sql쿼리를 직접 작성하지 않고도 데이터베이스 연산을 수행할 수 있도록 도와줌


//@Data : getter, setter, toString, equals, hashCode를 포괄한다.
//@Entity : 해당 클래스가 데이터베이스의 테이블과 매핑되는 엔티티클래스임을 나타낸다. -> 테이블로 변환된다.
//@Table : 데이터베이스의 특정 테이블과 매핑되도록


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class Board {

    private Long boardId;

    private String title;

    private String contents;

    private String userId;

    private String fileName;

    private String pwd;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


//    private Board(){}
//
//    public static Builder bulider(){
//        return new Builder();
//    }
//
//    public static class Builder{
//        private Board board = new Board();
//
//        public Builder setBoardId(String userid){
//            this.board.setUserId(userid);
//            return this;
//        }
//
//        public Builder setBoardPwd(String pwd){
//            this.board.setPwd(pwd);
//            return this;
//        }
//
//        public Board build(){
//            return board;
//        }
//    }
}
