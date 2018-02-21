package com.example.cbsmobility.nytimesmvp.view;

import com.example.cbsmobility.nytimesmvp.model.Result;

import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface ArticleListFragmentViewInterface {

    void updateList(List<Result> articleList);
    void navigateTo(String url);
    void showLoadingSpinner();
    void dismissLoadingSpinner();
    void showNetworkErrorDialog();
}
