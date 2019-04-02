package com.example.app.Model;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIRest {
    //On définit notre interface
    //Avec une méthod getListPokemon() qui retourne un objet
    //Call<RestPokemonResponse>
    @GET("pokemon?offset=0&limit=964")
    Call<RestPokemon> getListPokemon();
}