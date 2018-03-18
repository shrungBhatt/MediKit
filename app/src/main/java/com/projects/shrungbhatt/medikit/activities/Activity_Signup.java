package com.projects.shrungbhatt.medikit.activities;

import android.content.Intent;
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
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.util.Const;
import com.projects.shrungbhatt.medikit.util.URLGenerator;
import com.projects.shrungbhatt.medikit.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jigsaw on 28/1/18.
 */

public class Activity_Signup extends BaseActivity implements Validator.ValidationListener {

    private static final String TAG = "Activity_Signup";

    @Email
    @NotEmpty
    private EditText mUserEmail;

    @Password
    @NotEmpty
    private EditText mUserPassword;

    @NotEmpty
    private EditText mUsername;

    @ConfirmPassword
    @NotEmpty
    private EditText mRetypePassword;
    private Button mSignUpButton;
    private CheckBox mUserTypeCheckBox;
    private String mMacId;
    private Validator mValidator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mMacId = Utils.getMacAddress(TAG);

        mUserEmail = (EditText) findViewById(R.id.user_sign_up_email);

        mUserPassword = (EditText) findViewById(R.id.user_sign_up_password);

        mRetypePassword = (EditText) findViewById(R.id.user_sign_up_reenter_password);

        mUserTypeCheckBox = findViewById(R.id.sign_up_as_doctor);

        mUsername = findViewById(R.id.user_sign_up_username);

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);

        mSignUpButton = (Button) findViewById(R.id.user_register_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValidator.validate();
            }
        });


    }


    private void registerUser(final String email, final String userType, final String passWord,
                              final String userName) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.REGISTER_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.equals("Insert SuccessFul")) {
                                Toast.makeText(getApplicationContext(), "Registration Complete",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Activity_Signup.this, Activity_Login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("user_type", userType);
                params.put("password", passWord);
                params.put("user_name",userName);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onValidationSucceeded() {
        String email = mUserEmail.getText().toString();
        String password = mUserPassword.getText().toString().trim();
        String userName = mUsername.getText().toString().trim();

        if(mUserTypeCheckBox.isChecked()) {
            registerUser(email, Const.Doctor, password,userName);
        }else{
            registerUser(email,Const.User,password,userName);
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
