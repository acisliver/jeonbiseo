package com.example.demo.controller.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Debate;
import com.example.demo.service.DebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DebateApiController {

    @Autowired
    private DebateService debateService;

    @GetMapping("/api/debateHeader")
    public @ResponseBody
    Page<Debate> enterNoticeDebatePage(@PageableDefault(size=10,sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable){

        Page<Debate> debateHeader = debateService.postList(pageable);
        return debateHeader;
    }


    //글을 눌렀을 때, 해당 글을 볼 수 있도록
    //수정 버튼을 눌렀을 때 board 정보를 가지고 갈 수 있도록 함
    @GetMapping({"/api/debate/{debateId}"})
    public @ResponseBody Debate viewDebate(@PathVariable int debateId){
        Debate debate = debateService.viewDebate(debateId);
        return debate;
    }

    //토론 게시판 글쓰기 버튼 눌렀을 때
    //토큰검증 필요
    @GetMapping("/api/debate/write")
    public ResponseDto<Integer> Debatewrite(){
        //토큰검증을 하고 토큰 검증이 끝나면 ok사인을 보내 해당 주소로 보내게 한다.
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론 글쓰기를 통해 작성 한 글 저장 버튼을 눌렀을 때
    //requestbosy란, post요청 시 json을 객체로 바인딩 할 수 있게 하는 것.
    @PostMapping("/api/debate/write/save")
    public ResponseDto<Integer> debateSave(@RequestBody Debate debate,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails){
        debateService.writeDebate(debate,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    //토론 게시글 삭제
    @DeleteMapping("/api/debate/{id}")
    public ResponseDto<Integer> debateDelete(@PathVariable int id){
        debateService.deleteDebate(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/debate/{id}")
    public ResponseDto<Integer> debateUpdate(@PathVariable int id, @RequestBody Debate debate){
        debateService.updateBoard(id, debate);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론게시판 댓글달기
    @PostMapping("/api/debate/{debateId}/reply}")
    public ResponseDto<Integer> addDebateReply(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        debateService.writeDebateReply(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //토론 게시판 댓글삭제
    @DeleteMapping("/api/debate/{debatedId}/reply/{replyId}")
    public ResponseDto<Integer> replyDebateDelete(@PathVariable int replyId){
        debateService.deleteDebateReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
