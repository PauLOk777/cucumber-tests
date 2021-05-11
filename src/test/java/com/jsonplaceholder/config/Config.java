package com.jsonplaceholder.config;

public class Config {

    public static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    public static final String ID = "id";
    public static final String USER_ID = "userId";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String POSTS = "/posts";
    public static final String POST_BY_ID = POSTS + "/{" + ID + "}";
}
