package com.mandy.Todo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity //
@Data
public class Appclass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    @NotBlank
    @NotNull
    private String title;

    private Boolean isCompleted;

}
