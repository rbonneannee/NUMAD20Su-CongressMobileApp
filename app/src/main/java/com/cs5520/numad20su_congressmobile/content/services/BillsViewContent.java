package com.cs5520.numad20su_congressmobile.content.services;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.BillsJsonTextHandler;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.BillsSubjectSearchJsonTextHandler;
import com.cs5520.numad20su_congressmobile.layoutAdapters.BillsRecyclerViewAdapter;

import java.util.List;

// TODO Create filter methods to be called from a filter view
/**
 * This class is a service that provides bill information from the ProPublica Congress database to
 * the application. BillViewContent objects have endpoints to which they can make GET requests,
 */
public class BillsViewContent extends AbstractViewContent<Bill> {

    // Classifies and holds type of information requested from server
    enum RequestEnum {RECENT, SUBJECT, KEYWORD;}
    private RequestEnum prevRequestEnum;
    private String prevKeywordQuery;
    // TODO determine if application will support search by subject
    private String prevSubjectQuery;

    // Supported server endpoints
    private final String endpointBillsRecent;
    private final String endpointBillsSubjectSearch;
    private final String endpointBillsKeywordSearch;

    public BillsViewContent(Context context) {
        super(context);
        this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList);
        this.prevRequestEnum = RequestEnum.RECENT;
        this.prevKeywordQuery  = "";

        // Endpoint URLs
        this.endpointBillsRecent =
                "https://api.propublica.org/congress/v1/116/both/bills/active.json?offset=";
        this.endpointBillsSubjectSearch =
                "https://api.propublica.org/congress/v1/bills/subjects/";
        this.endpointBillsKeywordSearch =
                "https://api.propublica.org/congress/v1/bills/search.json?query=";
    }

    public void getRecentBills() {
        if (!conditionalReset(RequestEnum.RECENT, this.DEFAULT_QUERY, this.DEFAULT_QUERY)){
            incrementOffset();
        }
        this.submitRequest(this.endpointBillsRecent + this.offset);
    }

    /* TODO determine if application will support search by subject
    public void getBillsWithSubject(String subject) {
        this.prevRequestType = RequestEnum.SUBJECT;
        conditionalReset(subject, this.prevSubjectQuery, RequestEnum.SUBJECT, prevRequestType);
        this.prevSubjectQuery = subject;
        this.submitRequest(endpointBillsSubjectSearch + subject + ".json" + "?offset=" + offset);
        this.incrementOffset();
    }
     */

    public void getBillsWithKeyword(String keyword) {
        if (conditionalReset(RequestEnum.KEYWORD, keyword, this.prevKeywordQuery)) {
            this.prevKeywordQuery = keyword;
        } else {
            incrementOffset();
        }
        this.submitRequest(endpointBillsKeywordSearch + keyword + "&offset=" + this.offset);
    }

    @Override
    List<Bill> getListFromJsonText(String jsonText) {
        switch (this.prevRequestEnum) {
            case RECENT:
            case KEYWORD:
                return BillsJsonTextHandler.extract(jsonText);
            case SUBJECT:
                return BillsSubjectSearchJsonTextHandler.extract(jsonText);
            default:
                return null;
        }
    }



    // Reset when either the query changes or the request type changes
    private boolean conditionalReset(RequestEnum currentRequestEnum, String currentQuery,
                                     String prevQuery) {
        boolean isReset = false;
        if ((!currentQuery.equals(prevQuery)) || (currentRequestEnum != this.prevRequestEnum)) {
            this.offset = 0;
            this.resultList.clear();
            this.prevRequestEnum = currentRequestEnum;
            isReset = true;
        }
        return isReset;
    }

    public void loadMore() {
        switch (this.prevRequestEnum) {
            case RECENT:
                getRecentBills();
                break;
            case KEYWORD:
                getBillsWithKeyword(this.prevKeywordQuery);
                break;
            case SUBJECT:
                // TODO determine if application will support search by subject
                // getBillsWithSubject(this.prevSubjectQuery);
                break;
        }
    }
}
