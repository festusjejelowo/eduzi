package com.ocularminds.eduzi;

import com.google.gson.Gson;
import static spark.Spark.*;
import spark.ResponseTransformer;

public class JsonFront implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}