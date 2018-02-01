package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.PlayStoreProductBean;

import java.util.ArrayList;

/**
 * Created by pratibha on 1/12/17.
 */

public interface OnPriceGettingCompletion {
    void getPriceDetails(   ArrayList<PlayStoreProductBean> playStoreProductBeenList);
}
