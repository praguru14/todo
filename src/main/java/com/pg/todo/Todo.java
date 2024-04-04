package com.pg.todo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity // Add @Entity annotation to mark the class as a JPA entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Add an id field and annotate it with @Id and @GeneratedValue

    private String taskName;
}
