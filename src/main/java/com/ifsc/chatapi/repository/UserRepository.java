package com.ifsc.chatapi.repository;

import com.ifsc.chatapi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByUsername(String username);
    UserModel findByUsername(String username);
}
