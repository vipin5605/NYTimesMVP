package com.example.cbsmobility.nytimesmvp.services;

import com.example.cbsmobility.nytimesmvp.constants.ApiConstants;
import com.example.cbsmobility.nytimesmvp.error.NullURLEmptyException;
import com.example.cbsmobility.nytimesmvp.model.Articles;
import com.example.cbsmobility.nytimesmvp.network.ApiClientProvider;
import com.example.cbsmobility.nytimesmvp.network.ResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class GetArticlesServiceHelper {

    public static void getAllArticles(String period, final ResponseListener responseListener) {
        try {
            GetArticlesService getArticlesService = new ApiClientProvider(ApiConstants.BASE_URL, null).provideApiClient(false).create(GetArticlesService.class);
            Call<Articles> call = getArticlesService.getAllArticles(period);
            call.enqueue(new Callback<Articles>() {
                @Override
                public void onResponse(Call<Articles> call, Response<Articles> response) {
                    if (response != null) {
                        Articles allItems = response.body();
                        if (responseListener != null)
                            responseListener.onSuccess(allItems);
                    }

                }

                @Override
                public void onFailure(Call<Articles> call, Throwable t) {
                    if (responseListener != null)
                        responseListener.onFailure(t);
                }
            });
        } catch (NullURLEmptyException e) {
            e.printStackTrace();
        }

    }

}
