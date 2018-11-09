package com.example.rkjc.news_app_2;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String BASE_URL = "https://newsapi.org/v1/articles";
    final static String PARAM_SOURCE = "source";
    final static String PARAM_SOURCE_VALUE = "the-next-web";
    final static String PARAM_SORT = "sortBy";
    final static String PARAM_SORT_VALUE = "latest";
    final static String PARAM_KEY = "apiKey";
    final static String API_KEY = "040db57b7aeb4d5d97cd2289481042da";

    public static URL buildURL() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, PARAM_SOURCE_VALUE)
                .appendQueryParameter(PARAM_SORT, PARAM_SORT_VALUE)
                .appendQueryParameter(PARAM_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}