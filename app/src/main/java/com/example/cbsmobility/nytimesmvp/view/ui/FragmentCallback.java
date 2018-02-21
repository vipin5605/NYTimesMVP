package com.example.cbsmobility.nytimesmvp.view.ui;

import android.support.v4.app.Fragment;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface FragmentCallback {

    void showSpinner(String message);
    void dismissSpinner();
    void showErrorDialog(String title, String message, ErrorDialogAction action);
    void navigateTo(Fragment targetFragment);
    void updateRightPane(Fragment targetFragment);
    boolean isTwoPane();

    interface ErrorDialogAction {
        void onClickOk();
        void onClickRetry();
    }
}
