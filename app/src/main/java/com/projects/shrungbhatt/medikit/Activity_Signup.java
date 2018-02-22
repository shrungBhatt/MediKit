package com.projects.shrungbhatt.medikit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by jigsaw on 28/1/18.
 */

public class Activity_Signup extends AppCompatActivity {

    private static final String TAG = "Activity_Signup";
    private EditText mUserEmail;
    private EditText mUserPassword;
    private EditText mRetypePassword;
    private Button mSignUpButton;
    private String mMacId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mMacId = Utils.getMacAddress(TAG);

        mUserEmail = (EditText) findViewById(R.id.user_sign_up_email);

        mUserPassword = (EditText) findViewById(R.id.user_sign_up_password);

        mRetypePassword = (EditText) findViewById(R.id.user_sign_up_reenter_password);

        mSignUpButton = (Button) findViewById(R.id.user_register_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = mUserEmail.getText().toString();
                String password = mUserPassword.getText().toString().trim();
                String repass = mRetypePassword.getText().toString().trim();



                if(password.isEmpty()){
                    mUserPassword.setError("Enter a Password");
                }else if(userName.isEmpty()){
                    mUserEmail.setError("Enter username");
                }else if(repass.equals(password)){
                    registerUser(mMacId,userName,password);
                }else{
                    mRetypePassword.setError("Passwords does not match");
                }


            }
        });


    }


    private void registerUser(final String mac_id, final String userName, final String passWord) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/cook_book_register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.equals("Insert SuccessFul")) {
                                Toast.makeText(getApplicationContext(), "Registration Complete",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Activity_Signup.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (!mac_id.equals("")) {
                    params.put("mac_id", mac_id);
                    params.put("username",userName);
                    params.put("password",passWord);
                }
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
