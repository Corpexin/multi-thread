package com.corpex.pr27recyclerview;

import android.os.Parcelable;

/**
 * Created by corpex, by the Grace of God on 15/01/2016.
 */
public abstract class ListItem implements Parcelable{
    static final int TYPE_HEADER = 0;
    static final int TYPE_CHILD = 1;

    public abstract int getType();
}
