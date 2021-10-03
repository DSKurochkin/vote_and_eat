package ru.dm.projects.vote_and_eat.model;

import io.swagger.annotations.ApiModelProperty;
import ru.dm.projects.vote_and_eat.HasId;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity implements HasId {

    public static final int START_SEQ = 1000;
    @Id
    @SequenceGenerator(name = "GENERAL_SEQ", sequenceName = "GENERAL_SEQ", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERAL_SEQ")
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
