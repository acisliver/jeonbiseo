package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.service.BoardService;
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

    //게시판 페이지에 들어왔을 때, 페이징을 사용하여 한번에 10가지 게시글만 볼 수 있도록 함.
    @GetMapping("/noticeBoardPage")
    public @ResponseBody Page<Board> enterNoticeBoardPage(@PageableDefault(size=10,sort = "id",
                                             direction = Sort.Direction.DESC)Pageable pageable){
        Page<Board> pageBoard = boardService.postList(pageable);
        return pageBoard;
    }

    //글을 눌렀을 때, 해당 글을 볼 수 있도록
    //수정 버튼을 눌렀을 때 board 정보를 가지고 갈 수 있도록 함
    @GetMapping({"board/{id}","board/{id}/updateForm"})
    public @ResponseBody Board viewBoard(@PathVariable int id){
        Board board = boardService.viewBoard(id);
        return board;
    }

}
