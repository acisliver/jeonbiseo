package com.example.demo.repository;

import com.example.demo.model.Application;
import com.example.demo.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> {

    @Modifying
    @Query(value = "INSERT INTO userpreference(userId, applicationId, preference) VALUE(?1, ?2, ?3)",nativeQuery = true)
    void insertPreferenceOfItem(int userId, int applicationId, int preference);

    @Modifying
    @Query(value = "Update  userpreference Set preference = preference + ?1 WHERE applicationId= ?2 and userId =?3", nativeQuery = true)
    void increacePrefernce(int plusPreference, int applicationId, int userId);

    UserPreference findByUserIdAndApplicationId(int userId,int applicationId);
}
