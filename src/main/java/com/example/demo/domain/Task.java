package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String responsible;
    private String status;

    public Task(String text, String responsible, String status) {
        this.text = text;
        this.responsible = responsible;
        this.status = status;
    }

    public Task() {
    }
}