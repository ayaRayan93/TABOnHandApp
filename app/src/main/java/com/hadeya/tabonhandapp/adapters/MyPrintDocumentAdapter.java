package com.hadeya.tabonhandapp.adapters;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;

import com.hadeya.tabonhandapp.models.InvoiceItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shaimaa Derbaz on 4/12/2018.
 */

public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
    private Activity activity;
    PrintedPdfDocument  mPdfDocument;
    private int totalPages;
    private List<InvoiceItem> dataSet;
    public MyPrintDocumentAdapter (Activity activity , List<InvoiceItem>dataSet)
    {
       this.activity=activity;
        this.dataSet=dataSet;
    }
    @Override
    public void onStart ()
    {

    }
    @Override
    public void onWrite (PageRange[] pages, ParcelFileDescriptor destination,CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback callback)
    {
        // Iterate over each page of the document,
        // check if it's in the output range.
       // for (int i = 0; i < totalPages; i++) {
        for (int i = 0; i < 2; i++) {
            // Check to see if this page is in the output range.
            if (containsPage(pages, i))
            {
                // If so, add it to writtenPagesArray. writtenPagesArray.size()
                // is used to compute the next output page index.
                //writtenPagesArray.append(writtenPagesArray.size(), i);
               // PdfDocument.Page page = mPdfDocument.startPage(i);
                PdfDocument.Page page = mPdfDocument.startPage(i);
                // check for cancellation
                if (cancellationSignal.isCanceled()) {
                    callback.onWriteCancelled();
                    mPdfDocument.close();
                    mPdfDocument = null;
                    return;
                }

                // Draw page content for printing
                drawPage(page);

                // Rendering is complete, so page can be finalized.
                mPdfDocument.finishPage(page);
            }
        }

        // Write PDF document to file
        try {
            mPdfDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            mPdfDocument.close();
            mPdfDocument = null;
        }
        PageRange[] writtenPages = computeWrittenPages();
        // Signal the print framework the document is complete
        callback.onWriteFinished(writtenPages);

    }
    @Override
    public void onLayout (PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback callback, Bundle extras)
    {
        // Create a new PdfDocument with the requested page attributes
        mPdfDocument = new PrintedPdfDocument(activity, newAttributes);

        // Respond to cancellation request
        if (cancellationSignal.isCanceled() ) {
            callback.onLayoutCancelled();
            return;
        }

        // Compute the expected number of printed pages
        int pages = computePageCount(newAttributes);

        if (pages > 0) {
            // Return print information to print framework
            PrintDocumentInfo info = new PrintDocumentInfo
                    .Builder("print_output.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(pages)
                    .build();
            // Content layout reflow is complete
            callback.onLayoutFinished(info, true);
        } else {
            // Otherwise report an error to the print framework
            callback.onLayoutFailed("Page count calculation failed.");
        }


    }
    @Override
    public void onFinish ()
    {}

    private int computePageCount(PrintAttributes printAttributes) {
        int itemsPerPage = 4; // default item count for portrait mode

        PrintAttributes.MediaSize pageSize = printAttributes.getMediaSize();
        if (!pageSize.isPortrait()) {
            // Six items per page in landscape orientation
            itemsPerPage = 6;
        }

        // Determine number of print items
       // int printItemCount = getPrintItemCount();
        int printItemCount = 4;

        return (int) Math.ceil(printItemCount / itemsPerPage);
    }

    private void drawPage(PdfDocument.Page page) {
        Canvas canvas = page.getCanvas();

        // units are in points (1/72 of an inch)
        int titleBaseLine = 72;
        int leftMargin = 54;
        int padding=28;
        int j=1;
        double totalNet=0;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(32);
        canvas.drawText("Invoice No : "+ItemsListData.invoice.getInvoiceNo(), leftMargin, titleBaseLine, paint);
        paint.setTextSize(30);
        canvas.drawText("Customer Name : "+ItemsListData.invoice.getCustomer().getCustName(), leftMargin, titleBaseLine+padding*j, paint);j++;
        paint.setTextSize(30);
        canvas.drawText("Date : "+ItemsListData.invoice.getInvoiceDate(), leftMargin, titleBaseLine+padding*j, paint); j++;
        paint.setTextSize(30);
        canvas.drawText("________________________________", leftMargin, titleBaseLine+padding*j, paint); j++;


        for (int i=0;i<dataSet.size();i++)
        {
            paint.setTextSize(30);
            canvas.drawText("Item Name : "+dataSet.get(i).getItemName(), leftMargin, titleBaseLine+padding*j, paint);j++;
            paint.setTextSize(30);
            canvas.drawText("Item Code : "+dataSet.get(i).getItemCode(), leftMargin, titleBaseLine+padding*j, paint); j++;
            paint.setTextSize(30);
            canvas.drawText("Quantity : "+dataSet.get(i).getQuantity(), leftMargin,titleBaseLine+padding*j, paint); j++;
            paint.setTextSize(30);
            canvas.drawText("Price :"+dataSet.get(i).getPrice(), leftMargin,titleBaseLine+padding*j, paint);j++;
            paint.setTextSize(30);
            canvas.drawText("Discount Amount :"+dataSet.get(i).getDiscountAmount(), leftMargin,titleBaseLine+padding*j, paint);j++;
            paint.setTextSize(30);
            canvas.drawText("Discount Percent :"+dataSet.get(i).getDiscountPercent(), leftMargin,titleBaseLine+padding*j, paint);j++;
            paint.setTextSize(30);
            canvas.drawText("Net : "+dataSet.get(i).getNet(), leftMargin,titleBaseLine+padding*j, paint);j++;
            paint.setTextSize(30);
            canvas.drawText("_______________________________", leftMargin, titleBaseLine+padding*j, paint);j++;
            totalNet+=Double.parseDouble(dataSet.get(i).getNet());
        }
        paint.setTextSize(30);
        canvas.drawText("total Net : "+totalNet, leftMargin, titleBaseLine+padding*j, paint);j++;

       // paint.setColor(Color.BLUE);
       // canvas.drawRect(100, 100, 172, 172, paint);
    }

    private PageRange[] computeWrittenPages() {

        PageRange[] pageRanges = new PageRange[1];

        pageRanges[0] = new PageRange(0, 1);

        // TODO Auto-generated method stub
        return pageRanges;
    }

    private boolean containsPage(PageRange[] pageRanges, int i) {
        // int length = pageRanges.length;

        if (pageRanges[0].getStart() <= i && pageRanges[0].getEnd() >= i)
            return true;
        else
            return false;
    }
}
