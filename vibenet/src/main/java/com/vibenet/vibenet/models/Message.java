package com.vibenet.vibenet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    private String image;
    private String content;
    @ManyToOne
    private User user ;
    @JsonIgnore
    @ManyToOne
    private Chat chat;
    private LocalDateTime timeStamp;

}
