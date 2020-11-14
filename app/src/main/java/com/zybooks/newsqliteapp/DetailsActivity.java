package com.zybooks.newsqliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public static String EXTRA_CLASS_ID;
    private Course mClass;
    private CourseDatabase mClassDB;
    private int mClassID;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mClass = new Course();
        mClassDB = CourseDatabase.getInstance(getApplicationContext());

        //string = getIntent().getStringExtra("EXTRA_SESSION_ID");

        mClassID = MainActivity.getInt();
        string = String.valueOf(mClassID);

        mClass = mClassDB.getCourse(string);

        TextView nameTextView = findViewById(R.id.className);
        nameTextView.setText(mClass.getName());

        TextView descriptionTextView = findViewById(R.id.classDescription);
        descriptionTextView.setText(mClass.getDescription());

        TextView locationTextView = findViewById(R.id.classLocation);
        descriptionTextView.setText(mClass.getLocation());

        TextView dayTimeTextView = findViewById(R.id.classDaysTimes);
        descriptionTextView.setText(mClass.getDaysTimes());

        TextView instructorTextView = findViewById(R.id.classInstructor);
        descriptionTextView.setText(mClass.getInstructor());
    }


}