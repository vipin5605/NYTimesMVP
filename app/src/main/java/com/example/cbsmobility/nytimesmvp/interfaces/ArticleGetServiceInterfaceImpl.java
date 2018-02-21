package com.example.cbsmobility.nytimesmvp.interfaces;

import com.example.cbsmobility.nytimesmvp.model.Articles;
import com.example.cbsmobility.nytimesmvp.network.ResponseListener;
import com.example.cbsmobility.nytimesmvp.services.GetArticlesServiceHelper;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleGetServiceInterfaceImpl implements ArticleGetServiceInterface {

    @Override
    public void getArticles(String period, final ResponseListener responseListener) {
        GetArticlesServiceHelper.getAllArticles(period, new ResponseListener<Articles>() {
            @Override
            public void onSuccess(Articles allItems) {
                if(allItems != null){
                    if(responseListener != null)
                        responseListener.onSuccess(allItems.getResults());
                }

            }

            @Override
            public void onFailure(Throwable t) {
                if(responseListener != null)
                    responseListener.onFailure(t);
            }
        });
    }
}
