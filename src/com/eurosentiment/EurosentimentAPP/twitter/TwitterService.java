package com.eurosentiment.EurosentimentAPP.twitter;

import com.eurosentiment.EurosentimentAPP.Tweet;
import com.eurosentiment.EurosentimentAPP.sentiment.SentimentService;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;

/**
 * User: epeinado
 * Date: 24/07/14
 * Time: 13:56
 */
public class TwitterService {

    private static final String CONSUMER_KEY = "Dvbe6sN5X9oRJcOfrM7w";
    private static final String CONSUMER_SECRET = "IZ862OLEYohd6KvLfCRdKbCmUc6Zd4rp242kPVeh8I";
    private static final String ACCESS_TOKEN = "195658424-yjywweXbQz1Obt7THvXBnj3JqhfC5PtML0SkdytU";
    private static final String ACCESS_TOKEN_SECRET = "A7yfH9lIDqYOAK6twChCGz2aMMFbTqSVfQcikaymNKs";

    public ArrayList<Tweet> searchTwitter(String term) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true);
            cb.setOAuthConsumerKey(CONSUMER_KEY);
            cb.setOAuthConsumerSecret(CONSUMER_SECRET);
            cb.setOAuthAccessToken(ACCESS_TOKEN);
            cb.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
            TwitterFactory twitterFactory = new TwitterFactory(cb.build());
            Twitter twitter = twitterFactory.getInstance();
            Query query = new Query(term);
            ArrayList<Tweet> res = new ArrayList<Tweet>();
            SentimentService sentimentService = new SentimentService();
            try {
                QueryResult result = twitter.search(query);
                for (twitter4j.Status status : result.getTweets()) {
                    String sentiment = sentimentService.getSentiment(status.getText());
                    JSONObject sentiJson = new JSONObject(sentiment);
    //                System.out.print(sentiment2);
    //                String sentiment = "0.0";
                    res.add(new Tweet("@" + status.getUser().getScreenName(), status.getText(), sentiJson.getJSONObject("results").getJSONObject("analysis").getString("@dc:language")));
                    //                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                }
            } catch (TwitterException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        return res;
        }

}
