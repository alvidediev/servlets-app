package ru.dediev.servlets.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_path")
    private String path;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "file", cascade = CascadeType.ALL)
    private transient Event event;

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
