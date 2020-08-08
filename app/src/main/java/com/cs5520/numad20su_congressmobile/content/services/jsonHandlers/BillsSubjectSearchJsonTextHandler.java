package com.cs5520.numad20su_congressmobile.content.services.jsonHandlers;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.google.gson.Gson;
import java.util.List;

public class BillsSubjectSearchJsonTextHandler {

  static class ProPublicaResponse {

    String status;
    String copyright;
    int num_results;
    int offset;
    String subject;
    List<Bill> results;
  }

  public static List<Bill> extract(String jsonText) {
    Gson gson = new Gson();
    ProPublicaResponse response = gson.fromJson(jsonText, ProPublicaResponse.class);
    return response.results;
  }
}
