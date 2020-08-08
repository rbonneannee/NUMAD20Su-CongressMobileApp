package com.cs5520.numad20su_congressmobile.content.services.jsonHandlers;

import com.cs5520.numad20su_congressmobile.content.models.Committee;
import com.google.gson.Gson;
import java.util.List;

public class CommitteesJsonTextHandler {

    public static List<Committee> extract(String jsonText) {
        Gson gson = new Gson();
        ProPublicaResponse response = gson.fromJson(jsonText, ProPublicaResponse.class);
        return response.results.get(0).committees;
    }

    static class ProPublicaResponse {

        List<ResultObject> results;
    }

    static class ResultObject {

        public String congress;
        public String chamber;
        public int num_results;
        public List<Committee> committees;
    }
}


