package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diptoroy.example.onlinequizappproject.Model.SignInUpModel;
import com.diptoroy.example.onlinequizappproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgatePassword extends AppCompatActivity {

    EditText name;
    Button  show,back;

    TextView uname,password;

    FirebaseDatabase database;
    DatabaseReference showme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgate_password);

        name = findViewById(R.id.user);
        show = findViewById(R.id.reset);
        back = findViewById(R.id.backre);

        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);

        database = FirebaseDatabase.getInstance();
        showme = database.getReference("Users");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass(name.getText().toString().trim());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    private void pass(final String user) {

        if (user.isEmpty()){
            name.setError("Put your email!");
            name.findFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            uname.setError("Email is not valid");
            uname.findFocus();
            return;
        }

       showme.orderByChild("emailAddress").equalTo(user).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()){
                   Toast.makeText(getApplicationContext(),"User exists",Toast.LENGTH_LONG).show();
                   for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                       SignInUpModel detail = postSnapshot.getValue(SignInUpModel.class);

                       String username = detail.getUserName();
                       String upassword = detail.getPassword();


                       uname.setText("User name : "+username);
                       password.setText("Password : "+upassword);



                   }
               }else{
                   Toast.makeText(getApplicationContext(),"User not exists",Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
