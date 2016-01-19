package com.corpex.prm005httpurlconnectionpost;

/**
 * Created by corpex, by the Grace of God on 19/01/2016.
 */
public interface Callbacks {
    String proceso() throws Exception;
    void onPostExecute(String result);
}
