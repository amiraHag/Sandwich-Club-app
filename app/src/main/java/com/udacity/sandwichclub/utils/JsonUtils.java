package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject mainJsonObject = new JSONObject(json);

            JSONObject name = mainJsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>(alsoKnownAsArray.length());
            for (int j = 0; j < alsoKnownAsArray.length(); j++) {

                alsoKnownAs.add(alsoKnownAsArray.getString(j));
            }



            String placeOfOrigin = mainJsonObject.optString("placeOfOrigin");

            String description = mainJsonObject.getString("description");

            String image = mainJsonObject.getString("image");

            JSONArray ingredientsArray = mainJsonObject.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>(ingredientsArray.length());
            for (int j = 0; j < ingredientsArray.length(); j++) {
                alsoKnownAs.add(ingredientsArray.getString(j));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException error) {
            Log.e("Error", error.getMessage());
            return null;
        }
    }

}
