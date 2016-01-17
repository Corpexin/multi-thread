package com.corpex.prm003progressbarasynctask;

/**
 * Created by corpex, by the Grace of God on 13/01/2016.
 */
public interface Callbacks{
    void onPreExecute();
    void onProgressUpdate(int progress);
    void onPostExecute(int result);
}