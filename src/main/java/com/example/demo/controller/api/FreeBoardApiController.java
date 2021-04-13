package com.example.demo.controller.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.dto.ResponseGenericDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Board;
import com.example.demo.service.FreeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FreeBoardApiController {

    @Autowired
    private FreeBoardService freeBoardService;

    @GetMapping("/api/free-header")
    public @ResponseBody
    List<Board> enterNoticeBoardPage(){
        List<Board> freeHearder = freeBoardService.postList();
        return freeHearder;
    }

    //글을 눌렀을 때, 해당 글을 볼 수 있도록
    //수정 버튼을 눌렀을 때 board 정보를 가지고 갈 수 있도록 함
    @GetMapping({"/api/free-board/{freeId}"})
    public @ResponseBody
    ResponseGenericDto<Board> viewBoard(@PathVariable int freeId){
        Board board = freeBoardService.viewBoard(freeId);
        if(board != null)
            return new ResponseGenericDto<Board>(board,1);
        else
            return new ResponseGenericDto<Board>(board,0);
    }

    //자유게시판 글쓰기를 통해 작성 한 글 저장 버튼을 눌렀을 때
    //requestbosy란, post요청 시 json을 객체로 바인딩 할 수 있게 하는 것.
    @PostMapping("/api/free-board/write/save")
    public ResponseDto<Integer> boardSave(@RequestBody Board board,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails){
        freeBoardService.writeBoard(board,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //자유 게시글 삭제
    @DeleteMapping("/api/free-board/{freeId}")
    public ResponseDto<Integer> boardDelete(@PathVariable int freeId){
        freeBoardService.deleteBoard(freeId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //업데이트
    @PutMapping("/api/free-board/{freeId}")
    public ResponseDto<Integer> boardUpdate(@PathVariable int freeId, @RequestBody Board board){
        freeBoardService.updateBoard(freeId, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //자유게시판 댓글달기
    @PostMapping("/api/free-board/{freeId}/reply")
    public ResponseGenericDto<Board> addReply(@RequestBody ReplySaveRequestDto replySaveRequestDto,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        freeBoardService.writeReply(replySaveRequestDto, principalDetails.getUser().getId());

        Board board = freeBoardService.viewBoard(replySaveRequestDto.getBoardId());
        if(board != null)
            return new ResponseGenericDto<Board>(board,1);
        else
            return new ResponseGenericDto<Board>(board,0);

    }

    //자유 게시판 댓글삭제
    @DeleteMapping("/api/free-board/{freeId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        freeBoardService.deleteReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //좋아요 버튼 클릭
    @PutMapping("/api/free-board/{boardId}/goodNum")
    public ResponseDto<Integer> goodFreeBoard(@PathVariable int boardId){

        freeBoardService.pressGoodNum(boardId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //싫어요 버튼 클릭
    @PutMapping("/api/free-board/{boardId}/badNum")
    public ResponseDto<Integer> badFreeboard(@PathVariable int boardId){
        freeBoardService.pressBadNum(boardId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
