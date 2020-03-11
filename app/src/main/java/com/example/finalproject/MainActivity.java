package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {



    EditText user;
    EditText pass;
    TextView register;
    Button login;
    DatabaseLogin db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        db = new DatabaseLogin(this);
        login = findViewById(R.id.login);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.register);

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long [] pattern = {0, 500};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String admin_user = "admin";
                String admin_pass =  "admin";
                String user_name = user.getText().toString().trim();
                String pwd = pass.getText().toString().trim();
                Boolean res = db.checkUser(user_name, pwd);
                Boolean check = db.check(user_name);
                Boolean checkpass = db.checkPass(pwd);


                if(res){
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                    user.setBackgroundResource(R.drawable.correct_stroke);
                    pass.setBackgroundResource(R.drawable.correct_stroke);
                    Intent i = new Intent(getApplicationContext(), MainMenu.class);
                    i.putExtra("user_name", user_name);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }

                else if(check && !checkpass){
                    Toast.makeText(MainActivity.this, getString(R.string.incorrect_pass), Toast.LENGTH_SHORT).show();
                    user.setBackgroundResource(R.drawable.black_stroke);
                    pass.setBackgroundResource(R.drawable.error_storke);
                    pass.setError(getString(R.string.incorrect_pass));
                    vibrator.vibrate(pattern, -1);

                }

                else if(user.getText().length() == 0 && pass.getText().length() == 0){
                    user.setError(getString(R.string.required));
                    user.setBackgroundResource(R.drawable.error_storke);
                    pass.setError(getString(R.string.required));
                    pass.setBackgroundResource(R.drawable.error_storke);
                    vibrator.vibrate(pattern, -1);

                }

                else if(user.getText().length() > 1 && pass.getText().length() == 0){
                    user.setBackgroundResource(R.drawable.black_stroke);
                    pass.setError(getString(R.string.required));
                    pass.setBackgroundResource(R.drawable.error_storke);
                    vibrator.vibrate(pattern, -1);

                }


                else if(pass.getText().length() > 1 && user.getText().length() == 0) {
                    pass.setBackgroundResource(R.drawable.black_stroke);
                    user.setError(getString(R.string.required));
                    user.setBackgroundResource(R.drawable.error_storke);
                    vibrator.vibrate(pattern, -1);

                }
                else if(pass.getText().equals(admin_pass) && user.getText().equals(admin_user)){
                    db.deleteAll();
                    Toast.makeText(getApplicationContext(), "DELETED ALL USERS", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(MainActivity.this, "User is not registered.", Toast.LENGTH_LONG).show();
                    user.setBackgroundResource(R.drawable.error_storke);
                    pass.setBackgroundResource(R.drawable.error_storke);
                    try {
                        vibrator.vibrate(pattern, -1);
                    }catch(NullPointerException e){}
                }

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });


    }

}