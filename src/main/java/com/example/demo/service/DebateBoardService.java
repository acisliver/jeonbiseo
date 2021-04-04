package com.example.demo.service;

import com.example.demo.dto.DebateReplySaveRequestDto;
import com.example.demo.dto.PercentageDto;
import com.example.demo.model.*;
import com.example.demo.repository.DebateReplyRepository;
import com.example.demo.repository.DebateBoardRepository;
import com.example.demo.repository.StatisticRepository;
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
    @Autowired
    private StatisticRepository statisticRepository;

    @Transactional
    public List<Debate> postList(){
        return debateBoardRepository.findAll();
    }

    @Transactional
    public Debate viewDebate(int id){
        return debateBoardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 글을 찾을 수 없다");
        });
    }

    @Transactional
    public PercentageDto viewStatistic(int debateId) {
        Statistic statistic = statisticRepository.search(debateId);
        if(statistic != null){
            double pres = statistic.getPres();
            double cons = statistic.getCons();
            double negative = statistic.getNegative();
            double sum = 0 ;

            sum = pres + cons + negative;

            double precentagePres = (pres/sum) * 100;
            double precentageCons = (cons/sum) * 100;
            double precentageNegative = ( negative/sum) * 100;

            PercentageDto percentageDto = new PercentageDto(precentagePres, precentageCons, precentageNegative);
            return percentageDto;

        }
        return null;
    }

    @Transactional
    public void writeDebate(Debate debate, User user) {
        //토론 게시글 작성
        debate.setCount(0);
        debate.setUser(user);
        debate.setGood(0);
        debate.setNotGood(0);
        debateBoardRepository.save(debate);

        //객체는 call by reference이기 때문에 db에 생성된 id값을 가져 올 수 있다.
        System.out.println("sout de:"+debate.getId());

        //통계 게시글에 맞는 통계 db 생성
        //id는 1부터 시작해서 1씩 증가하므로 0보다 큰지 비교
        if(debate.getId() > 0) {
            Statistic statistic = new Statistic();
            statistic.setDebateId(debate.getId());
            statistic.setPres(0);
            statistic.setCons(0);
            statistic.setNegative(0);
            statisticRepository.save(statistic);
        }

    }

    @Transactional
    public void deleteDebate(int id) {
        debateBoardRepository.deleteById(id);
        statisticRepository.deleteStatistic(id);
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

        //게시물 찬성, 반대, 반박(부정)
        //id가 null이면 변수에 담을 수 없어 오류가 발생. 때문에 if 안에 변수 선언
        if(debateReplySaveRequestDto.getDebateBoardId() > 0){
            String pcn = debateReplySaveRequestDto.getProsAndConsAndNegative();
            int id = debateReplySaveRequestDto.getDebateBoardId();
            if(pcn.equals("Pres") || pcn.equals("pres"))
                statisticRepository.increasePres(id);
            else if(pcn.equals("Cons") || pcn.equals("cons"))
                statisticRepository.increaseCons(id);
            else if(pcn.equals("Negative") || pcn.equals("negative"))
                statisticRepository.increaseNegative(id);
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
