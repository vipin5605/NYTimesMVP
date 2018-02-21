package com.example.cbsmobility.nytimesmvp.interfaces;

import com.example.cbsmobility.nytimesmvp.network.ResponseListener;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface ArticleGetServiceInterface {
    void getArticles(String period, ResponseListener responseListener);

}
