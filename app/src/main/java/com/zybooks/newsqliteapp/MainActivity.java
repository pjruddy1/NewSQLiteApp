package com.zybooks.newsqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CourseDatabase mCourseDB;
    private List<Course> mCourses;
    private ListView mListView;
    public Toast toast;
    private int mCourseID;
    private TextView mIdTextView;
    static int statInt;
    String[] namesArray;
    int[] idArray;
    private String mString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCourseDB = CourseDatabase.getInstance(getApplicationContext());
        mCourses = mCourseDB.getCourses();

        mListView = findViewById(R.id.scheduleList);

        namesArray = new String[mCourses.size()];
        idArray = new int[mCourses.size()];

        for (int i = 0; i < mCourses.size(); i++) {
            namesArray[i] = mCourses.get(i).getName();
            idArray[i] = mCourses.get(i).getId();

        }

        CustomAdapater customAdapter = new CustomAdapater(getApplicationContext(), namesArray, idArray);
        mListView.setAdapter(customAdapter);

        registerForContextMenu(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick(view);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        mString = String.valueOf(v.getId());

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:

               Course course = new Course();
               mCourseDB = CourseDatabase.getInstance(getApplicationContext());
               course = mCourseDB.getCourse(mString);

               try {
                   mCourseDB.getInstance(getApplicationContext()).deleteCourse(course);
                   toast= Toast.makeText(getApplicationContext(),"Class Deleted",Toast.LENGTH_SHORT);
                   toast.setMargin(50,50);
                   toast.show();
                   Intent intent = new Intent(this, MainActivity.class);
                   startActivity(intent);

               }catch (Exception e){
                   toast = Toast.makeText(getApplicationContext(),"Failed to Delete Class",Toast.LENGTH_SHORT);
                   toast.setMargin(50,50);
                   toast.show();
               }
                return true;

            case R.id.update:
                mCourseDB = CourseDatabase.getInstance(getApplicationContext());
                course = mCourseDB.getCourse(mString);
                Intent intent = new Intent(this, UpdateCourseActivity.class);
                intent.putExtra(UpdateCourseActivity.EXTRA_CLASS_ID, String.valueOf(course.getId()));
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onClick(View view){

        mCourseID = view.getLabelFor();

        statInt = mCourseID;
        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_CLASS_ID, String.valueOf(mCourseID));
        startActivity(intent);
    }

    public static int getInt(){
        return statInt;
    }

    public void insertClick(View view) {
        Intent intent = new Intent(this, NewCourseActivity.class);
        startActivity(intent);
    }
}