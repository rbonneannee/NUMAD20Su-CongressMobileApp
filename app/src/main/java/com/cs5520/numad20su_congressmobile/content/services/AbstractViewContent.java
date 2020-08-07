package com.cs5520.numad20su_congressmobile.content.services;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cs5520.numad20su_congressmobile.BuildConfig;
import com.cs5520.numad20su_congressmobile.content.VolleySingleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the outer class of a nested class structure. It inherits from Response.Listener, a
 * callback interface for delivering parsed responses, and Response.ErrorListener, a callback
 * interface for delivering error responses.
 * <p>
 * The class represents the data and operations common to all developed services sub-classed by it.
 * Common fields include 1) a list of objects received from the ProPublica server and translated
 * from JSON, 2) an adapter that controls how the details of those objects will be displayed in the
 * application, 3) the offset and offset factor used for request pagination, 4) a default query, and
 * 5) the current meeting of Congress. Common methods include 1) requesting a JSON array in String
 * format from the server, 2) converting that String into a list of objects, 3) returning the list
 * of objects, 4) returning the adapter, and 5) incrementing the value of offset so as to get the
 * next page of results.
 */
public abstract class AbstractViewContent<T> implements Response.Listener<String>,
    Response.ErrorListener {

  private static final String TAG = "AbstractViewContent";

  // Manages worker threads for running network operations
  private RequestQueue requestQueue;

  // Common fields
  protected String endpointAllItems;
  protected RecyclerView.Adapter<? extends RecyclerView.ViewHolder> viewAdapter;
  protected List<T> resultList;
  protected int offset;
  protected int OFFSET_INCREMENT = 20;
  protected String DEFAULT_QUERY = "";
  protected int currentSession = 116;


  /**
   * Constructs a Volley Response object and initializes this object's "requestQueue" field to the
   * requestQueue of a VolleySingleton and its "resultList" to a new ArrayList object.
   *
   * @param context the context a view is running in
   */
  public AbstractViewContent(Context context) {
    VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
    this.requestQueue = volleySingleton.getRequestQueue();
    this.resultList = new ArrayList<>();
    this.offset = 0;
  }

  /**
   * This class is the inner class of a nested class structure. A ProPublicaRequest object is a
   * canned request for retrieving the response body at a given URL as a String using a customized
   * header. As a static class, it can access only the static members of its outer class.
   */
  static class ProPublicaRequest extends StringRequest {

    /**
     * Calls the constructor of StringRequest, creating a new GET request to the given URL.
     *
     * @param url           a String from which to fetch the String response
     * @param listener      a listener to receive the String response
     * @param errorListener a listener to receive errors; can be null if errors are to be ignored
     */
    public ProPublicaRequest(String url,
        Response.Listener<String> listener,
        @Nullable Response.ErrorListener errorListener) {
      super(url, listener, errorListener);
    }

    /**
     * Sets X-API-Key as a custom header in the request.
     *
     * @return a map of headers to be added to the request
     */
    @Override
    public Map<String, String> getHeaders() {
      Map<String, String> result = new HashMap<>();
      result.put("X-API-Key", BuildConfig.API_KEY);
      return result;
    }
  }

  /**
   * Adds a newly-instantiated ProPublicaRequest object to this service's request queue.
   *
   * @param endpoint a URL as a String
   */
  protected void submitRequest(String endpoint) {
    this.requestQueue.add(
        new ProPublicaRequest(
            endpoint, this, this));
  }

  /**
   * Inherited from the Response.Listener interface. Called when a response is received.
   *
   * @param jsonText a JSON object in String format
   */
  @Override
  public void onResponse(String jsonText) {
    Log.d(TAG, "onResponse:");
    this.resultList.addAll(getListFromJsonText(jsonText));
    this.viewAdapter.notifyDataSetChanged();
  }

  /**
   * Inherited from the Response.ErrorListener interface. A callback that an error has occurred.
   * Logs the provided error code.
   *
   * @param error an exception encapsulated as a VolleyError object
   */
  @Override
  public void onErrorResponse(VolleyError error) {
    Log.e(TAG, error.toString());
  }

  /**
   * Returns the recyclerView adapter that manages how items held in the resultList visibly appear
   * in the application.
   *
   * @return the adapter to a view's recyclerView
   */
  public RecyclerView.Adapter<? extends RecyclerView.ViewHolder> getViewAdapter() {
    return this.viewAdapter;
  }

  /**
   * Returns an ArrayList containing object representations of what this service gave in response to
   * a GET request.
   *
   * @return an ArrayList of objects
   */
  public List<T> getResultList() {
    return this.resultList;
  }

  /**
   * Increments offset by the offsetIncrement factor.
   */
  protected void incrementOffset() {
    this.offset += this.OFFSET_INCREMENT;
  }

  /**
   * Returns a list of objects of type T by delegating the conversion of a String response to an
   * object to the appropriate JSON Handler.
   *
   * @param rawResponse a GET request's response as a String
   * @return a list of objects of type T
   */
  public abstract List<T> getListFromJsonText(String rawResponse);

  /**
   * Checks if the requested information is the same as the previously requested information. If so,
   * submits a request for the next page of results; if not, submits a request for the first page of
   * recent results.
   */
  public abstract void getAllItems();
}