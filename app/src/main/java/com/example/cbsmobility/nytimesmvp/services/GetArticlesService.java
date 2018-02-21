package com.example.cbsmobility.nytimesmvp.services;

import com.example.cbsmobility.nytimesmvp.constants.ApiConstants;
import com.example.cbsmobility.nytimesmvp.model.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Vipin Vasu on 21/02/18.
 */


public interface GetArticlesService {

    @GET(ApiConstants.MOST_VIEWED_URL)
    Call<Articles> getAllArticles(@Path("period")String period);
}

