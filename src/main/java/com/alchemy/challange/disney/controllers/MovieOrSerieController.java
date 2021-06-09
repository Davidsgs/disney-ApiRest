package com.alchemy.challange.disney.controllers;

import com.alchemy.challange.disney.adapters.MovieOrSerieAdapter;
import com.alchemy.challange.disney.models.MovieOrSerieModel;
import com.alchemy.challange.disney.services.MovieOrSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = {"/movies"})
public class MovieOrSerieController {

    @Autowired
    public MovieOrSerieService movieOrSerieService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ArrayList<MovieOrSerieAdapter> getMoviesOrSeries() {
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
    public MovieOrSerieModel updateMovieOrSerie(@RequestBody MovieOrSerieModel movie) {
        return movieOrSerieService.updateMovieOrSerie(movie);
    }

    @DeleteMapping(value = "/{movie_or_serie_id}")
    public String removeMovieOrSerieById(@PathVariable(name = "movie_or_serie_id") Long movieId) {
        return movieOrSerieService.deleteById(movieId)?"The movie with ID: " + movieId + " has been deleted"
                :"The movie with ID: " + movieId + "doesn't exist";
    }

    //punto 10

    @RequestMapping(value = "", method = RequestMethod.GET, params = "title")
    public MovieOrSerieModel findMovieByTitle(String title){
        return movieOrSerieService.findByTitle(title);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = "genre")
    public ArrayList<MovieOrSerieModel> findMovieByGenre(Long idGenre){
        return movieOrSerieService.filterByGenre(idGenre);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = "order")
    public ArrayList<MovieOrSerieModel> orderByDateAscOrDesc(String order){
        return movieOrSerieService.orderByDateAscOrDesc(order);
    }


}
