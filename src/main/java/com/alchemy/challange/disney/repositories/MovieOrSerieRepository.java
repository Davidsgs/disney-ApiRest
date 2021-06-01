package com.alchemy.challange.disney.repositories;

import com.alchemy.challange.disney.models.MovieOrSerieModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieOrSerieRepository extends CrudRepository<MovieOrSerieModel,Long> {
}
