package com.example.cbsmobility.nytimesmvp;

import com.example.cbsmobility.nytimesmvp.interfaces.ArticleGetServiceInterface;
import com.example.cbsmobility.nytimesmvp.model.Result;
import com.example.cbsmobility.nytimesmvp.network.ResponseListener;
import com.example.cbsmobility.nytimesmvp.presenter.ArticleListFragmentPresenter;
import com.example.cbsmobility.nytimesmvp.presenter.ArticleListFragmentPresenterImpl;
import com.example.cbsmobility.nytimesmvp.view.ArticleListFragmentViewInterface;

import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticlesPresenterUnitTest {
    private ArticleListFragmentPresenter mArticleListFragmentPresenter;
    private FakeArticleListFragmentView fakeArticleListFragmentView;

    @Before
    public void setUp(){
        fakeArticleListFragmentView = new FakeArticleListFragmentView();
    }


    @Test
    public void test_getArticleList_Success(){
        ArticleGetServiceInterface fakeNYArticleServiceInteractor = new FakeNYArticleServiceInteractor(true);
        mArticleListFragmentPresenter = new ArticleListFragmentPresenterImpl(fakeArticleListFragmentView, fakeNYArticleServiceInteractor);
        assertFalse(fakeArticleListFragmentView.size == 2);
        mArticleListFragmentPresenter.getAllArticles("1");
        assertTrue(fakeArticleListFragmentView.size == 2);
        assertFalse(fakeArticleListFragmentView.size == 4);
    }

    @Test
    public void test_getArticleList_Fail(){
        ArticleGetServiceInterface fakeNYArticleServiceInteractor = new FakeNYArticleServiceInteractor(false);
        mArticleListFragmentPresenter = new ArticleListFragmentPresenterImpl(fakeArticleListFragmentView, fakeNYArticleServiceInteractor);
        assertFalse(fakeArticleListFragmentView.isErrorDialogShown == true);
        mArticleListFragmentPresenter.getAllArticles("1");
        assertTrue(fakeArticleListFragmentView.isErrorDialogShown == true);
    }


    public class FakeArticleListFragmentView implements ArticleListFragmentViewInterface{

        public int size;
        private boolean isErrorDialogShown = false;

        @Override
        public void updateList(List<Result> articleList) {
            if(articleList != null){
                size = articleList.size();
            }
        }

        @Override
        public void navigateTo(String url) {

        }

        @Override
        public void showLoadingSpinner() {
        }

        @Override
        public void dismissLoadingSpinner() {
        }

        @Override
        public void showNetworkErrorDialog() {
            isErrorDialogShown = true;
        }
    }

    public class FakeNYArticleServiceInteractor implements ArticleGetServiceInterface{

        private final boolean mIsSuccess;

        public FakeNYArticleServiceInteractor(boolean isSuccess){
            mIsSuccess = isSuccess;
        }
        @Override
        public void getArticles(String period, ResponseListener restRequestListener) {
            if(mIsSuccess)
                restRequestListener.onSuccess(createArticleList());
            else {
                restRequestListener.onFailure(new UnknownHostException());
            }
        }
    }

    private List<Result> createArticleList(){
        List<Result> list = new ArrayList<>();
        list.add(new Result());
        list.add(new Result());
        return list;
    }
}