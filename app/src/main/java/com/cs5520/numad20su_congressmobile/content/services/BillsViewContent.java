package com.cs5520.numad20su_congressmobile.content.services;


import android.app.Activity;
import android.content.Context;
import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.BillsJsonTextHandler;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.BillsSubjectSearchJsonTextHandler;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.layoutAdapters.BillsRecyclerViewAdapter;
import java.util.List;

// TODO Create filter methods to be called from a filter view

/**
 * This class extends AbstractViewContent and is a service that provides bill information from the
 * ProPublica Congress database to the application. BillViewContent objects have endpoints to which
 * they can make GET requests, and an enumerated type and a string query for keeping track of the
 * bill information previously requested from the ProPublica server. BillViewContent objects can
 * convert a JSON String response to a list of Bill objects for recent and keyword searched bills
 * and request the next page of results if desired by the user.
 * <p>
 * <p>
 * implements is needed
 */

public class BillsViewContent extends AbstractViewContent<Bill> {

  // Classifies and holds type of information requested from server
  private GetRequestType prevGetRequestType;
  private String prevKeywordQuery;
  // TODO determine if application will support search by subject
  private String prevSubjectQuery;
  private Context mContext;
  private Activity activity;

  // Supported server endpoints
  private final String endpointBillsSubjectSearch;
  private final String endpointBillsKeywordSearch;

  /**
   * Constructs a BillViewContent object and sets its context to the given context, its viewAdapter
   * to a newly instantiated BillRecyclerViewAdapter object, its previously requested information to
   * recent bills, and its endpoints to the server endpoints supported by the application.
   *
   * @param context the context a view is running in
   */
  public BillsViewContent(Context context, FollowInterface followInterface) {
    super(context);
    this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList, followInterface);
    this.prevGetRequestType = GetRequestType.ALL;
    this.prevKeywordQuery = this.DEFAULT_QUERY;
    this.prevSubjectQuery = this.DEFAULT_QUERY;

    // Endpoint URLs
    this.endpointAllItems =
        "https://api.propublica.org/congress/v1/" + this.currentSession
            + "/both/bills/introduced.json?offset=";
    this.endpointBillsSubjectSearch =
        "https://api.propublica.org/congress/v1/bills/subjects/";
    this.endpointBillsKeywordSearch =
        "https://api.propublica.org/congress/v1/bills/search.json?query=";
  }

  /**
   * Checks if the requested information is the same as the previously requested information. If so,
   * submits a request for the next page of bills; if not, submits a request for the first page of
   * bills. Results are organized in descending order by introduction date.
   */

  @Override
  public void getAllItems() {
    if (!conditionalReset(GetRequestType.ALL, this.DEFAULT_QUERY, this.DEFAULT_QUERY)) {
      incrementOffset();
    }
    this.submitRequest(this.endpointAllItems + this.offset);
  }

  /**
   * Checks if the requested information is the same as the previously requested information. If so,
   * submits a request for the next page of bills containing the given keyword; if not, submits a
   * request for the first page of bills containing the given keyword.
   *
   * @param keyword a String to search for in the title and full text of legislation
   */
  public void getBillsWithKeyword(String keyword) {
    if (conditionalReset(GetRequestType.TEXT_SEARCH, keyword, this.prevKeywordQuery)) {
      this.prevKeywordQuery = keyword;
    } else {
      incrementOffset();
    }
    this.submitRequest(endpointBillsKeywordSearch + keyword + "&offset="
        + this.offset);
  }

  // TODO determine if application will support search by subject

  /**
   * Checks if the requested information is the same as the previously requested information. If so,
   * submits a request for the next page of bills touching upon the given subject; if not, submits a
   * request for the first page of bills touching upon the given subject. Results are ordered by
   * most recently updated to least recently updated.
   *
   * @param subject a legislative subject
   */
  public void getBillsWithSubject(String subject) {
    if (conditionalReset(GetRequestType.SUBJECT_SEARCH, subject, this.prevSubjectQuery)) {
      this.prevSubjectQuery = subject;
    } else {
      incrementOffset();
    }
    this.submitRequest(endpointBillsSubjectSearch + subject + ".json" + "?offset="
        + offset);
  }

  /**
   * Returns a list of Bill objects by delegating the conversion of a String response to a Bill
   * object to the appropriate JSON Handler.
   *
   * @param jsonText a GET request's response as a String
   * @return a list of Bill objects corresponding to the server's response.
   */
  @Override
  public List<Bill> getListFromJsonText(String jsonText) {
    switch (this.prevGetRequestType) {
      case ALL:
      case TEXT_SEARCH:
        return BillsJsonTextHandler.extract(jsonText);
      case SUBJECT_SEARCH:
        return BillsSubjectSearchJsonTextHandler.extract(jsonText);
      case FILTER:
        // TODO
      default:
        return null;
    }
  }

  /**
   * Checks if the requested information is the same as the previously requested information.
   * Equivalence is determined by the equality of the previous and current request's requestEnum and
   * query. If the requests are different, resets the page, clears the list of results, and updates
   * the previous requestEnum to the current requestEnum.
   *
   * @param currentGetRequestType the category of information requested
   * @param currentQuery          the query the request will filter its results on
   * @param prevQuery             the query the request previously filtered its results on
   * @return true if the request is different from the previous request; otherwise, false
   */
  private boolean conditionalReset(GetRequestType currentGetRequestType, String currentQuery,
      String prevQuery) {
    boolean isReset = false;
    if ((!currentQuery.equals(prevQuery)) || (currentGetRequestType != this.prevGetRequestType)) {
      this.offset = 0;
      this.resultList.clear();
      this.prevGetRequestType = currentGetRequestType;
      isReset = true;
    }
    return isReset;
  }

  /**
   * Delegates loading of next page of results to the appropriate method.
   */
  public void loadMore() {
    switch (this.prevGetRequestType) {
      case ALL:
        getAllItems();
        break;
      case TEXT_SEARCH:
        getBillsWithKeyword(this.prevKeywordQuery);
        break;
      case SUBJECT_SEARCH:
        // TODO determine if application will support search by subject
        getBillsWithSubject(this.prevSubjectQuery);
        break;
      case FILTER:
        break;
    }
  }
}
