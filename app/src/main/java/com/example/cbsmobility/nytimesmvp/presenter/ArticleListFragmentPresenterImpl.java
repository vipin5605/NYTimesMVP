package com.example.cbsmobility.nytimesmvp.presenter;

import com.example.cbsmobility.nytimesmvp.interfaces.ArticleGetServiceInterface;
import com.example.cbsmobility.nytimesmvp.model.Result;
import com.example.cbsmobility.nytimesmvp.network.ResponseListener;
import com.example.cbsmobility.nytimesmvp.view.ArticleListFragmentViewInterface;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleListFragmentPresenterImpl implements ArticleListFragmentPresenter {

    private final ArticleListFragmentViewInterface mArticleListFragmentView;
    private final ArticleGetServiceInterface mArticleGetServiceInterface;

    public ArticleListFragmentPresenterImpl(ArticleListFragmentViewInterface articleListFragmentView, ArticleGetServiceInterface articleGetServiceInterface){
        mArticleListFragmentView = articleListFragmentView;
        mArticleGetServiceInterface = articleGetServiceInterface;

    }

    @Override
    public void getAllArticles(String period) {

        mArticleListFragmentView.showLoadingSpinner();
        mArticleGetServiceInterface.getArticles(period, new ResponseListener<List<Result>>() {
            @Override
            public void onSuccess(List<Result> list) {
                mArticleListFragmentView.dismissLoadingSpinner();
                if(list != null)
                    mArticleListFragmentView.updateList(list);
            }

            @Override
            public void onFailure(Throwable t) {
                mArticleListFragmentView.dismissLoadingSpinner();
                if(t instanceof UnknownHostException) {
                    mArticleListFragmentView.showNetworkErrorDialog();
                }

            }
        });
    }
}

