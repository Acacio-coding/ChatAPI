package com.ifsc.chatapi.repository;

import com.ifsc.chatapi.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {

    GroupModel findById(long id);
}
