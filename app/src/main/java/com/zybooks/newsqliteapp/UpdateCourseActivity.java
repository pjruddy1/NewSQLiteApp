package com.zybooks.newsqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCourseActivity extends AppCompatActivity {

    public static final String EXTRA_CLASS_ID = "Course Name";
    private EditText mNameEdit;
    private EditText mDescEdit;
    private EditText mLocEdit;
    private EditText mDayTimeEdit;
    private EditText mInstructorEdit;
    private Button mInsertButton;
    private CourseDatabase mClassDB;
    public Toast toast;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        mNameEdit = findViewById(R.id.update_name);
        mDescEdit = findViewById(R.id.update_description);
        mLocEdit = findViewById(R.id.update_location);
        mDayTimeEdit = findViewById(R.id.update_daytime);
        mInstructorEdit = findViewById(R.id.update_instructor);

        Course cls = new Course();
        mClassDB = CourseDatabase.getInstance(getApplicationContext());

        string = getIntent().getStringExtra("EXTRA_CLASS_ID");
        try {
            cls = mClassDB.getCourse(string);
        }catch (Exception e){
            toast = Toast.makeText(getApplicationContext(),"Failed to load Class",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }


        mNameEdit.setText(cls.getName());
        mDescEdit.setText(cls.getDescription());
        mLocEdit.setText(cls.getLocation());
        mDayTimeEdit.setText(cls.getDaysTimes());
        mInstructorEdit.setText(cls.getInstructor());

    }

    public void UpdateClick(View view) {
        mNameEdit = findViewById(R.id.update_name);
        mDescEdit = findViewById(R.id.update_description);
        mLocEdit = findViewById(R.id.update_location);
        mDayTimeEdit = findViewById(R.id.update_daytime);
        mInstructorEdit = findViewById(R.id.update_instructor);
        Course cls = new Course(mNameEdit.getText().toString(),mDescEdit.getText().toString(),mLocEdit.getText().toString(),mDayTimeEdit.getText().toString(),mInstructorEdit.getText().toString());
        try {
            mClassDB.getInstance(getApplicationContext()).updateCourse(cls);
            toast= Toast.makeText(getApplicationContext(),"Class Upadeted",Toast.LENGTH_SHORT);
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

            toast = Toast.makeText(getApplicationContext(),"Failed to update Class",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }
    }
}