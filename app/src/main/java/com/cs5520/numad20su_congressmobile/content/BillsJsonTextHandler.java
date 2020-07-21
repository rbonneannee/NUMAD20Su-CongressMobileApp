package com.cs5520.numad20su_congressmobile.content;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.google.gson.Gson;

import java.util.List;

public class BillsJsonTextHandler {

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
    }

    public static List<Bill> extract(String jsonText) {
        Gson gson = new Gson();
        ProPublicaResponse response = gson.fromJson(jsonText, ProPublicaResponse.class);
        return response.results.get(0).bills;
    }
}


