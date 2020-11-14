package com.zybooks.newsqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCourseActivity extends AppCompatActivity {

    private EditText mNameEdit;
    private EditText mDescEdit;
    private EditText mLocEdit;
    private EditText mDayTimeEdit;
    private EditText mInstructorEdit;
    private Button mInsertButton;
    private CourseDatabase mClassDB;
    public Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

    }


    public void InsertClick(View view) {
        mNameEdit = findViewById(R.id.course_name);
        mDescEdit = findViewById(R.id.course_description);
        mLocEdit = findViewById(R.id.course_location);
        mDayTimeEdit = findViewById(R.id.course_daytime);
        mInstructorEdit = findViewById(R.id.course_instructor);
        Course cls = new Course(mNameEdit.getText().toString(),mDescEdit.getText().toString(),mLocEdit.getText().toString(),mDayTimeEdit.getText().toString(),mInstructorEdit.getText().toString());
        try {
            mClassDB.getInstance(getApplicationContext()).addCourse(cls);
            toast=Toast.makeText(getApplicationContext(),"Class Added",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch (Exception e){
            mNameEdit.setText("");
            mDescEdit.setText("");
            mLocEdit.setText("");
            mDayTimeEdit.setText("");
            mInstructorEdit.setText("");

            toast = Toast.makeText(getApplicationContext(),"Failed to Add Class",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }

    }
}