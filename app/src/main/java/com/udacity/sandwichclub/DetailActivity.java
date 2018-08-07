package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    //Declare views that will hold the data
    private Sandwich mSandwich;
    private ImageView mIngredientsIv;
    private TextView mMainNameTv;
    private TextView mMainNameLb;
    private TextView mAlsoKnownAsTv;
    private TextView mAlsoKnownAsLb;
    private TextView mPlaceOfOriginTv;
    private TextView mPlaceOfOriginLb;
    private TextView mIngredientLb;
    private TextView mIngredientTv;
    private TextView mDescriptionLb;
    private TextView mDescriptionTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initialize the variabls
        mIngredientsIv = (ImageView) findViewById(R.id.image_ingredients_iv);
        mMainNameLb =(TextView) findViewById(R.id.main_name_lb);
        mMainNameTv =(TextView) findViewById(R.id.main_name_tv);
        mAlsoKnownAsLb =(TextView) findViewById(R.id.also_known_as_lb);
        mAlsoKnownAsTv =(TextView) findViewById(R.id.also_known_as_tv);
        mPlaceOfOriginLb =(TextView) findViewById(R.id.place_of_origin_lb);
        mPlaceOfOriginTv =(TextView) findViewById(R.id.place_of_origin_tv);
        mIngredientLb =(TextView) findViewById(R.id.ingredients_lb);
        mIngredientTv =(TextView) findViewById(R.id.ingredients_tv);
        mDescriptionLb =(TextView) findViewById(R.id.description_lb);
        mDescriptionTv =(TextView) findViewById(R.id.description_tv);




        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(mIngredientsIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {


        // Set value to Text
        setTextToTextView(mSandwich.getMainName(), mMainNameTv, mMainNameLb);
        setTextToTextView(mSandwich.getPlaceOfOrigin(), mPlaceOfOriginTv, mPlaceOfOriginLb);
        setTextToTextView(mSandwich.getDescription(), mDescriptionTv, mDescriptionLb);


        // Set value to list
        setListToTextView(mSandwich.getAlsoKnownAs(), mAlsoKnownAsTv, mAlsoKnownAsLb);
        setListToTextView(mSandwich.getIngredients(), mIngredientTv, mIngredientLb);



    }
    private void setTextToTextView(String textValue, TextView viewTv, View viewLb) {
        if (textValue.isEmpty() ) {
            viewTv.setVisibility(View.GONE);
            viewLb.setVisibility(View.GONE);
        } else {
            viewTv.setText(textValue);
        }
    }

    private void setListToTextView(List<String> listValue, TextView viewTv, View viewLb) {
        if (listValue == null) {
            viewTv.setVisibility(View.GONE);
            viewLb.setVisibility(View.GONE);
        } else {
            if(listValue.size() > 0) {
                StringBuilder listString = new StringBuilder();


                for (int i = 0; i < listValue.size(); i++) {

                    listString.append(listValue.get(i));
                    if(i < (listValue.size()-1)) {
                        listString.append("-" );
                    }
                }
                viewTv.setText(listString.toString());
            }
            else{
                viewTv.setText("Unknown");

            }
        }

    }

}


