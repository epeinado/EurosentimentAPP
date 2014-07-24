package com.eurosentiment.EurosentimentAPP;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * User: epeinado
 * Date: 23/07/14
 * Time: 12:51
 */
public class Pruebas {

    private static final String EUROSENTIMENT_TOKEN = "78cab860-d17d-4afa-b1ac-d3ff84e942ca";

       private static final String EUROSENTIMENT_SERVICE_URL = "http://217.26.90.243:8080/EuroSentimentServices/services/server/access/sptse0208";



    private static void searchTwitter(String term) {
           ConfigurationBuilder cb = new ConfigurationBuilder();
           cb.setDebugEnabled(true);
           cb.setOAuthConsumerKey("Dvbe6sN5X9oRJcOfrM7w");
           cb.setOAuthConsumerSecret("IZ862OLEYohd6KvLfCRdKbCmUc6Zd4rp242kPVeh8I");
           cb.setOAuthAccessToken("195658424-yjywweXbQz1Obt7THvXBnj3JqhfC5PtML0SkdytU");
           cb.setOAuthAccessTokenSecret("A7yfH9lIDqYOAK6twChCGz2aMMFbTqSVfQcikaymNKs");
           TwitterFactory twitterFactory = new TwitterFactory(cb.build());
           Twitter twitter = twitterFactory.getInstance();
           Query query = new Query("nba");
           try {
               QueryResult result = twitter.search(query);
               for (Status status : result.getTweets()) {
                       System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
               }
           } catch (TwitterException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           }

       }

    public static String getSentiment(String input) {
           DefaultHttpClient httpClient = new DefaultHttpClient();

           HttpPost post =
               new HttpPost(EUROSENTIMENT_SERVICE_URL);

           post.setHeader("x-eurosentiment-token", EUROSENTIMENT_TOKEN);
   //        post.setHeader("content-type", "application/json");
           post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject("{\n" +
                    "    \"http_code\": \"200\",\n" +
                    "    \"results\": {\n" +
                    "        \"@context\": [\n" +
                    "            \"http://demos.gsi.dit.upm.es/eurosentiment/static/context.jsonld\"\n" +
                    "        ],\n" +
                    "        \"@id\": \"pt:LanguageDetection\",\n" +
                    "        \"analysis\": {\n" +
                    "            \"@id\": \"pt:LanguageDetection\",\n" +
                    "            \"@type\": \"pt:LanguageDetection\",\n" +
                    "            \"@dc:language\": \"en\"\n" +
                    "        },\n" +
                    "        \"entries\": [\n" +
                    "            {\n" +
                    "                \"dc:language\": \"en\",\n" +
                    "                \"nif:isString\": \"The hotel was fantastic. The restaurant was cheap and the room was comfortable.\"\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return jsonObject.toString();
       }

    public static void main(String[] args) throws org.json.JSONException {
//        NifInput nifInput = new NifInput("{'input': 'This hotel is wonderful'}");
        String result = getSentiment("This hotel is wonderful");
        System.out.print(result.toString());
    }
}
