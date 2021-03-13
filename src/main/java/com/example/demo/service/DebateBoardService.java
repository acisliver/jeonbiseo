package com.example.demo.service;

import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.model.Debate;
import com.example.demo.model.User;
import com.example.demo.repository.DebateReplyRepository;
import com.example.demo.repository.DebateBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DebateBoardService {
    @Autowired
    private DebateBoardRepository debateBoardRepository;
    @Autowired
    private DebateReplyRepository debateReplyRepository;

    @Transactional
    public List<Debate> postList(){
        return debateBoardRepository.findAll();
    }

    public Debate viewDebate(int id){
        return debateBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없다");
        });
    }

    public void writeDebate(Debate debate, User user) {
        debate.setCount(0);
        debate.setUser(user);
        debateBoardRepository.save(debate);
    }

    public void deleteDebate(int id) {
        debateBoardRepository.deleteById(id);
    }

    public void updateBoard(int id, Debate requestDebate) {
        Debate debate= debateBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
        });

        debate.setTitle(requestDebate.getTitle());
        debate.setContent(requestDebate.getContent());
    }


    public void writeDebateReply(ReplySaveRequestDto replySaveRequestDto) {
        debateReplyRepository.replySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    public void deleteDebateReply(int replyId) {
        debateReplyRepository.deleteById(replyId);
    }
}