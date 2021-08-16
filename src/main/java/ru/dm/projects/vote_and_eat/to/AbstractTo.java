package ru.dm.projects.vote_and_eat.to;

public abstract class AbstractTo {
    Long id;
    String name;

    public AbstractTo() {
    }

    public AbstractTo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

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
