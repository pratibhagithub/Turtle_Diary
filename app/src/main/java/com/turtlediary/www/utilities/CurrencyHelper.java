package com.turtlediary.www.utilities;

import android.app.Activity;

import com.turtlediary.www.beans.PlayStoreProductBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by pratibha on 4/12/17.
 */
public class CurrencyHelper {
    private static CurrencyHelper ourInstance = new CurrencyHelper();

    public static CurrencyHelper getInstance() {
        return ourInstance;
    }

    private CurrencyHelper() {
    }

    public   String getCurrencySymbol(String currencyCode) {
        try {
            Currency currency = Currency.getInstance(currencyCode);
            return currency.getSymbol();
        } catch (Exception e) {
            return currencyCode;
        }
    }
    public  String getCalculatedMonthyRental(int indexValueForYearPrice, ArrayList<PlayStoreProductBean> playStoreProductBeenList, Activity activity)
    {
        DecimalFormat precision = new DecimalFormat("0.00");
        String amountInMacros=getMacrosValue(indexValueForYearPrice,playStoreProductBeenList);
        amountInMacros = amountInMacros.replaceAll("^\"|\"$", "");
        Double  monthlyprice=002d;
        try{
            Double  amountInMacrosInt =Double.parseDouble(amountInMacros.toString());
            Double calculatedValueWithoutMacros= (amountInMacrosInt/1000000);
            monthlyprice= calculatedValueWithoutMacros/12;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        String calculatedPrice=precision.format(monthlyprice)+"";
        Preferences.saveCountryCode(activity,playStoreProductBeenList.get(0).getCurrencyCode());
     //   AppConstants.CurrencyCode=playStoreProductBeenList.get(0).getCurrencyCode();
        String curencySymbol=getCurrencySymbol(playStoreProductBeenList.get(0).getCurrencyCode());
        return curencySymbol+calculatedPrice;
    }

    private String getMacrosValue(int indexValueForYearPrice,ArrayList<PlayStoreProductBean> playStoreProductBeenList) {
        String macrosValue = "0";
        for (int i = 0; i < playStoreProductBeenList.size(); i++) {
            if (playStoreProductBeenList.get(i).getIndex()==(indexValueForYearPrice)) {
                macrosValue = playStoreProductBeenList.get(i).getProductAmountInMacros();
                break;
            }
        }
        return macrosValue;
    }
    public String getPriceValue(int indexValue,ArrayList<PlayStoreProductBean> playStoreProductBeenList) {
        String amount = "0";
        for (int i = 0; i < playStoreProductBeenList.size(); i++) {
            Logg.p("Product Selected price ",playStoreProductBeenList.get(i).getProductPrice());

            if (playStoreProductBeenList.get(i).getIndex()==(indexValue)) {
                amount = playStoreProductBeenList.get(i).getProductPrice();
                break;
            }
        }
        return amount;
    }

}
