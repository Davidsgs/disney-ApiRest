package com.alchemy.challange.disney.services;
import com.alchemy.challange.disney.adapters.MovieOrSerieAdapter;
import com.alchemy.challange.disney.models.MovieOrSerieModel;
import com.alchemy.challange.disney.repositories.MovieOrSerieRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieOrSerieService {
    @Autowired
    MovieOrSerieRepository movieOrSerieRepository;

    public ArrayList<MovieOrSerieAdapter> getMoviesOrSeries(){
        var movieOrSerieModel = (ArrayList<MovieOrSerieModel>) movieOrSerieRepository.findAll();
        return (ArrayList<MovieOrSerieAdapter>) movieOrSerieModel
                .stream()
                .map(MovieOrSerieAdapter::adaptMovie)
                .collect(Collectors.toList());
    }

    public MovieOrSerieModel getMovieOrSerieById(Long movieId){
        Optional<MovieOrSerieModel> movie = movieOrSerieRepository.findById(movieId);
        return movie.orElse(null);
    }

    public MovieOrSerieModel createMovieOrSerie(MovieOrSerieModel movie){
        if(movie != null){
            movie = movieOrSerieRepository.save(movie);
            return movie;
        }
        return null;
    }

    public Boolean updateMovieOrSerie(MovieOrSerieModel movie){
        if(movie != null && movie.getId() != null){
            MovieOrSerieModel movieAux = getMovieOrSerieById(movie.getId());

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
        var bool = movieAux.isPresent();
        if(bool){
            movieOrSerieRepository.deleteById(movieId);
        }
        return bool;
    }

    //Punto 10

    public ArrayList<MovieOrSerieModel> findByTitle(String title){
        return movieOrSerieRepository.findByTitle(title);
    }

    public ArrayList<MovieOrSerieModel> findByGenre(Long idGenre){
        return movieOrSerieRepository.findByGenre_Id(idGenre);
    }

    @SneakyThrows
    public ArrayList<MovieOrSerieModel> orderByAscOrDesc(String ascOrDesc) {
        var aux = new ArrayList<MovieOrSerieModel>();
        if( ascOrDesc.equalsIgnoreCase("ASC")){
            aux = movieOrSerieRepository.findByOrderByIdAsc();
        }else if(ascOrDesc.equalsIgnoreCase("DESC")){
            aux = movieOrSerieRepository.findByOrderByIdDesc();
        }else{
            throw new Exception("Bad Argument: Only you can order by ASC or DESC.");
        }
        return aux;
    }

}
