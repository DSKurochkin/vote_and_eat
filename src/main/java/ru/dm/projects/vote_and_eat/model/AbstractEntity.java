package ru.dm.projects.vote_and_eat.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "name", nullable = false)
    protected String name;

    protected AbstractEntity() {
    }

    protected AbstractEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    boolean isNew() {
        return id == null;
    }

}
