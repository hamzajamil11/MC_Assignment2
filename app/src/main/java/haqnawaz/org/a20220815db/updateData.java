package haqnawaz.org.a20220815db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class updateData extends AppCompatActivity {

    Button buttonUpdateData, buttonViewAllUpdateData;
    EditText editTextNameUpdateData, studentRollNumberUpdateData;
    Switch switchStudentUpdate;
    ListView listViewStudentUpdateData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        buttonUpdateData=findViewById(R.id.buttonUpdateData);
        buttonViewAllUpdateData=findViewById(R.id.buttonViewAllUpdateData);
        editTextNameUpdateData=findViewById(R.id.editTextNameUpdateData);
        studentRollNumberUpdateData=findViewById(R.id.StudentRollNumberUpdateData);
        switchStudentUpdate=findViewById(R.id.switchStudentUpdate);
        listViewStudentUpdateData=findViewById(R.id.listViewStudentUpdateData);

        buttonViewAllUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(updateData.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
                        (updateData.this, android.R.layout.simple_list_item_1,list);
                listViewStudentUpdateData.setAdapter(arrayAdapter);
            }
        });

        buttonUpdateData.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModelUpdate;

            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();
                int rollNum=bundle.getInt("rollNumber");

                try {
                    studentModelUpdate = new StudentModel(editTextNameUpdateData.getText().toString(), Integer.parseInt(studentRollNumberUpdateData.getText().toString()), switchStudentUpdate.isChecked());
                    Toast.makeText(updateData.this, studentModelUpdate.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(updateData.this, "Error", Toast.LENGTH_SHORT).show();
                }

                DBHelper dbHelper = new DBHelper(updateData.this);
                dbHelper.updateStudent(studentModelUpdate,rollNum);
                Toast.makeText(updateData.this, "Updated Successfully", Toast.LENGTH_SHORT).show();





            }
        });
    }
}