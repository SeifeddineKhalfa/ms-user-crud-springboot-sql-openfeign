package com.esprit.msuser.persistance.repositories;

import com.esprit.msuser.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    @Query("SELECT u FROM User u WHERE u.teamId = :teamId")
    List<User> findByTeamId(@Param("teamId") String teamId);


}
