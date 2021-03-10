package com.example.demo.service;

import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.model.Board;
import com.example.demo.model.Debate;
import com.example.demo.model.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.DebateReplyRepository;
import com.example.demo.repository.DebateRepository;
import com.example.demo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public Page<Board> postList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board viewBoard(int id){
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없다");
        });
    }

    public void writeBoard(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    public void updateBoard(int id, Board requestBoard) {
        Board board=boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
        });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }


    public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
        if(replySaveRequestDto.getReparentId()!=0){//부모가 있을때 즉 대댓글일때
            ReplySaveRequestDto reparentData= replyRepository.search(replySaveRequestDto.getReparentId());
            replyRepository.update(reparentData.getReorder());
            replyRepository.replySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent()
                    ,replySaveRequestDto.getReparentId(),1,reparentData.getReorder()+1);
        }
        else{//일반댓글일때
            replyRepository.update(0);
            replyRepository.replySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent()
                    ,replySaveRequestDto.getReparentId(),0,0);
        }
    }


    public void deleteReply(int replyId) {
        replyRepository.deleteById(replyId);
    }


}
