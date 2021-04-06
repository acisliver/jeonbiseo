package com.example.demo.controller.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.dto.ResponseDetailBoardDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Debate;
import com.example.demo.service.DebateBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DebateBoardApiController {

    @Autowired
    private DebateBoardService debateBoardService;

    @GetMapping("/api/debate-header")
    public @ResponseBody
    List<Debate> enterNoticeDebatePage(){
        List<Debate> debateHeader = debateBoardService.postList();
        return debateHeader;
    }


    //글을 눌렀을 때, 해당 글을 볼 수 있도록
    //수정 버튼을 눌렀을 때 board 정보를 가지고 갈 수 있도록 함
    @GetMapping({"/api/debate/{debateId}"})
    public @ResponseBody ResponseDetailBoardDto<Debate> viewDebate(@PathVariable int debateId){
        Debate debate = debateBoardService.viewDebate(debateId);
        if(debate != null)
            return new ResponseDetailBoardDto<Debate>(debate,1);
        else
            return new ResponseDetailBoardDto<Debate>(debate,0);
    }

    //토론 글쓰기를 통해 작성 한 글 저장 버튼을 눌렀을 때
    //requestbosy란, post요청 시 json을 객체로 바인딩 할 수 있게 하는 것.
    @PostMapping("/api/debate/write/save")
    public ResponseDto<Integer> debateSave(@RequestBody Debate debate,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails){
        debateBoardService.writeDebate(debate,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    //토론 게시글 삭제
    @DeleteMapping("/api/debate/{debateId}")
    public ResponseDto<Integer> debateDelete(@PathVariable int debateId){
        debateBoardService.deleteDebate(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/debate/{debateId}")
    public ResponseDto<Integer> debateUpdate(@PathVariable int debateId, @RequestBody Debate debate){
        debateBoardService.updateBoard(debateId, debate);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론게시판 댓글달기
    @PostMapping("/api/debate/{debateId}/reply}")
    public ResponseDto<Integer> addDebateReply(@RequestBody ReplySaveRequestDto replySaveRequestDto,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        debateBoardService.writeDebateReply(replySaveRequestDto,principalDetails.getUser().getId());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론 게시판 댓글삭제
    @DeleteMapping("/api/debate/{debatedId}/reply/{replyId}")
    public ResponseDto<Integer> replyDebateDelete(@PathVariable int replyId){
        debateBoardService.deleteDebateReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //좋아요 버튼 클릭
    @PutMapping("/api/debate/{debateId}/good")
    public ResponseDto<Integer> goodDebateBoard(@PathVariable int debateId){
        debateBoardService.pressGood(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //싫어요 버튼 클릭
    @PutMapping("/api/debate/{debateId}/not-good")
    public ResponseDto<Integer> notGoodDebateBoard(@PathVariable int debateId){
        debateBoardService.pressNotGood(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
