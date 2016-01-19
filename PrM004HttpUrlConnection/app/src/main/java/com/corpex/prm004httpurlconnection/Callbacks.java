package com.corpex.prm004httpurlconnection;

/**
 * Created by corpex, by the Grace of God on 18/01/2016.
 */
public interface Callbacks{
    String proceso() throws Exception;
    void onPostExecute(String result);
}