package com.cs5520.numad20su_congressmobile.content;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.controllers.BillsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BillsViewContent extends AbstractViewContent<Bill> {

    private String REQUEST_RECENT = "RECENT";
    private String REQUEST_SUBJECT_SEARCH = "SUBJECT SEARCH";
    private String REQUEST_KEYWORD_SEARCH = "KEYWORD SEARCH";
    private String REQUEST_FILTER = "FILTER";

    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/both/bills/active.json";
    private static final String ENDPOINT_SUBJECT_SEARCH = "https://api.propublica.org/congress/v1/bills/subjects/";
    private static final String ENDPOINT_KEYWORD_SEARCH = "https://api.propublica.org/congress/v1/bills/search.json?query=";

    private int OFFSET_INCREMENT = 20;

    private String requestType = REQUEST_RECENT;
    private String query = "";
    private static int offset = 0;


    public BillsViewContent(Context context) {
        super(context);
        this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList);
    }

    // TODO Create filter methods to be called from a filter view
    public void getBills() {
        if (!this.requestType.equals(REQUEST_RECENT)) {
            resetLoad(REQUEST_RECENT);
        }
        this.submitRequest(ENDPOINT + "?offset=" + offset);
        incrementOffset();
    }

    public void searchBillsBySubject(String subject) {
        if (!this.requestType.equals(REQUEST_SUBJECT_SEARCH) || !this.query.equals(subject)) {
            resetLoad(REQUEST_SUBJECT_SEARCH);
        }
        this.query = subject;
        this.submitRequest(ENDPOINT_SUBJECT_SEARCH + subject + ".json");
    }

    public void searchBillsByKeyword(String keyword) {
        if (!this.requestType.equals(REQUEST_KEYWORD_SEARCH) || !this.query.equals(keyword)) {
            resetLoad(REQUEST_KEYWORD_SEARCH);
        }
        this.query = keyword;
        this.submitRequest(ENDPOINT_KEYWORD_SEARCH + keyword);
    }

    @Override
    List<Bill> getListFromJsonText(String jsonText) {
        switch (requestType) {
            case "RECENT":
            case "KEYWORD SEARCH":
                return BillsJsonTextHandler.extract(jsonText);
            case "SUBJECT SEARCH":
                return BillsSubjectSearchJsonTextHandler.extract(jsonText);
            case "FILTER":
                // TODO
            default:
                return null;
        }
    }

    private void incrementOffset() {
        offset += OFFSET_INCREMENT;
    }

    private void resetLoad(String requestType) {
            this.requestType = requestType;
            this.resetResultList();
            offset = 0;
    }

    public void loadMore(){
        switch (requestType) {
            case "RECENT":
                getBills();
                break;
            case "KEYWORD SEARCH":
                searchBillsByKeyword(this.query);
                break;
            case "SUBJECT SEARCH":
                searchBillsBySubject(this.query);
                break;
            case "FILTER":
                // TODO
        }
    }


    public List<Bill> getResultList() {
        return this.resultList;
    }

    public void resetResultList() {
        this.resultList.clear();
    }
}
