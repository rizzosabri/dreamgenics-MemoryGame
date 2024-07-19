package com.dreamgenics.spring.datajpa.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ImgService {

    private static final String API_KEY = "44823368-e1e49eb85a60dcafa4f5ec64e";
    private static final String BASE_URL = "https://pixabay.com/api/";
    private static final String IMAGE_TYPE = "vector";

    public String fetchImageUrlFromPixabay(String query) {
        String urlString = buildUrl(query);
        try {
            String response = getHttpResponse(urlString);
            return parseImageUrl(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    private String buildUrl(String query) {
        return String.format("%s?key=%s&q=%s&image_type=%s", BASE_URL, API_KEY, query, IMAGE_TYPE);
    }

    private String getHttpResponse(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } finally {
            connection.disconnect();
        }
    }

    private String parseImageUrl(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        JsonNode hits = jsonNode.get("hits");

        if (hits.isArray() && hits.size() > 0) {
            return hits.get(0).get("largeImageURL").asText();
        } else {
            return "No images found";
        }
    }
}
