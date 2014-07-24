package com.eurosentiment.EurosentimentAPP;

/**
 * User: epeinado
 * Date: 23/07/14
 * Time: 13:29
 */
public class Tweet {
    private String user;
    private String tweetText;
    private String sentiment;
    private long id;

    public Tweet(String user, String tweetText, String sentiment) {
        this.user = user;
        this.tweetText = tweetText;
        this.sentiment = sentiment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
}
