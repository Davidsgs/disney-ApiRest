package com.alchemy.challange.disney.adapters;

import lombok.Data;

@Data
public class CharacterAdapter {

    private String name;
    private String image;

    public CharacterAdapter(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
