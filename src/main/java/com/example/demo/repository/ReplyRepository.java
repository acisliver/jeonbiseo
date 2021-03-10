package com.example.demo.repository;

import com.example.demo.dto.ReplySaveRequestDto;
import com.example.demo.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    //insert를 할 것이기 때문에 @Modifying 필요, @Modifying는 int만 반활 할 수 있다.
    @Modifying
    @Query(value = "INSERT INTO reply(userID, boardId, content, createDate,reparent, redepth, reoder) VALUE(?1,?2,?3,now(),?4,?5,?6)",nativeQuery = true)
    int replySave(int userId, int boardId, String content, int reparent, int redepth, int reoder);

    @Query(value = "Update  reply Set reorder=reorder+1 WHERE reorder>=?1", nativeQuery = true)
    int update(int reorder);

    @Query(value = "SELECT * FROM reply WHERE reparent= ?1", nativeQuery = true)
    ReplySaveRequestDto search(int reparentId);
}
