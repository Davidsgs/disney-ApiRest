package com.alchemy.challange.disney.services;
import com.alchemy.challange.disney.adapters.MovieOrSerieAdapter;
import com.alchemy.challange.disney.models.MovieOrSerieModel;
import com.alchemy.challange.disney.repositories.MovieOrSerieRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class MovieOrSerieService {
    @Autowired
    MovieOrSerieRepository movieOrSerieRepository;

    public ArrayList<MovieOrSerieModel> findAll(){
        return (ArrayList<MovieOrSerieModel>) movieOrSerieRepository.findAll();
    }

    public ArrayList<MovieOrSerieAdapter> getMoviesOrSeries(){
        var movieOrSerieModel = (ArrayList<MovieOrSerieModel>) movieOrSerieRepository.findAll();
        return (ArrayList<MovieOrSerieAdapter>) movieOrSerieModel
                .stream()
                .map(MovieOrSerieAdapter::adaptMovie)
                .collect(Collectors.toList());
    }

    public MovieOrSerieModel getMovieOrSerieById(Long movieId){
        return movieOrSerieRepository.findById(movieId).orElseThrow(
                () -> new NullPointerException("There is no movie or serie with the id : " + movieId));
    }

    public MovieOrSerieModel createMovieOrSerie(MovieOrSerieModel movie){
        if(movie != null){
            movie = movieOrSerieRepository.save(movie);
        }
        return movie;
    }

    @Transactional
    public MovieOrSerieModel updateMovieOrSerie(MovieOrSerieModel movie){
        var auxMovie =  getMovieOrSerieById(movie.getId());
        if(movie.getTitle() != null){ auxMovie.setTitle(movie.getTitle());}
        if(movie.getImage() != null) {auxMovie.setImage(movie.getImage());}
        if(movie.getDate() != null) {auxMovie.setDate(movie.getDate());}
        if(movie.getScore() > 0 && movie.getScore() < 6) {auxMovie.setScore(movie.getScore());}
        if(movie.getGenre() != null){auxMovie.setGenre(movie.getGenre());}
        if(!movie.getAssociatedCharacters().isEmpty()){auxMovie.setAssociatedCharacters(movie.getAssociatedCharacters());}
        return auxMovie;
    }

    public boolean deleteById(Long id){
        try{
            movieOrSerieRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Punto 10

    public MovieOrSerieModel findByTitle(String title){
        return movieOrSerieRepository.findByTitle(title).orElseThrow(
                () -> new IllegalStateException("There is no movie or serie with the title : " + title)
        );
    }

    public ArrayList<MovieOrSerieModel> filterByGenre(Long idGenre){
        return movieOrSerieRepository.findByGenre_Id(idGenre);
    }

    @SneakyThrows
    public ArrayList<MovieOrSerieModel> orderByDateAscOrDesc(String ascOrDesc) {
        var aux = new ArrayList<MovieOrSerieModel>();
        if( ascOrDesc.equalsIgnoreCase("ASC")){
            aux = movieOrSerieRepository.findByOrderByDateAsc();
        }else if(ascOrDesc.equalsIgnoreCase("DESC")){
            aux = movieOrSerieRepository.findByOrderByDateDesc();
        }else{
            throw new IllegalArgumentException("Only you can order by ASC or DESC.");
        }
        return aux;
    }

}
