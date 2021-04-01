package com.example.demo.service;

import com.example.demo.dto.DebateReplySaveRequestDto;
import com.example.demo.model.Debate;
import com.example.demo.model.DebateReply;
import com.example.demo.model.Reply;
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
    public void writeDebateReply(DebateReplySaveRequestDto debateReplySaveRequestDto, int userId) {
        //부모가 있을때 즉 대댓글일때
        if(debateReplySaveRequestDto.getReparentId()!= 0){
            //전달받은 getReparentId로 몇번째 댓글의 자식(대댓글)인지 확인
            DebateReply parentDebateReply= debateReplyRepository.search(debateReplySaveRequestDto.getReparentId());

            //댓글에 대댓글을 달기 위해 기존의 데이터들의 순서를 하나씩 미룬다.
            debateReplyRepository.update(parentDebateReply.getReorder()+1);

            //update에서 미룬 위치에 공간이 하나 확보 되므로, 해당 위치에 데이터 저장

            debateReplyRepository.debateReplySave(userId,debateReplySaveRequestDto.getDebateBoardId(), debateReplySaveRequestDto.getContent()
                    ,debateReplySaveRequestDto.getReparentId(),1,parentDebateReply.getReorder()+1, debateReplySaveRequestDto.getProsAndConsAndNegative());
        }
        else{//일반댓글일때
            debateReplyRepository.update(0);
            debateReplyRepository.debateReplySave(userId, debateReplySaveRequestDto.getDebateBoardId(), debateReplySaveRequestDto.getContent()
                    ,debateReplySaveRequestDto.getReparentId(),0,0, debateReplySaveRequestDto.getProsAndConsAndNegative());
        }
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
