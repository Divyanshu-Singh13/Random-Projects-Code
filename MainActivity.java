package com.example.to_do_list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tasktitle ,taskdesc, task_id;
    Button add, update, view, delete,logout;
    DataBaseTask mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DataBaseTask(this);
        tasktitle = (EditText) findViewById(R.id.taskname);
        task_id = (EditText) findViewById(R.id.taskid);
        taskdesc = (EditText) findViewById(R.id.taskdetails);
        add = (Button) findViewById(R.id.btn_add);
        update = (Button) findViewById(R.id.btn_upgrade);
        logout = (Button) findViewById(R.id.btn_logout);
        view = (Button) findViewById(R.id.btn_view);
        delete = (Button) findViewById(R.id.btn_delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tasktitle.getText().toString().equals("") || taskdesc.getText().toString().equals("") || task_id.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please Fill the Fields", Toast.LENGTH_LONG).show();

                } else {

                    boolean isinserted = mydb.insertData(tasktitle.getText().toString(), taskdesc.getText().toString(), task_id.getText().toString());
                    if (isinserted == true) {

                        Toast.makeText(MainActivity.this, "Task added in TO-DO List", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Task cannot be added", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedrows = mydb.deleteData(tasktitle.getText().toString(), taskdesc.getText().toString(), task_id.getText().toString());
                if (deletedrows > 0) {
                    Toast.makeText(MainActivity.this, "Task deleted Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Unable to delete Task", Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor data = mydb.viewData();
                if (data.getCount() == 0) {
                    //message
                    message("0 Task Found", "Create task to see it here!!");
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (data.moveToNext()) {

                        buffer.append("Task_ID :" + data.getString(0) + "\n");
                        buffer.append("Task_Title :" + data.getString(1) + "\n");
                        buffer.append("Task_Details :" + data.getString(2) + "\n\n");
                    }
                    message(data.getCount() + " Task Found", buffer.toString());

                }

            }
        });

       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (tasktitle.getText().toString().equals("") || taskdesc.getText().toString().equals("") || task_id.getText().toString().equals(""))
               {
                   Toast.makeText(MainActivity.this, "Please Fill the Fields", Toast.LENGTH_LONG).show();
               }
               else
               {
                   boolean isupdate = mydb.updateData(tasktitle.getText().toString(), taskdesc.getText().toString(), task_id.getText().toString());
                   if (isupdate == true)
                   {
                       Toast.makeText(MainActivity.this, "Task Updated Successfully", Toast.LENGTH_LONG).show();

                   }
                   else
                   {
                       Toast.makeText(MainActivity.this, "Task Cannot be Updated", Toast.LENGTH_LONG).show();

                   }
               }

           }
       });


        }







    public void message(String t_name, String t_mes)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(t_name);
        builder.setMessage(t_mes);
        builder.show();

    }

    public void btn_logout(View view) {
        startActivity(new Intent(getApplicationContext(),Login_form.class));
    }
}


