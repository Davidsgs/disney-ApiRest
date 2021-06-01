package com.alchemy.challange.disney.adapters;

import lombok.Data;

import java.util.Calendar;

@Data
public class MovieOrSerieAdapter {

    private String image;
    private String title;
    private Calendar date;

    public MovieOrSerieAdapter(String image, String title, Calendar date) {
        this.image = image;
        this.title = title;
        this.date = date;
    }
}
