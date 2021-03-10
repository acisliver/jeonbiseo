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

    public Debate viewDebate(int id){
        return debateRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없다");
        });
    }

    public void writeDebate(Debate debate, User user) {
        debate.setCount(0);
        debate.setUser(user);
        debateRepository.save(debate);
    }

    public void deleteDebate(int id) {
        debateRepository.deleteById(id);
    }

    public void updateBoard(int id, Debate requestDebate) {
        Debate debate=debateRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
        });

        debate.setTitle(requestDebate.getTitle());
        debate.setContent(requestDebate.getContent());
    }


    public void writeDebateReply(ReplySaveRequestDto replySaveRequestDto) {
        //debateReplyRepository.replySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    public void deleteDebateReply(int replyId) {
        debateReplyRepository.deleteById(replyId);
    }
}
