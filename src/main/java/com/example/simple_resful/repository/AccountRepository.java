package com.example.simple_resful.repository;

import com.example.simple_resful.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

//    @Query("select a from Account a where a.username = :username")
    Account findByUsername(@Param("username")String username);
}
