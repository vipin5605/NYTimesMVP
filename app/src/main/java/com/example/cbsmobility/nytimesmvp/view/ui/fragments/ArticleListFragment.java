package com.example.cbsmobility.nytimesmvp.view.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbsmobility.nytimesmvp.R;
import com.example.cbsmobility.nytimesmvp.interfaces.ArticleGetServiceInterfaceImpl;
import com.example.cbsmobility.nytimesmvp.model.Result;
import com.example.cbsmobility.nytimesmvp.presenter.ArticleListFragmentPresenter;
import com.example.cbsmobility.nytimesmvp.presenter.ArticleListFragmentPresenterImpl;
import com.example.cbsmobility.nytimesmvp.util.Utils;
import com.example.cbsmobility.nytimesmvp.view.ArticleListFragmentViewInterface;
import com.example.cbsmobility.nytimesmvp.view.ArticleListItemClickListener;
import com.example.cbsmobility.nytimesmvp.view.ui.FragmentCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleListFragment extends BaseFragment implements ArticleListFragmentViewInterface, ArticleListItemClickListener {

    private RecyclerView mRecyclerView;
    private FragmentCallback mFragmentCallback;
    private ArticleListFragmentPresenter mArticleListFragmentPresenter;
    private FragmentManager mFragmentManager;
    private ArticleListAdapter mArticleListAdapter;
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArticleListFragmentPresenter = new ArticleListFragmentPresenterImpl(this, new ArticleGetServiceInterfaceImpl());
        mArticleListFragmentPresenter.getAllArticles("7");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null)
            mView = inflater.inflate(R.layout.article_list, null);
        else
            return mView;

        mRecyclerView = mView.findViewById(R.id.listView);
        mRecyclerView.addItemDecoration(Utils.getListDivider(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentManager = getActivity().getSupportFragmentManager();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context != null && context instanceof FragmentCallback){
            mFragmentCallback = (FragmentCallback) context;
        }
    }

    @Override
    public void updateList(List<Result> articleList) {
        if(mArticleListAdapter == null)
            mArticleListAdapter = new ArticleListAdapter(articleList, this, Picasso.with(getActivity()), this);
        mRecyclerView.setAdapter(mArticleListAdapter);
        if(mFragmentCallback.isTwoPane())
            onItemClick(articleList.get(0).getUrl());
    }

    @Override
    public void navigateTo(String url) {

        Fragment detailFragment = new ArticleDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        mFragmentManager.beginTransaction().replace(R.id.container_detail, detailFragment).commit();
    }

    @Override
    public void showLoadingSpinner() {
        mFragmentCallback.showSpinner(getString(R.string.loading));
    }

    @Override
    public void dismissLoadingSpinner() {
        mFragmentCallback.dismissSpinner();
    }

    @Override
    public void showNetworkErrorDialog() {
        mFragmentCallback.showErrorDialog(getString(R.string.network_error), "Please check your network connection", new FragmentCallback.ErrorDialogAction() {
            @Override
            public void onClickOk() {
                //nothing
            }

            @Override
            public void onClickRetry() {
                mArticleListFragmentPresenter.getAllArticles("7");
            }
        });

    }

    @Override
    public void onItemClick(String url) {
        final Fragment detailFragment = new ArticleDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        detailFragment.setArguments(bundle);
        if(mFragmentCallback.isTwoPane()){
            mFragmentCallback.updateRightPane(detailFragment);
        }else{
            mFragmentCallback.navigateTo(detailFragment);
        }
    }
}

