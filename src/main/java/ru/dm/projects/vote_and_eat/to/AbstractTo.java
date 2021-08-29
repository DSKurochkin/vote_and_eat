package ru.dm.projects.vote_and_eat.to;

import ru.dm.projects.vote_and_eat.HasId;

public abstract class AbstractTo implements HasId {
    Long id;

    public AbstractTo() {
    }

    public AbstractTo(Long id) {
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
