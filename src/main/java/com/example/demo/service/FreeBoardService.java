package com.example.demo.service;

import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.model.Board;
import com.example.demo.model.User;
import com.example.demo.repository.FreeBoardRepository;
import com.example.demo.repository.FreeBoardReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreeBoardService {

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private FreeBoardReplyRepository freeBoardReplyRepository;

    @Transactional
    public List<Board> postList(){
        return freeBoardRepository.findAll();
    }

    @Transactional
    public Board viewBoard(int id){
        return freeBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없다");
        });
    }

    @Transactional
    public void writeBoard(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        freeBoardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(int id) {
        freeBoardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(int id, Board requestBoard) {
        Board board = freeBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
        });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
        freeBoardReplyRepository.replySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    @Transactional
    public void deleteReply(int replyId) {
        freeBoardReplyRepository.deleteById(replyId);
    }


}
