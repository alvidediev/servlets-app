package ru.dediev.servlets.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_file_id")
    private FileEntity file;

    @ManyToOne(  cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private User user;
}
