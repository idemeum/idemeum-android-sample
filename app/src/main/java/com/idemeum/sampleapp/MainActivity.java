package com.idemeum.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idemeum.androidsdk.listeners.IdemeumSigninListener;
import com.idemeum.androidsdk.manager.IdemeumManager;
import com.idemeum.androidsdk.models.IdemeumSigninResponse;
import com.idemeum.androidsdk.listeners.TokenValidationListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Declare an object for Idemeum Request class
    private IdemeumManager mIdemeumManager;
    //BIOMETRIC
    private final String apiKeyBiometric = "5166e6ac-9442-11eb-a8b3-0242ac130003";
    //ONE CLICK
    private final String apiKeyOneClick = "00000000-0000-0000-0000-000000000000";
    //DVMI
    private final String apiKeyDVMI = "c1d84ad4-9442-11eb-a8b3-0242ac130003";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mBtnLoginWithIdemeumBiometric = findViewById(R.id.btn_login_with_idemeum_biometric);
        Button mBtnLoginWithIdemeumOneClick = findViewById(R.id.btn_login_with_idemeum_one_click);
        Button mBtnLoginWithIdemeumDVMI = findViewById(R.id.btn_login_with_idemeum_dvmi);

        // initialize an idemeum manager object


        mBtnLoginWithIdemeumBiometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on button click do login with idemeum
                mIdemeumManager = IdemeumManager.getInstance(apiKeyBiometric);
                mIdemeumManager.login(MainActivity.this
                        , new IdemeumSigninListener() {


                            @Override
                            public void onSuccess(IdemeumSigninResponse mIdemeumSigninResponse) {
                                validateToken();
                            }

                            @Override
                            public void onError(int statusCode, String error) {
                                Toast.makeText(MainActivity.this,
                                        error, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        mBtnLoginWithIdemeumOneClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on button click do login with idemeum
                mIdemeumManager = IdemeumManager.getInstance(apiKeyOneClick);
                mIdemeumManager.login(MainActivity.this
                        , new IdemeumSigninListener() {
                            @Override
                            public void onSuccess(IdemeumSigninResponse mIdemeumSigninResponse) {
                                validateToken();
                            }

                            @Override
                            public void onError(int statusCode, String errorMsg) {
                                Toast.makeText(MainActivity.this,
                                        errorMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        mBtnLoginWithIdemeumDVMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on button click do login with idemeum
                mIdemeumManager = IdemeumManager.getInstance(apiKeyDVMI);
                mIdemeumManager.login(MainActivity.this
                        , new IdemeumSigninListener() {
                            @Override
                            public void onSuccess(IdemeumSigninResponse mIdemeumSigninResponse) {
                                validateToken();
                            }

                            @Override
                            public void onError(int statusCode, String errorMsg) {
                                Toast.makeText(MainActivity.this,
                                        errorMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void validateToken() {
        mIdemeumManager.userClaims(this, new TokenValidationListener() {

            @Override
            public void onSuccess(JSONObject mClaims, String message) {
                //user claims will be received as JSONObject here
                Intent mIntent = new Intent(MainActivity.this,
                        HomeScreenActivity.class);
                mIntent.putExtra("response", mClaims.toString());
                startActivity(mIntent);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }

        });
    }


}