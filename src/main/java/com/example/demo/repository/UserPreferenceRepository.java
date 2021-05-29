package com.example.demo.repository;

import com.example.demo.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> {

    @Modifying
    @Query(value = "INSERT INTO userpreference(userId, applicationId, preference) VALUE(?1, ?2, ?3)",nativeQuery = true)
    void insertPreferenceOfItem(int userId, int applicationId, double preference);
}
