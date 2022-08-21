package haqnawaz.org.a20220815db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class del extends AppCompatActivity {

    Button buttonViewAll;
    ListView listViewStudentDel;
    Button buttonDeleteRecord;
    EditText editRollNumberDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);

        buttonViewAll = findViewById(R.id.buttonViewAllDel);
        listViewStudentDel = findViewById(R.id.listViewStudentDel);
        buttonDeleteRecord=findViewById(R.id.buttonDelDel);
        editRollNumberDel = findViewById(R.id.RollNumberDel);


        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(del.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
                        (del.this, android.R.layout.simple_list_item_1,list);
                listViewStudentDel.setAdapter(arrayAdapter);

            }
        });

        buttonDeleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper(del.this);

                int rollNumber = Integer.parseInt(editRollNumberDel.getText().toString());


                boolean result=dbHelper.FindStudent(rollNumber);

                if(result==true){
                    Toast.makeText(del.this, "Student Found", Toast.LENGTH_SHORT).show();
                    dbHelper.deleteStudent(rollNumber);
                    Toast.makeText(del.this, "Student Deleted", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(del.this, "Student Not Found, try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}