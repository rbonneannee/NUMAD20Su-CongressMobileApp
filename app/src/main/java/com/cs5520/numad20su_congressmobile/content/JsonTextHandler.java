package com.cs5520.numad20su_congressmobile.content;

import com.cs5520.numad20su_congressmobile.models.Bill;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonTextHandler {

    ProPublicaResponse response;
    Gson gson;

    static class ProPublicaResponse {
        String status;
        String copyright;
        List<ResultObject> results;
    }

    static class ResultObject {
        public String congress;
        public String chamber;
        public int num_results;
        public int offset;
        public List<Bill> bills;

        List<Bill> getBills() {
            return new ArrayList<>(this.bills);
        }
    }

    public JsonTextHandler(String rawResponse) {
        this.gson = new Gson();
        this.response = this.gson.fromJson(rawResponse, ProPublicaResponse.class);
    }

    public List<Bill> getResults() {
        return response.results.get(0).bills;
    }
}


