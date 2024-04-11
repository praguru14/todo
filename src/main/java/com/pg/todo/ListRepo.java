package com.pg.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepo extends JpaRepository<ListModel, Long> {
}
