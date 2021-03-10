package com.example.demo.controller;

import com.example.demo.config.BoardReturn;
import com.example.demo.model.Board;
import com.example.demo.model.Debate;
import com.example.demo.model.DebateReply;
import com.example.demo.service.BoardService;
import com.example.demo.service.DebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private DebateService debateService;

    @GetMapping("/board")
    public @ResponseBody Page<Board> board(){
        return null;
    }

    @GetMapping("/api/boardHeader")
    public @ResponseBody Page<Board> enterNoticeBoardPage(@PageableDefault(size=10,sort = "id",
            direction = Sort.Direction.DESC)Pageable pageable){

        Page<Board> boardHearder = boardService.postList(pageable);
        return boardHearder;
    }
    @GetMapping("/api/debateHeader")
    public @ResponseBody Page<Debate> enterNoticeDebatePage(@PageableDefault(size=10,sort = "id",
            direction = Sort.Direction.DESC)Pageable pageable){

        Page<Debate> debateHeader = debateService.postList(pageable);
        return debateHeader;
    }

    //글을 눌렀을 때, 해당 글을 볼 수 있도록
    //수정 버튼을 눌렀을 때 board 정보를 가지고 갈 수 있도록 함
    @GetMapping({"board/{boardId}","board/{boardId}/updateForm"})
    public @ResponseBody Board viewBoard(@PathVariable int boardId){
        Board board = boardService.viewBoard(boardId);
        return board;
    }

}
