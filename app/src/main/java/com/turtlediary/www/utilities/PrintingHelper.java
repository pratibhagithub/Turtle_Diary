package com.turtlediary.www.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;


/**
 * Created by pratibha on 11/12/17.
 */
public class PrintingHelper

{
    private static PrintingHelper ourInstance;
    private PrintJob mPrintJob;

    private PrintingHelper() {
    }

    ;

    public static PrintingHelper getInstance() {
        if (ourInstance == null)
            ourInstance = new PrintingHelper();

        return ourInstance;
    }



    public void createWebPrintJob(WebView webView, Activity activity, PrintFinishListener completeListner) {

       /* // Get a PrintManager instance
        PrintManager printManager = (PrintManager) activity.getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter;
        if (Build.VERSION.SDK_INT < 21)
            printAdapter = webView.createPrintDocumentAdapter();
        else
            printAdapter = webView.createPrintDocumentAdapter(activity.getString(R.string.app_name) + Calendar.getInstance().getTimeInMillis());

        // Create a print job with name and adapter instance
        String jobName = activity.getString(R.string.app_name) + Calendar.getInstance().getTimeInMillis();
        if (mPrintJob == null) {
            Log.e("PrintJob", "PrintJob object is null");

        } else {
            Log.e("PrintJob", "PrintJob object is not null");

        }

        mPrintJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());*/




        // Save the job object for later status checking
        //mPrintJobs.add(printJob);

        doPrint(webView, activity, completeListner);
    }

    private WebView webView;
    private void doPrint(WebView mWebView, Activity activity, final PrintFinishListener finishListener) {
        webView = mWebView;
        // Get the print manager.
        PrintManager printManager = (PrintManager) activity.getSystemService(
                Context.PRINT_SERVICE);
        // Create a wrapper PrintDocumentAdapter to clean up when done.
        PrintDocumentAdapter adapter = new PrintDocumentAdapter() {
            private final PrintDocumentAdapter mWrappedInstance =
                    webView.createPrintDocumentAdapter();
            @Override
            public void onStart() {
                mWrappedInstance.onStart();
            }
            @Override
            public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
                                 CancellationSignal cancellationSignal, LayoutResultCallback callback,
                                 Bundle extras) {
                mWrappedInstance.onLayout(oldAttributes, newAttributes, cancellationSignal,
                        callback, extras);
            }
            @Override
            public void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
                                CancellationSignal cancellationSignal, WriteResultCallback callback) {
                try {
                    mWrappedInstance.onWrite(pages, destination, cancellationSignal, callback);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void onFinish() {
                mWrappedInstance.onFinish();
                // Intercept the finish call to know when printing is done
                // and destroy the WebView as it is expensive to keep around.
                /*webView.destroy();
                webView = null;*/
                finishListener.onFinish();
            }
        };
        // Pass in the ViewView's document adapter.
        printManager.print("MotoGP stats", adapter, null);
    }



}
