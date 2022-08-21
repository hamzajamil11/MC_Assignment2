package haqnawaz.org.a20220815db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "StudentName";
    public static final String STUDENT_ROLL = "StudentRollNumber";
    public static final String STUDENT_ENROLL = "IsEnrolled";
    public static final String STUDENT_TABLE = "StudentTable";



    public DBHelper(@Nullable Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTableSTatementOne = "CREATE TABLE CustTable(CustomerID Integer PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME_FIRST + " Text, CustomerAge Int, ActiveCustomer BOOL) ";
        String createTableSTatement = "CREATE TABLE " + STUDENT_TABLE + "(" +
                STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, "
                + STUDENT_ROLL + " Int, " + STUDENT_ENROLL + " BOOL) ";
        db.execSQL(createTableSTatement);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    public void  addStudent(StudentModel STUDENTModel){
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, STUDENTModel.getName());
        cv.put(STUDENT_ROLL, STUDENTModel.getRollNmber());
        cv.put(STUDENT_ENROLL, STUDENTModel.isEnroll());
        db.insert(STUDENT_TABLE, null, cv);
        db.close();
        //NullCoumnHack
        //long insert =gi
        //if (insert == -1) { return false; }
        //else{return true;}
    }
    public boolean FindStudent(int StudentID)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        String StuID=Integer.toString(StudentID);
        Cursor cursor=db.rawQuery("SELECT * FROM " + STUDENT_TABLE +" WHERE " + STUDENT_ROLL + "=?",new String[]{StuID});

        if(cursor.getCount()>0){
            //Toast.makeText(DBHelper.this,"No Student found",Toast.LENGTH_LONG).show();

            cursor.close();
            return true;
        }
        else
        {
            cursor.close();
            //Toast.makeText(,"No Student found",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public void updateStudent(StudentModel stu,int StudentID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(STUDENT_NAME, stu.getName());
        cv.put(STUDENT_ROLL, stu.getRollNmber());
        cv.put(STUDENT_ENROLL, stu.isEnroll());

        String StuID=Integer.toString(StudentID);
        Cursor cursor=db.rawQuery("SELECT * FROM " + STUDENT_TABLE +" WHERE " + STUDENT_ROLL + "=?",new String[]{StuID});

        if(cursor.getCount()>0){
            long result=db.update(STUDENT_TABLE,cv,"STUDENT_ROLL=?",new String[]{StuID});
        }
        cursor.close();
    }

    public void  deleteStudent(int StudentID){
        SQLiteDatabase db = this.getWritableDatabase();
        String StuID=Integer.toString(StudentID);
        Cursor cursor=db.rawQuery("SELECT * FROM " + STUDENT_TABLE +" WHERE " + STUDENT_ROLL + "=?",new String[]{StuID});

        if(cursor.getCount()>0){
            long result=db.delete(STUDENT_TABLE,"STUDENT_ROLL=?",new String[]{StuID});
        }
        cursor.close();

    }

    public ArrayList<StudentModel> getAllStudents() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);

        ArrayList<StudentModel> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return studentArrayList;
    }

}