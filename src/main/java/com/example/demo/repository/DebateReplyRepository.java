package com.example.demo.repository;

import com.example.demo.model.DebateReply;
import com.example.demo.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DebateReplyRepository extends JpaRepository<DebateReply,Integer> {

    //insert를 할 것이기 때문에 @Modifying 필요, @Modifying는 int만 반활 할 수 있다.
    @Modifying
    @Query(value = "INSERT INTO debatereply(userID, debateId, content, createDate,reparent, redepth, reorder, prosAndConsAndNegative) VALUE(?1,?2,?3,now(),?4,?5,?6,?7)",nativeQuery = true)
    int debateReplySave(int userId, int boardId, String content,int reparent, int redepth, int reorder, String prosAndConsAndNegative);

    @Modifying
    @Query(value = "Update  debatereply Set reorder = reorder+1 WHERE reorder >= ?1", nativeQuery = true)
    int update(int reorder);

    @Query(value = "SELECT * FROM debatereply WHERE id= ?1", nativeQuery = true)
    DebateReply search(int reparentId);

}
