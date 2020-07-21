package com.cs5520.numad20su_congressmobile.content;

import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.google.gson.Gson;

import java.util.List;

public class MembersJsonTextHandler {

    public static List<Member> extract(String jsonText) {
        Gson gson = new Gson();
        ProPublicaResponse response = gson.fromJson(jsonText, ProPublicaResponse.class);
        return response.results.get(0).members;
    }

    static class ProPublicaResponse {
        List<ResultObject> results;
    }

    static class ResultObject {
        public String congress;
        public String chamber;
        public int num_results;
        public int offset;
        public List<Member> members;
    }
}


