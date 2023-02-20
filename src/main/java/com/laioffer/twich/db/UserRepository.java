package com.laioffer.twich.db;

import com.laioffer.twich.db.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastName(String lastName);

    UserEntity findByUsername(String username);

    @Modifying
    @Query("UPDATE users SET first_name = :firstName, last_name = :lastName WHERE username = :username")
    void updateNameByUsername(String username, String firstName, String lastName);
}
