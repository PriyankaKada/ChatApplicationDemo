package com.example.webwerks.chatapplicationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity {

    static final String APP_ID="71475";
    static final String AUTH_KEY="43xXB6jcbHDFWgf";
    static final String AUTH_SECRET="xGLmwXwVW9Ek2fp";
    static final String ACCOUT_KEY="JtiV62sV8-GHxCqFKpK2";

    Button btnLogin,btnSignup;
    EditText editUser,editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFrameWork();
        btnLogin=(Button)findViewById(R.id.main_btn_login);
        btnSignup=(Button)findViewById(R.id.main_btn_signup);

        editUser=(EditText)findViewById(R.id.main_editLogin);
        editPass=(EditText)findViewById(R.id.main_editpassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignupActivity.class));

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user=editUser.getText().toString().trim();
                final String pass=editPass.getText().toString().trim();

                QBUser qbUser=new QBUser(user,pass);
                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,ChatDialogActivity.class);
                        intent.putExtra("User",user);
                        intent.putExtra("Pass",pass);

                        startActivity(intent);



                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(MainActivity.this, "Login Fail"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    private void initializeFrameWork() {
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUT_KEY);
    }
}
