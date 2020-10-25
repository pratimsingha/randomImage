package com.random.randomImages.model;

import javax.persistence.*;

@Entity
@Table(name = "randomlinks")
public class  RandomLinks {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "links", nullable = false)
    private String links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
