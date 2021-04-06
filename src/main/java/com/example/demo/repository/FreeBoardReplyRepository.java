package com.example.demo.repository;

import com.example.demo.model.Reply;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FreeBoardReplyRepository extends JpaRepository<Reply,Integer> {

    //insert를 할 것이기 때문에 @Modifying 필요, @Modifying는 int만 반활 할 수 있다.
    @Modifying
    @Query(value = "INSERT INTO reply(userID, boardId, content, createDate,reparent, redepth, reorder) VALUE(?1,?2,?3,now(),?4,?5,?6)",nativeQuery = true)
    int replySave(int userId, int boardId, String content,int reparent, int redepth, int reorder);

    @Modifying
    @Query(value = "Update  reply Set reorder = reorder+1 WHERE reorder >= ?1", nativeQuery = true)
    int update(int reorder);

    @Query(value = "SELECT * FROM reply WHERE id= ?1", nativeQuery = true)
    Reply search(int reparentId);

}
