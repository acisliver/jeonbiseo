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

    @Transactional
    public void writeDebate(Debate debate, User user) {
        debate.setCount(0);
        debate.setUser(user);
        debateBoardRepository.save(debate);
    }

    @Transactional
    public void deleteDebate(int id) {
        debateBoardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(int id, Debate requestDebate) {
        Debate debate= debateBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
        });

        debate.setTitle(requestDebate.getTitle());
        debate.setContent(requestDebate.getContent());
    }

    @Transactional
    public void writeDebateReply(ReplySaveRequestDto replySaveRequestDto, int userId) {
        debateReplyRepository.replySave(userId,replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    @Transactional
    public void deleteDebateReply(int replyId) {
        debateReplyRepository.deleteById(replyId);
    }

    @Transactional
    public void pressGood(int debateId) {
        debateBoardRepository.goodUp(debateId);
    }

    @Transactional
    public void pressNotGood(int debateId) {
        debateBoardRepository.notGoodUp(debateId);
    }
}
