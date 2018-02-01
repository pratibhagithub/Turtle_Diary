package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.GameListBean;

/**
 * Created by arvind on 31/10/17.
 */

public interface OnGameListCompleteListener {

    void requestSuccess(GameListBean gameListBean);
    void requestFailed(String error);



}
