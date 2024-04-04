package com.pg.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepo extends JpaRepository<Password, Long> {
    @Query("SELECT t.password FROM Password t WHERE t.id =1")
    String findPasswordByPassword();
}
