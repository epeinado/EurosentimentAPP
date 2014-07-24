package com.eurosentiment.EurosentimentAPP.sentiment;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: epeinado
 * Date: 24/07/14
 * Time: 13:54
 */
public class SentimentService {

    private static final String EUROSENTIMENT_TOKEN = "78cab860-d17d-4afa-b1ac-d3ff84e942ca";

    private static final String EUROSENTIMENT_SERVICE_URL = "http://217.26.90.243:8080/EuroSentimentServices/services/server/access/sptdl0407";

    public String getSentiment(String input) {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost post =
                new HttpPost(EUROSENTIMENT_SERVICE_URL);

        post.setHeader("x-eurosentiment-token", EUROSENTIMENT_TOKEN);

        org.json.JSONObject jsonObject = null;
        try {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("input", input));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
            entity.setContentType(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            post.setEntity(entity);

            org.apache.http.HttpResponse resp = httpClient.execute(post);

            String resStr = EntityUtils.toString(resp.getEntity());
            System.out.print(resStr);
            return resStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

}
