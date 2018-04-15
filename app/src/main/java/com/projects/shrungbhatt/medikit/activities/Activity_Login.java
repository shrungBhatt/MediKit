package com.projects.shrungbhatt.medikit.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.util.Const;
import com.projects.shrungbhatt.medikit.util.MySharedPreferences;
import com.projects.shrungbhatt.medikit.util.URLGenerator;
import com.projects.shrungbhatt.medikit.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jigsaw on 28/1/18.
 */


public class Activity_Login extends BaseActivity implements Validator.ValidationListener {

    private static final String TAG = "LoginActivity";
    public static Activity mActivity;
    public static Boolean mActive;

    @NotEmpty
    @Email
    private EditText mUserEmail;
    @Password
    private EditText mUserPassword;
    private CheckBox mSignInAsDocCheckBox;
    private Button mLoginButton;
    private Button mSignUpButton;
    private Validator mValidator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mActivity = this;

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);

        mSignInAsDocCheckBox = findViewById(R.id.sign_in_as_doctor);

        //To check whether the user is logged in or not.
        boolean status = MySharedPreferences.getStoredLoginStatus(Activity_Login.this);
        if (status) {
            Intent i;
            if (MySharedPreferences.isAdminLoggedOn(this)) {
                i = new Intent(this, Activity_DoctorAppointmentList.class);
            } else {
                i = new Intent(this, Activity_DiseaseList.class);
            }
            startActivity(i);
            finish();
        }


        mUserEmail = findViewById(R.id.user_email);

        mUserPassword = findViewById(R.id.user_password);

        mLoginButton = findViewById(R.id.email_sign_in_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String macId = Utils.getMacAddress(TAG);
                String emailId = mUserEmail.getText().toString();
                String pass = mUserPassword.getText().toString();

                if(isNetworkAvailableAndConnected()){

                    if(mSignInAsDocCheckBox.isChecked()){
                        requestLogin(Const.Doctor,emailId,pass);

                    }else{
                        requestLogin(Const.User,emailId,pass);
                    }

                }

            }
        });

        mSignUpButton = (Button) findViewById(R.id.user_sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Login.this, Activity_Signup.class);
                startActivity(i);

            }
        });


    }

    private void requestLogin(final String user_type, final String user_name, final String password) {
        showProgressBar(this,TAG);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.USER_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        try {
                            if (response != null &&
                                    !response.equals("Wrong Username or Password")) {
                                MySharedPreferences.setStoredLoginStatus(Activity_Login.this, true);
                                MySharedPreferences.setStoredUsername(Activity_Login.this, user_name);
                                Intent i;
                                if (mSignInAsDocCheckBox.isChecked()) {
                                    MySharedPreferences.setIsAdminLoggedOn(Activity_Login.this, true);
                                    i = new Intent(Activity_Login.this, Activity_DoctorAppointmentList.class);
                                    finish();
                                } else {
                                    i = new Intent(Activity_Login.this, Activity_DiseaseList.class);
                                    finish();
                                }
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT)
                                        .show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressBar();
                        Log.e(TAG, error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_type", user_type);
                params.put("user_name", user_name);
                params.put("password", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    @Override
    public void onStart() {
        super.onStart();
        mActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        mActive = false;
    }



    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
