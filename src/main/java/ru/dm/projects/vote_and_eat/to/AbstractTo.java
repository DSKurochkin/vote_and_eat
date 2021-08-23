package ru.dm.projects.vote_and_eat.to;

import ru.dm.projects.vote_and_eat.HasId;

public abstract class AbstractTo implements HasId {
    Long id;
    String name;

    public AbstractTo() {
    }

    public AbstractTo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
