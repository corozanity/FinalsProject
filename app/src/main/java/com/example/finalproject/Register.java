package com.example.finalproject;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    DatabaseLogin db;
    EditText add_user;
    EditText add_pass;
    EditText add_pass_confirm;
    Button submit;
    Button dlt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        db = new DatabaseLogin(this);
        add_user = findViewById(R.id.add_user);
        add_pass = findViewById(R.id.add_pass);
        add_pass_confirm = findViewById(R.id.add_pass_confirm);
        submit = findViewById(R.id.submit);
        dlt = findViewById(R.id.dlt);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String user = add_user.getText().toString().trim();
                final String pwd = add_pass.getText().toString().trim();
                String cnf_pwd = add_pass_confirm.getText().toString().trim();
                Boolean check = db.check(user);


                //all empty field
                if (add_user.getText().length() == 0 && add_pass.getText().length() == 0 && add_pass_confirm.getText().length() == 0) {
                    add_user.setError(getString(R.string.required));
                    add_user.setBackgroundResource(R.drawable.error_storke);
                    add_pass.setError(getString(R.string.required));
                    add_pass.setBackgroundResource(R.drawable.error_storke);
                    add_pass_confirm.setError(getString(R.string.required));
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);


                }

                //only user input
                else if (add_user.getText().length() >= 1 && add_pass.getText().length() == 0 && add_pass_confirm.getText().length() == 0) {
                    add_user.setBackgroundResource(R.drawable.black_stroke);
                    add_pass.setError(getString(R.string.required));
                    add_pass.setBackgroundResource(R.drawable.error_storke);
                    add_pass_confirm.setError(getString(R.string.required));
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);

                }

                //user and pass
                else if (add_user.getText().length() >= 1 && add_pass.getText().length() >= 1 && add_pass_confirm.getText().length() == 0) {
                    add_user.setBackgroundResource(R.drawable.black_stroke);
                    add_pass.setBackgroundResource(R.drawable.black_stroke);
                    add_pass_confirm.setError(getString(R.string.required));
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);

                }

                //only pass input
                else if (add_user.getText().length() == 0 && add_pass.getText().length() >= 1 && add_pass_confirm.getText().length() == 0) {
                    add_pass.setBackgroundResource(R.drawable.black_stroke);
                    add_user.setError(getString(R.string.required));
                    add_user.setBackgroundResource(R.drawable.error_storke);
                    add_pass_confirm.setError(getString(R.string.required));
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);

                }

                //pass and confirm
                else if (add_user.getText().length() == 0 && add_pass.getText().length() >= 1 && add_pass_confirm.getText().length() >= 1) {
                    add_pass.setBackgroundResource(R.drawable.black_stroke);
                    add_pass_confirm.setBackgroundResource(R.drawable.black_stroke);
                    add_user.setError(getString(R.string.required));
                    add_user.setBackgroundResource(R.drawable.error_storke);


                }

                //only passconfirm input
                else if (add_user.getText().length() == 0 && add_pass.getText().length() >= 1 && add_pass_confirm.getText().length() == 0) {
                    add_pass_confirm.setBackgroundResource(R.drawable.black_stroke);
                    add_user.setError(getString(R.string.required));
                    add_user.setBackgroundResource(R.drawable.error_storke);
                    add_pass.setError(getString(R.string.required));
                    add_pass.setBackgroundResource(R.drawable.error_storke);

                }

                //passconfirm and user
                else if (add_user.getText().length() == 0 && add_pass.getText().length() >= 1 && add_pass_confirm.getText().length() == 0) {
                    add_pass_confirm.setBackgroundResource(R.drawable.black_stroke);
                    add_user.setBackgroundResource(R.drawable.black_stroke);
                    add_pass.setError(getString(R.string.required));
                    add_pass.setBackgroundResource(R.drawable.error_storke);

                } else if (add_user.getText().length() == 0) {
                    add_user.setError(getString(R.string.required));
                    add_user.setBackgroundResource(R.drawable.error_storke);
                    add_user.requestFocus();
                    return;

                } else if (add_pass.getText().length() == 0) {
                    add_pass.setError(getString(R.string.required));
                    add_pass.setBackgroundResource(R.drawable.error_storke);
                    add_pass.requestFocus();
                    return;
                } else if (add_pass_confirm.getText().length() == 0) {
                    add_pass_confirm.setError(getString(R.string.required));
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);
                    add_pass_confirm.requestFocus();
                    return;
                }

                //success
                if (pwd.equals(cnf_pwd) && pwd.length() >= 1 && cnf_pwd.length() >= 1 && user.length() >= 1) {

                        //checks if user does not exist in database
                        if (!check) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                            builder.setMessage("Are you sure you want to create this account?")
                                    .setTitle("Confirmation")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            long val = db.addUser(user, pwd);
                                            if (val > 0) {
                                                Toast.makeText(Register.this, getString(R.string.r_success), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(Register.this, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.left_in, R.anim.right_out);

                                            } else {
                                                Toast.makeText(Register.this, getString(R.string.r_error), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();


                        }
                            else {
                            Toast.makeText(Register.this, getString(R.string.user_taken), Toast.LENGTH_LONG).show();
                            add_user.setError(getString(R.string.user_taken));
                            add_user.setBackgroundResource(R.drawable.error_storke);

                        }




                } else if (!pwd.equals(cnf_pwd) && user.length() >= 1) {
                    //Toast.makeText(Register.this, "Password does not match.", Toast.LENGTH_LONG).show();
                    add_user.setBackgroundResource(R.drawable.black_stroke);
                    add_pass.setBackgroundResource(R.drawable.black_stroke);
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);
                    add_pass_confirm.setError(getString(R.string.pword_match));
                    add_pass_confirm.setText("");

                } else if (!pwd.equals(cnf_pwd) && user.length() == 0) {
                    //Toast.makeText(Register.this, "Password does not match.", Toast.LENGTH_LONG).show();
                    add_user.setBackgroundResource(R.drawable.error_storke);
                    add_user.setError(getString(R.string.required));
                    add_pass.setBackgroundResource(R.drawable.black_stroke);
                    add_pass_confirm.setBackgroundResource(R.drawable.error_storke);
                    add_pass_confirm.setError(getString(R.string.pword_match));

                } else {
                    Toast.makeText(Register.this, getString(R.string.asnwerAll), Toast.LENGTH_LONG).show();

                }
            }

        });


        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAll();
                Toast.makeText(Register.this, "Deleted all data from registration.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

}