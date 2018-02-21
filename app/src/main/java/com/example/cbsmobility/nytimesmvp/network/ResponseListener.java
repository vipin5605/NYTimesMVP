package com.example.cbsmobility.nytimesmvp.network;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface ResponseListener<T> {

    void onSuccess(T t);
    void onFailure(Throwable t);
}
