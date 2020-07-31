package com.cs5520.numad20su_congressmobile.content;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.controllers.BillsRecyclerViewAdapter;

import java.util.List;

// TODO Gather bills from both chambers for each endpoint
// TODO Create filter methods to be called from a filter view
public class BillsViewContent extends AbstractViewContent<Bill> {

    enum RequestEnum {RECENT, SUBJECT, SEARCH;}

    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/both/bills/active.json";
    private static final String ENDPOINT_SUBJECT_SEARCH = "https://api.propublica.org/congress/v1/bills/subjects/";
    private static final String ENDPOINT_KEYWORD_SEARCH = "https://api.propublica.org/congress/v1/bills/search.json?query=";
    private static final int OFFSET_INCREMENT = 20;

    private static int RECENT_OFFSET = 0;
    private static int SUBJECT_OFFSET = 0;
    private static int SEARCH_OFFSET = 0;
    private String searchQuery;
    private String subjectQuery;
    private RequestEnum lastRequestType;

    public BillsViewContent(Context context) {
        super(context);
        this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList);
    }

    public void getRecentBills() {
        this.lastRequestType = RequestEnum.RECENT;
        this.submitRequest(ENDPOINT + "?offset=" + RECENT_OFFSET);
        incrementOffset(RequestEnum.RECENT);
    }

    public void getRecentBillsBySubject(String subject) {
        this.lastRequestType = RequestEnum.SUBJECT;
        conditionalReset(subject, this.subjectQuery, RequestEnum.SUBJECT);
        this.subjectQuery = subject;
        this.submitRequest(ENDPOINT_SUBJECT_SEARCH + subject + ".json" + "?offset=" + SUBJECT_OFFSET);
        incrementOffset(RequestEnum.SUBJECT);
    }

    public void searchBills(String query) {
        this.lastRequestType = RequestEnum.SEARCH;
        conditionalReset(query, this.searchQuery, RequestEnum.SEARCH);
        this.searchQuery = query;
        this.submitRequest(ENDPOINT_KEYWORD_SEARCH + query + "&offset=" + SEARCH_OFFSET);
        incrementOffset(RequestEnum.SEARCH);
    }

    @Override
    List<Bill> getListFromJsonText(String jsonText) {
        switch (this.lastRequestType) {
            case RECENT:
            case SEARCH:
                return BillsJsonTextHandler.extract(jsonText);
            case SUBJECT:
                return BillsSubjectSearchJsonTextHandler.extract(jsonText);
            default:
                return null;
        }
    }

    private void incrementOffset(RequestEnum requestType) {
        switch (requestType) {
            case RECENT:
                RECENT_OFFSET += OFFSET_INCREMENT;
                break;
            case SEARCH:
                SEARCH_OFFSET += OFFSET_INCREMENT;
                break;
            case SUBJECT:
                SUBJECT_OFFSET += OFFSET_INCREMENT;
                break;
        }
    }

    private boolean conditionalReset(String s1, String s2, RequestEnum type) {
        boolean result = false;
        if (!s1.equals(s2)) {
            result = true;
            switch (type) {
                case RECENT:
                    RECENT_OFFSET = 0;
                    break;
                case SEARCH:
                    SEARCH_OFFSET = 0;
                    break;
                case SUBJECT:
                    SUBJECT_OFFSET = 0;
                    break;
            }
        }
        return result;
    }

    public void loadMore() {
        switch (this.lastRequestType) {
            case RECENT:
                getRecentBills();
                break;
            case SEARCH:
                searchBills(this.searchQuery);
                break;
            case SUBJECT:
                getRecentBillsBySubject(this.subjectQuery);
                break;
        }
    }

    public List<Bill> getResultList() {
        return this.resultList;
    }
}
