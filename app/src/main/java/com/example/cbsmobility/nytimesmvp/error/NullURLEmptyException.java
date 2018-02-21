package com.example.cbsmobility.nytimesmvp.error;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class NullURLEmptyException extends Exception {

    public NullURLEmptyException() {
        super("Base Url can not be null or empty");
    }
}
