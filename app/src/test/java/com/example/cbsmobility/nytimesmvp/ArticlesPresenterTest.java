package com.example.cbsmobility.nytimesmvp;

import com.example.cbsmobility.nytimesmvp.interfaces.ArticleGetServiceInterface;
import com.example.cbsmobility.nytimesmvp.interfaces.ArticleGetServiceInterfaceImpl;
import com.example.cbsmobility.nytimesmvp.model.Result;
import com.example.cbsmobility.nytimesmvp.network.ResponseListener;
import com.example.cbsmobility.nytimesmvp.presenter.ArticleListFragmentPresenterImpl;
import com.example.cbsmobility.nytimesmvp.view.ArticleListFragmentViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by Vipin Vasu on 3/4/2018.
 */

public class ArticlesPresenterTest {


    private static List<Result> ARTICLES = null;

    private static List<Result> EMPTY_ARTICLES = new ArrayList<>(0);
    @Mock
    private  ArticleListFragmentViewInterface mArticleListFragmentView;

    @Mock
    private  ArticleGetServiceInterface mArticleGetServiceInterface;

    @Captor
    private ArgumentCaptor<ResponseListener> responseListenerArgumentCaptor;


    private ArticleListFragmentPresenterImpl articleListFragmentPresenter;


    @Before
    public void setUpMockPresenter()
    {
        MockitoAnnotations.initMocks(this);

        ARTICLES = createFakeList();
        articleListFragmentPresenter = new ArticleListFragmentPresenterImpl(mArticleListFragmentView, mArticleGetServiceInterface);

    }

    @Test
    public void loadArticlesFromRepoAndDisplayOnView()
    {
        articleListFragmentPresenter.getAllArticles("1");

        InOrder inOrder = Mockito.inOrder(mArticleListFragmentView);
        inOrder.verify(mArticleListFragmentView).showLoadingSpinner();
        verify(mArticleGetServiceInterface).getArticles(eq("1"), responseListenerArgumentCaptor.capture());
        responseListenerArgumentCaptor.getValue().onSuccess(ARTICLES);
        inOrder.verify(mArticleListFragmentView).dismissLoadingSpinner();
        verify(mArticleListFragmentView).updateList(ARTICLES);
    }

    private List<Result> createFakeList()
    {
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setTitle("Title 1");
        result.setTitle("sample date");
        return results;
    }
}
