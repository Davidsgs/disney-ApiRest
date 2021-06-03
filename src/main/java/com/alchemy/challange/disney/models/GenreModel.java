package com.alchemy.challange.disney.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "genre")
public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String name;
    private String image;

    @OneToMany(targetEntity = MovieOrSerieModel.class, mappedBy = "genre",cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<MovieOrSerieModel> associatedMovies = new HashSet<>();

}
