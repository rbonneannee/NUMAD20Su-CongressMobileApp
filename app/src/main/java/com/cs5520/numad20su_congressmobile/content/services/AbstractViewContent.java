package com.cs5520.numad20su_congressmobile.content.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cs5520.numad20su_congressmobile.BuildConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

 */
abstract class AbstractViewContent<T> implements Response.Listener<String>,
        Response.ErrorListener {

    private static final String TAG = "AbstractViewContent";

    private RequestQueue requestQueue;

    protected RecyclerView.Adapter<? extends RecyclerView.ViewHolder> viewAdapter;
    protected List<T> resultList;

    public AbstractViewContent(Context context) {
        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
        this.requestQueue = volleySingleton.getRequestQueue();
        this.resultList = new ArrayList<>();
    }

    static class ProPublicaRequest extends StringRequest {

        public ProPublicaRequest(String url,
                                 Response.Listener<String> listener,
                                 @Nullable Response.ErrorListener errorListener) {
            super(url, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> result = new HashMap<>();
            result.put("X-API-Key", BuildConfig.API_KEY);
            return result;
        }
    }

    // TODO MainActivity (indirectly) calls this method and puts the
    protected void submitRequest(String endpoint) {
        this.requestQueue.add(
                new ProPublicaRequest(
                        endpoint, this, this));
    }

    @Override
    public void onResponse(String jsonText) {
        Log.d(TAG, "onResponse:");
        this.resultList.addAll(getListFromJsonText(jsonText));
        this.viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.toString());
    }

    public RecyclerView.Adapter<? extends RecyclerView.ViewHolder> getViewAdapter() {
        return this.viewAdapter;
    }

    abstract List<T> getListFromJsonText(String rawResponse);

    public List<T> getResultList() {
        return this.resultList;
    }

}
