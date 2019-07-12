package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.diptoroy.example.onlinequizappproject.Model.Admin;
import com.diptoroy.example.onlinequizappproject.Model.CommonModel;
import com.diptoroy.example.onlinequizappproject.Model.SignInUpModel;
import com.diptoroy.example.onlinequizappproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    //for sign in
    EditText edtUser,edtPwd;
    //for sign up
////    EditText edtNewUser,edtnewPassword,edtnewPhnNum,edtnewEmail;
//    EditText mfullName,muserName,mschool,mphnNum,memail,mpassword;
//    CountryCodePicker mCountryCode;
//    Spinner mclassName,mdivision,mrefer;
//    TextView mClassedt,mDivisionedt,mReferedt;

    Button signIn,signUp;
    TextView forgate;


    //admin panel
    EditText adminName,adminPwd;

    FirebaseDatabase adminP;
    DatabaseReference adminuser;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = findViewById(R.id.signinUserEdit);
        edtPwd = findViewById(R.id.signinUserPwd);

        signIn = findViewById(R.id.signinBtn);
        signUp = findViewById(R.id.signUpBtn);
//        admin = findViewById(R.id.admin);
        forgate = findViewById(R.id.forgate);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        adminP = FirebaseDatabase.getInstance();
        adminuser = adminP.getReference("Admin");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signUpDilog();
                Intent reg = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(reg);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInMethod(edtUser.getText().toString(),edtPwd.getText().toString());
            }
        });

//        admin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adminPanel();
//            }
//        });

        forgate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ForgatePassword.class));
                finish();
            }
        });
    }

//    private void adminPanel() {
//
//        AlertDialog.Builder malertDialog = new AlertDialog.Builder(MainActivity.this);
//        malertDialog.setTitle("Sign Up");
//        malertDialog.setMessage("Please fill your information");
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View adminLay = inflater.inflate(R.layout.adminpanel,null);
//
//        adminName = adminLay.findViewById(R.id.adminSigninUserEdit);
//        adminPwd = adminLay.findViewById(R.id.adminSigninUserPwd);
//
//
//
//        malertDialog.setView(adminLay);
//        malertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);
//
//        malertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//
//        malertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                adminuser.addListenerForSingleValueEvent(new ValueEventListener() {
//                    final Admin admin = new Admin(adminName.getText().toString(),adminPwd.getText().toString());
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                        if (dataSnapshot.child(adminU).exists()){
////
////                            if (!adminU.isEmpty()){
////
////                                Admin adminLogin = dataSnapshot.child(adminU).getValue(Admin.class);
////                                if (adminLogin.getPassword().equals(adminPassword)){
////                                    Toast.makeText(MainActivity.this,"Login ok!",Toast.LENGTH_LONG).show();
////                                    Intent home = new Intent(MainActivity.this,HomeActivity.class);
////                                    CommonModel.currentAdmin = adminLogin;
////                                    startActivity(home);
////                                    finish();
////                                }
////
////                                else
////                                    Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_LONG).show();
////                            }else{
////                                Toast.makeText(MainActivity.this,"Please enter your user name",Toast.LENGTH_LONG).show();
////                            }
////                        }
////                        else
////                            Toast.makeText(MainActivity.this,"User is not exists",Toast.LENGTH_LONG).show();
//                        if (dataSnapshot.child(admin.getUserName()).exists()){
//                            Toast.makeText(MainActivity.this,"User already exists",Toast.LENGTH_LONG).show();
//                            Intent home = new Intent(MainActivity.this,AdminRanking.class);
//                                    CommonModel.currentAdmin = admin;
//                                    startActivity(home);
//                                    finish();
//                        }else{
//                            users.child(admin.getUserName()).setValue(admin);
//                            Toast.makeText(MainActivity.this,"User registration success!",Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                dialogInterface.dismiss();
//            }
//        });
//        malertDialog.show();
//
//
//
//
//    }


    private void signInMethod(final String user, final String pwd) {

        if (user.isEmpty()){
            edtUser.setError("Please put your User name");
            edtUser.findFocus();
            return;
        }

        if (pwd.isEmpty()){
            edtPwd.setError("Please put your password");
            edtPwd.findFocus();
            return;
        }

        if (pwd.length()<6){
            edtPwd.setError("Password length should be minimum 6");
            edtPwd.findFocus();
            return;
        }

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user).exists()){

                    if (!user.isEmpty()){

                        SignInUpModel login = dataSnapshot.child(user).getValue(SignInUpModel.class);
                        if (login.getPassword().equals(pwd)){
                            Toast.makeText(MainActivity.this,"Login ok!",Toast.LENGTH_LONG).show();
                            Intent home = new Intent(MainActivity.this,QuizActivity.class);
                            CommonModel.currentUser = login;
                            startActivity(home);
                            finish();
                        }

                        else
                            Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Please enter your user name",Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"User is not exists",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void signUpDilog() {
//
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//        alertDialog.setTitle("Register");
//        alertDialog.setMessage("Please fill your information");
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View signUpLayout = inflater.inflate(R.layout.signuplayout,null);
//
////        edtNewUser = signUpLayout.findViewById(R.id.signUpEdit);
////        edtnewEmail = signUpLayout.findViewById(R.id.signoutemailEdit);
////        edtnewPhnNum = signUpLayout.findViewById(R.id.signupphnEdit);
////        edtnewPassword = signUpLayout.findViewById(R.id.signUpPwd);
//
//        mfullName = signUpLayout.findViewById(R.id.fullName);
//        muserName = signUpLayout.findViewById(R.id.userName);
//        mschool = signUpLayout.findViewById(R.id.schoolName);
//        mphnNum = signUpLayout.findViewById(R.id.phnNumber);
//        memail = signUpLayout.findViewById(R.id.email);
//        mpassword = signUpLayout.findViewById(R.id.password);
//
//        mCountryCode = signUpLayout.findViewById(R.id.country_code);
//        mCountryCode.registerCarrierNumberEditText(mphnNum);
////        mCountryCode.setNumberAutoFormattingEnabled(true);
////        mphnNum.setText(mCountryCode.getFormattedFullNumber());
//
//        mclassName = signUpLayout.findViewById(R.id.class_Spinner);
//        mdivision = signUpLayout.findViewById(R.id.division_spinner);
//        mrefer = signUpLayout.findViewById(R.id.reference_spinner);
//
//        mClassedt = signUpLayout.findViewById(R.id.classEdt);
//        mDivisionedt = signUpLayout.findViewById(R.id.divisionedt);
//        mReferedt = signUpLayout.findViewById(R.id.referedt);
//
//        //class spinner
//        ArrayAdapter<CharSequence> clAdaptar = ArrayAdapter.createFromResource(this,R.array.classNumber,android.R.layout.simple_spinner_item);
//        clAdaptar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mclassName.setAdapter(clAdaptar);
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
//
//
//        //division spinner
//        ArrayAdapter<CharSequence> divAdaptar = ArrayAdapter.createFromResource(this,R.array.division,android.R.layout.simple_spinner_item);
//        divAdaptar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mdivision.setAdapter(divAdaptar);
//        mdivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String di = mdivision.getSelectedItem().toString();
//                mDivisionedt.setText(di);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        //refer spinner
//
//        ArrayAdapter<CharSequence> refAdapter = ArrayAdapter.createFromResource(this,R.array.refer,android.R.layout.simple_spinner_item);
//        refAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mrefer.setAdapter(refAdapter);
//        mrefer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                String item3 = mrefer.getSelectedItem().toString();
//                mReferedt.setText(item3);
////                Toast.makeText(parent.getContext(),"Selected:"+item2,Toast.LENGTH_SHORT ).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        alertDialog.setView(signUpLayout);
//        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);
//
//        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//
//        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                final SignInUpModel user = new SignInUpModel(mfullName.getText().toString(),muserName.getText().toString(),mschool.getText().toString(),mClassedt.getText().toString(),
//                        mDivisionedt.getText().toString(),mphnNum.getText().toString(),memail.getText().toString(),mReferedt.getText().toString(),mpassword.getText().toString());
//                users.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.child(user.getUserName()).exists()){
//                            Toast.makeText(MainActivity.this,"User already exists",Toast.LENGTH_LONG).show();
//                        }else{
//                            users.child(user.getUserName()).setValue(user);
//                            Toast.makeText(MainActivity.this,"User registration success!",Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                dialogInterface.dismiss();
//            }
//        });
//        alertDialog.show();
//
//
//
//    }
}
