package com.example.webwerks.chatapplicationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignupActivity extends AppCompatActivity {
    Button btnSignup, btnCancle;
    EditText editUser, editPass,editFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerSession();

        btnSignup = (Button) findViewById(R.id.signup_btn_signup);
        btnCancle = (Button) findViewById(R.id.signup_btn_cancel);

        editUser = (EditText) findViewById(R.id.signup_editLogin);
        editPass = (EditText) findViewById(R.id.signup_editpassword);
        editFullName= (EditText) findViewById(R.id.signup_editFullName);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editUser.getText().toString().trim();
                String pass = editPass.getText().toString().trim();

                QBUser qbUser = new QBUser(user, pass);
                    qbUser.setFullName(editFullName.getText().toString().trim());
                QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(SignupActivity.this, "Successfully Created User", Toast.LENGTH_SHORT).show();




                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(SignupActivity.this, "Failed to Created User" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

            }
        });
    }

    private void registerSession() {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {

                Log.e("ERROR", e.getMessage());


            }
        });

    }


}
