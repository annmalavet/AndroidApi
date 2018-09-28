package com.marvel.malavet.marvel;

import com.marvel.malavet.marvel.Models.ComicBookObject.MarvelInitialObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APICalls {

    @GET("/v1/public/comics/{id}")
    Call<MarvelInitialObject> getComic(
            @Path("id") int comicId,
            @QueryMap HashMap<String, String> options

    );

    @GET("/v1/public/{characters}")
    Call<MarvelInitialObject> getCharacter(
            @Path("characters") String characters,
            @Query("nameStartsWith") String lady,
            @QueryMap HashMap<String, String> options
    );


}
