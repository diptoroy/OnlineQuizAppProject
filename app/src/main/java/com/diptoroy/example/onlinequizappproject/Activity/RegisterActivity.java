package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.diptoroy.example.onlinequizappproject.Model.SignInUpModel;
import com.diptoroy.example.onlinequizappproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class RegisterActivity extends AppCompatActivity {

    EditText mfullName,muserName,mschool,mphnNum,memail,mpassword;
    CountryCodePicker mCountryCode;
    Spinner mclassName,mdivision,mrefer;

    Button regbtn,backbtn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        mfullName = findViewById(R.id.fullName);
        muserName = findViewById(R.id.userName);
        mschool = findViewById(R.id.schoolName);
        mphnNum = findViewById(R.id.phnNumber);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        regbtn = findViewById(R.id.regbtn);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mCountryCode = findViewById(R.id.country_code);
//        mCountryCode.registerCarrierNumberEditText(mphnNum);
//        mCountryCode.setNumberAutoFormattingEnabled(true);
//        mphnNum.setText(mCountryCode.getFormattedFullNumber());

        mclassName = findViewById(R.id.class_Spinner);
        mdivision = findViewById(R.id.division_spinner);
        mrefer = findViewById(R.id.reference_spinner);


        //class spinner
        ArrayAdapter<CharSequence> clAdaptar = ArrayAdapter.createFromResource(this,R.array.classNumber,android.R.layout.simple_spinner_item);
        clAdaptar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mclassName.setAdapter(clAdaptar);
//        mclassName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
////                String item2 = parent.getItemAtPosition(position).toString();
//
//                String item1 = mclassName.getSelectedItem().toString();
//                mClassedt.setText(item1);
//
////                Toast.makeText(parent.getContext(),"Selected:"+item2,Toast.LENGTH_SHORT ).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        //division spinner
        ArrayAdapter<CharSequence> divAdaptar = ArrayAdapter.createFromResource(this,R.array.division,android.R.layout.simple_spinner_item);
        divAdaptar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mdivision.setAdapter(divAdaptar);


        //refer spinner

        ArrayAdapter<CharSequence> refAdapter = ArrayAdapter.createFromResource(this,R.array.refer,android.R.layout.simple_spinner_item);
        refAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mrefer.setAdapter(refAdapter);



        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SignInUpModel user = new SignInUpModel(mfullName.getText().toString(),muserName.getText().toString(),mschool.getText().toString(),mclassName.getSelectedItem().toString(),
                        mdivision.getSelectedItem().toString(),mphnNum.getText().toString(),memail.getText().toString(),mrefer.getSelectedItem().toString(),mpassword.getText().toString());

                if (mfullName.getText().toString().isEmpty()){
                    mfullName.setError("Insert your Full name!");
                    mfullName.findFocus();
                    return;
                }
                if (muserName.getText().toString().isEmpty()){
                    muserName.setError("Insert your User Name!");
                    muserName.findFocus();
                    return;
                }
                if (mschool.getText().toString().isEmpty()){
                    mschool.setError("Insert your School name!");
                    mschool.findFocus();
                    return;
                }
//                if (mclassName.getSelectedItem().toString().isEmpty()){
//                    mclassName.setEnabled(false);
//                    mclassName.findFocus();
//                    return;
//                }
//                if (mdivision.getSelectedItem().toString().isEmpty()){
//                    mdivision.setError("Put your School name");
//                    mdivision.findFocus();
//                    return;
//                }
                if (mphnNum.getText().toString().isEmpty()){
                    mphnNum.setError("Insert your phone number with country code!");
                    mphnNum.findFocus();
                    return;
                }
                if(mphnNum.length()<14){
                    mphnNum.setError("Invalid format,please insert +88 before the number!");
                    mphnNum.findFocus();
                    return;
                }
                if(mphnNum.length()>15){
                    mphnNum.setError("Invalid Phone number!");
                    mphnNum.findFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(memail.getText().toString()).matches()){
                    memail.setError("Email is not valid!");
                    memail.findFocus();
                    return;
                }
                if (memail.getText().toString().isEmpty()){
                    memail.setError("Insert your Email!");
                    memail.findFocus();
                    return;
                }
//                if (mrefer.getSelectedItem().toString().isEmpty()){
//                    mrefer.setError("Put your School name");
//                    mrefer.findFocus();
//                    return;
//                }
                if (mpassword.getText().toString().isEmpty()){
                    mpassword.setError("Insert your Password!");
                    mpassword.findFocus();
                    return;
                }

                if (mpassword.length()<6){
                    mpassword.setError("Password length should be at least 6 characters!");
                    mpassword.findFocus();
                    return;
                }

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUserName()).exists()){
                            //Toast.makeText(RegisterActivity.this,"User already exists",Toast.LENGTH_LONG).show();
                            muserName.setError("User name already taken!");
                            muserName.findFocus();
                            return;
                        }else{
                            users.child(user.getUserName()).setValue(user);
                            Toast.makeText(RegisterActivity.this,"User registration success!",Toast.LENGTH_LONG).show();
//                            Intent newUser = new Intent(RegisterActivity.this,QuizActivity.class);
//                            startActivity(newUser);
//                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
