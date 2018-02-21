package com.example.cbsmobility.nytimesmvp.network;

import com.example.cbsmobility.nytimesmvp.model.Result;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class JSONDeserializer implements JsonDeserializer<Result> {

    @Override
    public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Result response = new Gson().fromJson(json, Result.class);
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("per_facet")) {
            JsonElement elem = jsonObject.get("per_facet");
            if (elem != null && !elem.isJsonNull()) {
                if(elem.isJsonArray()){
                    List<String> list = new ArrayList<>();
                    for (JsonElement value: elem.getAsJsonArray()) {
                        list.add(value.getAsString());
                    }
                    response.setPerFacet(list);
                }else{
                    response.setPerFacetPrimitive(elem.getAsString());
                }
            }
        }
        return response;
    }
}
