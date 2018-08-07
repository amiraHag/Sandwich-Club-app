package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

   private static Sandwich sSandwich = null;

   private static String sMainName;
   private static String sImage;
   private static List<String> sAlsoKnownAs;
   private static String sPlaceOfOrigin;
   private static String sDescription;
   private static List<String> sIngredients;

    public static Sandwich parseSandwichJson(String json) {


        try {

            JSONObject mainObject = new JSONObject(json);

            JSONObject name = mainObject.getJSONObject("name");

            //set value for strings
            sImage = mainObject.getString("image");
            sMainName = name.getString("mainName");
            sPlaceOfOrigin = mainObject.optString("placeOfOrigin");
            sDescription = mainObject.getString("description");

            //set value for lists
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            sAlsoKnownAs = new ArrayList<>(alsoKnownAsArray.length());
            for (int j = 0; j < alsoKnownAsArray.length(); j++) {

                sAlsoKnownAs.add(alsoKnownAsArray.getString(j));
            }

            JSONArray ingredientsArray = mainObject.getJSONArray("ingredients");
            sIngredients = new ArrayList<>(ingredientsArray.length());
            for (int i = 0; i < ingredientsArray.length(); i++) {
                sIngredients.add(ingredientsArray.getString(i));
            }

            //set the value for the sandwich object
            sSandwich = new Sandwich(sMainName, sAlsoKnownAs, sPlaceOfOrigin, sDescription, sImage, sIngredients);

        } catch (JSONException error) {
            Log.e("Error", error.getMessage());

        }
        return sSandwich;
    }


}
