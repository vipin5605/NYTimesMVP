package com.example.cbsmobility.nytimesmvp.view.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.cbsmobility.nytimesmvp.R;
import com.example.cbsmobility.nytimesmvp.util.AppWebViewClient;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleDetailFragment extends BaseFragment {

    public final String TAG = ArticleDetailFragment.class.getSimpleName();
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null)
            mView = inflater.inflate(R.layout.article_detail, null);
        else
            return mView;

        ProgressBar progressBar = mView.findViewById(R.id.progressBar);
        final Bundle bundle = getArguments();
        if(bundle != null) {
            String url = bundle.getString("url");
            WebView webView = mView.findViewById(R.id.web);
            webView.setWebViewClient(new AppWebViewClient(progressBar));
            webView.loadUrl(url);
        }
        return mView;
    }
}

