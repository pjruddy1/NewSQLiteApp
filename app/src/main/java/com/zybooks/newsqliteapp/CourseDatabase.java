package com.zybooks.newsqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CourseDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "course.db";
    private static CourseDatabase mClassDb;

    private static final class CourseTable {
        private static final String TABLE = "classes";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_DESC = "description";
        private static final String COL_LOC = "location";
        private static final String COL_DAYTIME = "daystimes";
        private static final String COL_INSTRUCTOR = "instructor";
    }

    public static CourseDatabase getInstance(Context context) {
        if (mClassDb == null) {
            mClassDb = new CourseDatabase(context);
        }
        return mClassDb;
    }

    private CourseDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create classes table
        db.execSQL("create table " + CourseTable.TABLE + " (" +
                CourseTable.COL_ID + " integer primary key autoincrement, " +
                CourseTable.COL_NAME + ", " +
                CourseTable.COL_DESC + ", " +
                CourseTable.COL_LOC + ", " +
                CourseTable.COL_DAYTIME + ", " +
                CourseTable.COL_INSTRUCTOR + ")");

        ContentValues values = new ContentValues();
        values.put(CourseTable.COL_NAME, "ISYS 221");
        values.put(CourseTable.COL_DESC, "Android Mobile Dev");
        values.put(CourseTable.COL_LOC, "Online");
        values.put(CourseTable.COL_DAYTIME, "Everyday");
        values.put(CourseTable.COL_INSTRUCTOR, "Travis Bussler");
        db.insert(CourseTable.TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + CourseTable.TABLE);
        onCreate(db);
    }

    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from " + CourseTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(Integer.parseInt(cursor.getString(0)));
                course.setName(cursor.getString(1));
                course.setDescription(cursor.getString(2));
                course.setLocation(cursor.getString(3));
                course.setDaysTimes(cursor.getString(4));
                course.setInstructor(cursor.getString(5));
                courses.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return courses;
    }

    public boolean addCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CourseTable.COL_NAME, course.getName());
        values.put(CourseTable.COL_DESC, course.getDescription());
        values.put(CourseTable.COL_LOC, course.getLocation());
        values.put(CourseTable.COL_DAYTIME, course.getDaysTimes());
        values.put(CourseTable.COL_INSTRUCTOR, course.getInstructor());
        long id = db.insert(CourseTable.TABLE, null, values);
        return id != -1;
    }

    public void updateCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CourseTable.COL_NAME, course.getName());
        values.put(CourseTable.COL_DESC, course.getDescription());
        values.put(CourseTable.COL_LOC, course.getLocation());
        values.put(CourseTable.COL_DAYTIME, course.getDaysTimes());
        values.put(CourseTable.COL_INSTRUCTOR, course.getInstructor());
        db.update(CourseTable.TABLE, values,
                CourseTable.COL_NAME + " = ?", new String[] { course.getName() });
    }

    public void deleteCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CourseTable.TABLE,
                CourseTable.COL_NAME + " = ?", new String[] { course.getName() });
    }

    public Course getCourse(String questionID) {

        SQLiteDatabase db = mClassDb.getWritableDatabase();
        String sql = "select * from " + CourseTable.TABLE +
                " where " + CourseTable.COL_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { questionID });

        if (cursor.moveToFirst()) {
            Course course = new Course();
            course.setName(cursor.getString(1));
            course.setDescription(cursor.getString(2));
            course.setLocation(cursor.getString(3));
            course.setDaysTimes(cursor.getString(4));
            course.setInstructor(cursor.getString(5));
            return course;
        }
        else return null;

    }
}