package com.example.demo.controller.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.DebateReplySaveRequestDto;
import com.example.demo.dto.PercentageDto;
import com.example.demo.dto.ResponseDetailDebateBoardDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Debate;
import com.example.demo.model.Statistic;
import com.example.demo.service.DebateBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public @ResponseBody
    ResponseDetailDebateBoardDto<Debate, PercentageDto> viewDebate(@PathVariable int debateId){
        Debate debate = debateBoardService.viewDebate(debateId);
        PercentageDto percentageDto = debateBoardService.viewStatistic(debateId);
        if((debate != null) && (percentageDto != null))
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto,1);
        else
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto,0);
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

    //게시판 수정
    @PutMapping("/api/debate/{debateId}")
    public ResponseDto<Integer> debateUpdate(@PathVariable int debateId, @RequestBody Debate debate){
        debateBoardService.updateBoard(debateId, debate);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론게시판 댓글달기
    @PostMapping("/api/debate/{debateId}/reply")
    public ResponseDetailDebateBoardDto<Debate, PercentageDto> addDebateReply(@RequestBody DebateReplySaveRequestDto debateReplySaveRequestDto,
                                                                          @AuthenticationPrincipal PrincipalDetails principalDetails){
        debateBoardService.writeDebateReply(debateReplySaveRequestDto,principalDetails.getUser().getId());
        Debate debate = debateBoardService.viewDebate(debateReplySaveRequestDto.getDebateBoardId());
        PercentageDto percentageDto = debateBoardService.viewStatistic(debateReplySaveRequestDto.getDebateBoardId());
        if((debate != null) && (percentageDto != null))
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto,1);
        else
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto,0);

    }

    //토론 게시판 댓글삭제
    @DeleteMapping("/api/debate/{debatedId}/reply/{replyId}")
    public ResponseDto<Integer> replyDebateDelete(@PathVariable int replyId){
        debateBoardService.deleteDebateReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //좋아요 버튼 클릭
    @PutMapping("/api/debate/{debateId}/goodNum")
    public ResponseDto<Integer> goodDebateBoard(@PathVariable int debateId){
        debateBoardService.pressGoodNum(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //싫어요 버튼 클릭
    @PutMapping("/api/debate/{debateId}/badNum")
    public ResponseDto<Integer> badDebateBoard(@PathVariable int debateId){
        debateBoardService.pressBadNum(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
