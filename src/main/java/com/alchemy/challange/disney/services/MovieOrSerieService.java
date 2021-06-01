package com.alchemy.challange.disney.services;

import com.alchemy.challange.disney.adapters.MovieOrSerieAdapter;
import com.alchemy.challange.disney.models.MovieOrSerieModel;
import com.alchemy.challange.disney.repositories.MovieOrSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieOrSerieService {
    @Autowired
    MovieOrSerieRepository movieOrSerieRepository;

    public List<MovieOrSerieAdapter> getMoviesOrSeries(){
        var movieOrSerieModel = (ArrayList<MovieOrSerieModel>) movieOrSerieRepository.findAll();
        return (ArrayList<MovieOrSerieAdapter>) movieOrSerieModel.stream().map(movie -> movie.toMovieAdapter()).collect(Collectors.toList());
    }

    public MovieOrSerieModel getMovieOrSerieById(Long movieId){
        Optional<MovieOrSerieModel> movie = movieOrSerieRepository.findById(movieId);
        if(movie != null && movie.isPresent()){
            return movie.get();
        }
        return null;
    }

    public MovieOrSerieModel createMovieOrSerie(MovieOrSerieModel movie){
        if(movie != null){
            movie = movieOrSerieRepository.save(movie);
            if(movie != null){
                return movie;
            }
        }
        return null;
    }

    public Boolean updateMovieOrSerie(MovieOrSerieModel movie){
        if(movie != null && movie.getId() != null){
            MovieOrSerieModel movieAux = getMovieOrSerieById(movie.getId());
            movieAux = movie;
            return true;
        }
        return false;
    }

    public boolean deleteById(Long id){
        try{
            movieOrSerieRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean removeMovieOrSerieById(Long movieId) {
        var movieAux = movieOrSerieRepository.findById(movieId);
        var bool = movieAux != null && movieAux.isPresent();
        if(bool){
            movieOrSerieRepository.deleteById(movieId);
        }
        return bool;
    }
}
