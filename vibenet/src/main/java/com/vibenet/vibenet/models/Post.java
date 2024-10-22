package com.vibenet.vibenet.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String caption;
    private String message;
    private String image;
    private String video;
    @OneToMany
    private List<User>liked=new ArrayList<>();
    @ManyToOne
    private  User user;
    @OneToMany
    private List<Comment>comments= new ArrayList<>();
    private LocalDateTime createdAt;


}
