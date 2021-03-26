package com.example.demo.service;

import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.model.Board;
import com.example.demo.model.Reply;
import com.example.demo.model.User;
import com.example.demo.repository.FreeBoardRepository;
import com.example.demo.repository.FreeBoardReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
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
    public void writeReply(ReplySaveRequestDto replySaveRequestDto,int userId) {
        //부모가 있을때 즉 대댓글일때
        if(replySaveRequestDto.getReparentId()!= 0){
            //전달받은 getReparentId로 몇번째 댓글의 자식(대댓글)인지 확인
            Reply parentReply= freeBoardReplyRepository.search(replySaveRequestDto.getReparentId());

            //댓글에 대댓글을 달기 위해 기존의 데이터들의 순서를 하나씩 미룬다.
            freeBoardReplyRepository.update(parentReply.getReorder()+1);

            //update에서 미룬 위치에 공간이 하나 확보 되므로, 해당 위치에 데이터 저장
            freeBoardReplyRepository.replySave(userId,replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent()
                    ,replySaveRequestDto.getReparentId(),1,parentReply.getReorder()+1);
        }
        else{//일반댓글일때
            freeBoardReplyRepository.update(0);
            freeBoardReplyRepository.replySave(userId,replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent()
                    ,replySaveRequestDto.getReparentId(),0,0);
        }
    }

    @Transactional
    public void deleteReply(int replyId) {
        freeBoardReplyRepository.deleteById(replyId);
    }


}
