package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
@Modifying  // INSERT, DELETE, UPDATE 를 네이트브 쿼리로 작성하려면 해당 어노테이션이 필요함
@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);
    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId and touserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);
}
