package ru.dediev.servlets.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "user_name")
    private  String userName;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Event> events;
}
