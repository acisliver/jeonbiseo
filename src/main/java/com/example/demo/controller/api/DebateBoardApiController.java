package com.example.demo.controller.api;

import com.example.demo.Exception.OverDueDateException;
import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.DebateReplySaveRequestDto;
import com.example.demo.dto.PercentageDto;
import com.example.demo.dto.ResponseDetailDebateBoardDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Debate;
import com.example.demo.model.Statistic;
import com.example.demo.service.DebateBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DebateBoardApiController {

    private static final Logger logger = LoggerFactory.getLogger(DebateBoardApiController.class);

    @Autowired
    private DebateBoardService debateBoardService;

    @ExceptionHandler(OverDueDateException.class)
    public ResponseDto<Integer> OverDueDate(){
        //에러를 실행
        logger.error("[ERROR]토론 게시판 토론 시간 종료");
        return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(), 0);
    }


    @GetMapping("/api/debate-header")
    public @ResponseBody
    List<Debate> enterNoticeDebatePage(){
        logger.info("[INFO]게시판 페이지 진입시 토론 게시판 정보 전송");
        List<Debate> debateHeader = debateBoardService.postList();
        return debateHeader;
    }

    //글을 눌렀을 때, 해당 글을 볼 수 있도록
    //수정 버튼을 눌렀을 때 board 정보를 가지고 갈 수 있도록 함
    @GetMapping({"/api/debate/{debateId}"})
    public @ResponseBody
    ResponseDetailDebateBoardDto<Debate, PercentageDto> viewDebate(@PathVariable int debateId){
        logger.info("[INFO]토론 게시판 정보 요청");
        Debate debate = debateBoardService.viewDebate(debateId);
        PercentageDto percentageDto = debateBoardService.viewStatistic(debateId);
        System.out.println(debate.getClearTime());
        if((debate != null) && (percentageDto != null)) {
            logger.info("[INFO]토론 게시판 요청 성공");
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto, 1);
        }
        else {
            logger.error("[ERROR]토론 게시판 요청 실패");
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto, 0);
        }
    }

    //토론 글쓰기를 통해 작성 한 글 저장 버튼을 눌렀을 때
    //requestbosy란, post요청 시 json을 객체로 바인딩 할 수 있게 하는 것.
    @PostMapping("/api/debate/write/save")
    public ResponseDto<Integer> debateSave(@RequestBody Debate debate,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 작성 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.writeDebate(debate,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론 게시글 삭제
    @DeleteMapping("/api/debate/{debateId}")
    public ResponseDto<Integer> debateDelete(@PathVariable int debateId,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 삭제 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.deleteDebate(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //게시판 수정
    @PutMapping("/api/debate/{debateId}")
    public ResponseDto<Integer> debateUpdate(@PathVariable int debateId, @RequestBody Debate debate,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 수정 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.updateBoard(debateId, debate);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론게시판 댓글달기
    @PostMapping("/api/debate/{debateId}/reply")
    public ResponseDetailDebateBoardDto<Debate, PercentageDto>  addDebateReply(@RequestBody DebateReplySaveRequestDto debateReplySaveRequestDto,
                                                                          @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 댓글 작성 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.writeDebateReply (debateReplySaveRequestDto,principalDetails.getUser().getId());
        Debate debate = debateBoardService.viewDebate(debateReplySaveRequestDto.getDebateBoardId());
        PercentageDto percentageDto = debateBoardService.viewStatistic(debateReplySaveRequestDto.getDebateBoardId());
        if((debate != null) && (percentageDto != null)) {
            logger.info("[INFO]토론 게시판 댓글 작성 성공."+ "요청 유저:" +principalDetails.getUser());
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto, 1);
        }
        else {
            logger.error("[ERROR]토론 게시판 댓글 작성 성공."+ "요청 유저:" +principalDetails.getUser());
            return new ResponseDetailDebateBoardDto<Debate, PercentageDto>(debate, percentageDto, 0);
        }

    }

    //토론 게시판 댓글삭제
    @DeleteMapping("/api/debate/{debatedId}/reply/{replyId}")
    public ResponseDto<Integer> replyDebateDelete(@PathVariable int replyId,
                                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 댓글 삭제 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.deleteDebateReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //좋아요 버튼 클릭
    @PutMapping("/api/debate/{debateId}/goodNum")
    public ResponseDto<Integer> goodDebateBoard(@PathVariable int debateId,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 좋아요 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.pressGoodNum(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //싫어요 버튼 클릭
    @PutMapping("/api/debate/{debateId}/badNum")
    public ResponseDto<Integer> badDebateBoard(@PathVariable int debateId,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("[INFO]토론 게시판 싫어요 요청."+ "요청 유저:" +principalDetails.getUser());
        debateBoardService.pressBadNum(debateId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
