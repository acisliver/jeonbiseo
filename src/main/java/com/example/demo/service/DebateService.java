package com.example.demo.service;

import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.model.Board;
import com.example.demo.model.Debate;
import com.example.demo.model.DebateReply;
import com.example.demo.model.User;
import com.example.demo.repository.DebateReplyRepository;
import com.example.demo.repository.DebateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DebateService {
    @Autowired
    private DebateRepository debateRepository;
    @Autowired
    private DebateReplyRepository debateReplyRepository;

    @Transactional
    public Page<Debate> postList(Pageable pageable){
        return debateRepository.findAll(pageable);
    }

    public Debate viewBoard(int id){
        return debateRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없다");
        });
    }

    public void writeBoard(Debate debate, User user) {
        debate.setCount(0);
        debate.setUser(user);
        debateRepository.save(debate);
    }

    public void deleteBoard(int id) {
        debateRepository.deleteById(id);
    }

    public void updateBoard(int id, Board requestBoard) {
        Debate board=debateRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
        });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
        debateReplyRepository.replySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    public void deleteReply(int replyId) {
        debateReplyRepository.deleteById(replyId);
    }
}
