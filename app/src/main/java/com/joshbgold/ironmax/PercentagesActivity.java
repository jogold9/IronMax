package com.joshbgold.ironmax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

public class PercentagesActivity extends MainActivity {
    private String exercise_name = "jumping jack";
    private String personalRecordString = "";
    private Integer personal_record = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percentages_layout);

        final TextView exerciseName = (TextView) findViewById(R.id.exercise_name);
        final TextView oneHundredTwoPercent = (TextView) findViewById(R.id.oneHundredTwoPercentValue);
        final TextView oneHundredPercent = (TextView) findViewById(R.id.oneHundredPercentValue);
        final TextView ninetyFivePercent = (TextView) findViewById(R.id.ninetyFivePercentValue);
        final TextView ninetyPercent = (TextView) findViewById(R.id.ninetyPercentValue);
        final TextView eightyFivePercent = (TextView) findViewById(R.id.eightyFivePercentValue);
        final TextView eightyPercent = (TextView) findViewById(R.id.eightyPercentValue);
        final TextView seventyFivePercent = (TextView) findViewById(R.id.seventyFivePercentValue);
        final TextView seventyPercent = (TextView) findViewById(R.id.seventyPercentValue);
        final TextView sixtyFivePercent = (TextView) findViewById(R.id.sixtyFivePercentValue);
        final TextView sixtyPercent = (TextView) findViewById(R.id.sixtyPercentValue);
        final TextView fiftyFivePercent = (TextView) findViewById(R.id.fiftyFivePercentValue);
        final TextView fiftyPercent = (TextView) findViewById(R.id.fiftyPercentValue);

        assert getSupportActionBar() != null;
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.barbell);
        actionBar.setTitle("  " + "Iron Max");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            exercise_name = (String) bundle.get("exerciseName");
            exerciseName.setText(exercise_name);
            personalRecordString = (String) bundle.get("personalRecord");
            personal_record = parseStringToInt(personalRecordString);

            oneHundredTwoPercent.setText(((int) (personal_record * 1.02)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            oneHundredPercent.setText(personal_record + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            ninetyFivePercent.setText(((int) (personal_record * 0.95)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            ninetyPercent.setText(((int) (personal_record * 0.90)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            eightyFivePercent.setText(((int) (personal_record * 0.85)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            eightyPercent.setText(((int) (personal_record * 0.80)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            seventyFivePercent.setText(((int) (personal_record * 0.75)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            seventyPercent.setText(((int) (personal_record * 0.70)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            sixtyFivePercent.setText(((int) (personal_record * 0.65))  + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            sixtyPercent.setText(((int) (personal_record * 0.60)) +  " lb / " + Math.round(personal_record / 2.2046) + "kg");
            fiftyFivePercent.setText(((int) (personal_record * 0.55)) + " lb / " + Math.round(personal_record / 2.2046) + "kg");
            fiftyPercent.setText(((int) (personal_record * 0.50)) +  " lb / " + Math.round(personal_record / 2.2046) + "kg");
        }

    }

    private Integer parseStringToInt(String personalRecordString) {
        Integer result = 100;
        result = Integer.valueOf((personalRecordString.replaceAll("[^\\d.]", "")));  //removes non-numeric chars, converts to int
        return result;
    }
}
