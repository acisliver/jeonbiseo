package com.example.demo.controller.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Board;
import com.example.demo.model.Debate;
import com.example.demo.service.BoardService;
import com.example.demo.service.DebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;


    @GetMapping("/api/boardHeader")
    public @ResponseBody
    Page<Board> enterNoticeBoardPage(@PageableDefault(size=10,sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> boardHearder = boardService.postList(pageable);
        return boardHearder;
    }

    //자유 게시판 글쓰기 버튼 눌렀을 때
    //토큰검증 필요
    @GetMapping("/api/board/write")
    public ResponseDto<Integer> boardWrite(){
        //토큰검증을 하고 토큰 검증이 끝나면 ok사인을 보내 해당 주소로 보내게 한다.
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //자유게시판 글쓰기를 통해 작성 한 글 저장 버튼을 눌렀을 때
    //requestbosy란, post요청 시 json을 객체로 바인딩 할 수 있게 하는 것.
    @PostMapping("/api/board/write/save")
    public ResponseDto<Integer> boardSave(@RequestBody Board board,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.writeBoard(board,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //자유 게시글 삭제
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> boardDelete(@PathVariable int id){
        boardService.deleteBoard(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    //업데이트
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> boardUpdate(@PathVariable int id, @RequestBody Board board){
        boardService.updateBoard(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    //자유게시판 댓글달기
    @PostMapping("/api/board/{boardId}/reply}")
    public ResponseDto<Integer> addReply(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.writeReply(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //자유 게시판 댓글삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        boardService.deleteReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
