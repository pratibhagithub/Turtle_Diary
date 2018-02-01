package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.ProductPriceBean;

/**
 * Created by pratibha on 13/11/17.
 */

public interface ProductIdCompleteInterface {
    void productIDSucess(ProductPriceBean productPriceBean);

    void productIDFailed(String msg);

}
