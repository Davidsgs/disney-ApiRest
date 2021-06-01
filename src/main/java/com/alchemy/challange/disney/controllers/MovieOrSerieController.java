package com.alchemy.challange.disney.controllers;

import com.alchemy.challange.disney.adapters.MovieOrSerieAdapter;
import com.alchemy.challange.disney.models.MovieOrSerieModel;
import com.alchemy.challange.disney.services.MovieOrSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/movies"})
public class MovieOrSerieController {

    @Autowired
    public MovieOrSerieService movieOrSerieService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<MovieOrSerieAdapter> getMoviesOrSeries() {
        return movieOrSerieService.getMoviesOrSeries();
    }

    @GetMapping(value = "/{movie_or_serie_id}")
    public MovieOrSerieModel getMovieOrSerie(@PathVariable(name = "movie_or_serie_id") Long movieId) {
        return movieOrSerieService.getMovieOrSerieById(movieId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public MovieOrSerieModel createMovieOrSerie(@RequestBody MovieOrSerieModel movie) {
        return movieOrSerieService.createMovieOrSerie(movie);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Boolean updateMovieOrSerie(@RequestBody MovieOrSerieModel movie) {
        return movieOrSerieService.updateMovieOrSerie(movie);
    }

    @DeleteMapping(value = "/{movie_or_serie_id}")
    public Boolean removeMovieOrSerieById(@PathVariable(name = "movie_or_serie_id") Long movieId) {
        return movieOrSerieService.removeMovieOrSerieById(movieId);
    }


}
