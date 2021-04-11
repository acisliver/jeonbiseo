package com.example.demo.service;

import com.example.demo.Exception.OverDueDateException;
import com.example.demo.dto.DebateReplySaveRequestDto;
import com.example.demo.dto.PercentageDto;
import com.example.demo.model.*;
import com.example.demo.repository.DebateReplyRepository;
import com.example.demo.repository.DebateBoardRepository;
import com.example.demo.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

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
            double pros = statistic.getPros();
            double cons = statistic.getCons();
            //double negative = statistic.getNegative();
            double sum = 0 ;

            sum = pros + cons;

            double precentagePres = (pros/sum) * 100;
            double precentageCons = (cons/sum) * 100;
            //double precentageNegative = ( negative/sum) * 100;

            PercentageDto percentageDto = new PercentageDto(precentagePres, precentageCons);
            return percentageDto;

        }
        return null;
    }

    @Transactional
    public void writeDebate(Debate debate, User user) {
        //토론 게시글 작성
        debate.setCount(0);
        debate.setUser(user);
        debate.setGoodNum(0);
        debate.setBadNum(0);
        debateBoardRepository.save(debate);

        //통계 게시글에 맞는 통계 db 생성
        //id는 1부터 시작해서 1씩 증가하므로 0보다 큰지 비교
        if(debate.getId() > 0) {
            Statistic statistic = new Statistic();
            statistic.setDebateId(debate.getId());
            statistic.setPros(0);
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
        Date creatDate= null;
        Date timezoneDate=null;
        Timestamp timestamp=null;
        int compare=0;

        //데이터 비교와 매핑을 위해 SimpleDateFormat사용
        SimpleDateFormat simpleTimeZon=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //보더를 찾아옴
        Optional<Debate>debate=debateBoardRepository.findById(debateReplySaveRequestDto.getDebateBoardId());

        //timezone 서울의 데이터를 받아옴
        TimeZone timeZone=TimeZone.getTimeZone("Asia/Seoul");
        simpleTimeZon.setTimeZone(timeZone);

        //timestamp에 서버의 creatdate날짜를 가져옴
        timestamp=debate.get().getCreateDate();

        try {
            //date에 값을 세팅
            creatDate = simpleDateFormat.parse(timestamp+"");
            creatDate.setDate(creatDate.getDate()+3);

            timezoneDate=simpleDateFormat.parse(simpleTimeZon.format(new Date())+"");

            //timezone과 create데이터를 비교 후 반환 -1보다 크면 기간이 지나지 않음
            compare=timezoneDate.compareTo(creatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //비교값이 최종날을 넘으면 Exception을 던져서 사용을 중단시킴
        if(-1<compare){
            throw new OverDueDateException("날이 지났습니다.");
        }

        //부모가 있을때 즉 대댓글일때
        if(debateReplySaveRequestDto.getReparentId()!= 0){
            //전달받은 getReparentId로 몇번째 댓글의 자식(대댓글)인지 확인
            DebateReply parentDebateReply= debateReplyRepository.search(debateReplySaveRequestDto.getReparentId());

            //댓글에 대댓글을 달기 위해 기존의 데이터들의 순서를 하나씩 미룬다.
            debateReplyRepository.update(parentDebateReply.getReorder()+1);

            //update에서 미룬 위치에 공간이 하나 확보 되므로, 해당 위치에 데이터 저장
            debateReplyRepository.debateReplySave(userId,debateReplySaveRequestDto.getDebateBoardId(), debateReplySaveRequestDto.getContent()
                    ,debateReplySaveRequestDto.getReparentId(),1,parentDebateReply.getReorder()+1, debateReplySaveRequestDto.getProsAndCons());

        }
        else{//일반댓글일때
            debateReplyRepository.update(0);

            debateReplyRepository.debateReplySave(userId, debateReplySaveRequestDto.getDebateBoardId(), debateReplySaveRequestDto.getContent()
                    ,debateReplySaveRequestDto.getReparentId(),0,0, debateReplySaveRequestDto.getProsAndCons());
        }

        //게시물 찬성, 반대, 반박(부정)
        //id가 null이면 변수에 담을 수 없어 오류가 발생. 때문에 if 안에 변수 선언
        if(debateReplySaveRequestDto.getDebateBoardId() > 0){
            String pcn = debateReplySaveRequestDto.getProsAndCons();
            int id = debateReplySaveRequestDto.getDebateBoardId();
            if(pcn.equals("Pros") || pcn.equals("pros"))
                statisticRepository.increasePros(id);
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
    public void pressGoodNum(int debateId) {
        debateBoardRepository.goodNum(debateId);
    }

    @Transactional
    public void pressBadNum(int debateId) {
        debateBoardRepository.badNum(debateId);
    }

}
