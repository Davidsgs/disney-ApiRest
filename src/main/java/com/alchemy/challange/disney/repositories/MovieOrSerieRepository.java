package com.alchemy.challange.disney.repositories;

import com.alchemy.challange.disney.models.MovieOrSerieModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MovieOrSerieRepository extends CrudRepository<MovieOrSerieModel,Long> {
    public abstract Optional<MovieOrSerieModel> findByTitle(String title);
    public abstract ArrayList<MovieOrSerieModel> findByGenre_Id(Long idGenre);
    public abstract ArrayList<MovieOrSerieModel> findByOrderByDateAsc();
    public abstract ArrayList<MovieOrSerieModel> findByOrderByDateDesc();
}
