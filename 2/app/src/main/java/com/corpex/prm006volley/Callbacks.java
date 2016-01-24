package com.corpex.prm006volley;

/**
 * Created by corpex, by the Grace of God on 22/01/2016.
 */
public interface Callbacks {
    String proceso() throws Exception;
    void onPostExecute(String result);
}