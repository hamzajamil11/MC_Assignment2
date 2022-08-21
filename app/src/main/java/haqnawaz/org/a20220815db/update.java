package haqnawaz.org.a20220815db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class update extends AppCompatActivity {

    Button buttonViewAllUpdate;
    ListView listViewStudentUpdate;
    Button buttonUpdateRecord;
    EditText editIdUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        buttonViewAllUpdate=findViewById(R.id.buttonViewAllUpdate);
        listViewStudentUpdate=findViewById(R.id.listViewStudentUpdate);
        buttonUpdateRecord=findViewById(R.id.buttonUpdate);
        editIdUpdate=findViewById(R.id.StudentIdUpdate);

        buttonViewAllUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(update.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
                        (update.this, android.R.layout.simple_list_item_1,list);
                listViewStudentUpdate.setAdapter(arrayAdapter);

            }
        });

        buttonUpdateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper(update.this);
                int rollNumber = Integer.parseInt(editIdUpdate.getText().toString());
                boolean result=dbHelper.FindStudent(rollNumber);
                if(result){
                    Toast.makeText(update.this, "Student Found", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(update.this, updateData.class);
                    i.putExtra("rollNumber",rollNumber);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(update.this, "Student Not Found, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}