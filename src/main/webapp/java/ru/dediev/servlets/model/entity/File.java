package ru.dediev.servlets.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "file")
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_path")
    private String filePath;


    @OneToOne(mappedBy = "file")
    @ToString.Exclude
    private Event event;
}
