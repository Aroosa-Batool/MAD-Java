package com.example.realtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database =    FirebaseDatabase.getInstance();
        DatabaseReference myRef =  database.getReference("LionDatabase");



        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //myRef.setValue("Hello, Pakistan!");

                Employee e1 = new Employee(1,"Khalid","CS", 5002.1);
                Employee e2 = new Employee(2,"Majid","CE", 5102.1);
                Employee e3 = new Employee(3,"Maria","EE", 5402.1);


                myRef.child("employees").child("1").setValue(e1);
                myRef.child("employees").child("2").setValue(e2);
                myRef.child("employees").child("3").setValue(e3);

            }
        });


        TextView t = findViewById(R.id.text);
        Button b1 = findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child("employees").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("****","**** DATA CHANGED");
                        for (DataSnapshot messageSnapshot: snapshot.getChildren()) {
                            String name=(String)messageSnapshot.child("name").getValue();
                            String dep = (String)messageSnapshot.child("dept").getValue();
                            String id = (String)messageSnapshot.child("id").getValue().toString();
                            String sal = (String)messageSnapshot.child("salary").getValue().toString();
                            t.append("id : "+id  +"\nName:"+name+"\nSalary : "+dep.toString()+ "\nSalary : "+sal.toString() +"\n\n\n" );
                            Log.d("****","id : "+id  +"  NAME:"+name+", Salary : " +dep.toString()+ "Salary : "+sal.toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

            }
        });



    }
}




