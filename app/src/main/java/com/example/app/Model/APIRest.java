package com.example.app.Model;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIRest {
    //On définit notre interface
    //Avec une méthod getListPokemon() qui retourne un objet
    //Call<RestPokemonResponse>
    @GET("pokemon")
    Call<RestPokemon> getListPokemon();

    @GET("abilities")
    Call<RestPokemon> getListAbilities();
}