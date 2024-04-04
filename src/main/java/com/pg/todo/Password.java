package com.pg.todo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "Password") // Add @Entity annotation to mark the class as a JPA entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
}
