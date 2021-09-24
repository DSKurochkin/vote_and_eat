package ru.dm.projects.vote_and_eat.model;

import io.swagger.annotations.ApiModelProperty;
import ru.dm.projects.vote_and_eat.HasId;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity implements HasId {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "the unique id of the entity")
    protected Long id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
