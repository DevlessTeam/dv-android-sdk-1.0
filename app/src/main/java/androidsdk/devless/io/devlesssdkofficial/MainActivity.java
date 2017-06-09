package androidsdk.devless.io.devlesssdkofficial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidsdk.devless.io.devless.interfaces.LoginResponse;
import androidsdk.devless.io.devless.interfaces.LogoutResponse;
import androidsdk.devless.io.devless.interfaces.RequestResponse;
import androidsdk.devless.io.devless.main.Devless;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);


        String appUrl = "http://easead.herokuapp.com";
        String serviceName = "custom_json";
        String devlessToken = "0f14af651033d54856cac541e7624b1a";
        String tableName = "logic";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "finney");
        params.put("stage", 0);

        Devless devless = new Devless(this, appUrl, devlessToken);


        devless.addUserToken(sp);

        Log.e("token", devless.getDevlessUserToken());



/*
        devless.where("name", "finney")
                .orderBy("id")
                .size(2)
                .queryData(serviceName,tableName,new Devless.SearchResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("search response two==", response);
            }

                    @Override
                    public void UserNotAuthenticated(String message) {
                        Log.e("search order by==", message);
                    }
                });



        devless.where("stage", "0").queryData(serviceName,tableName, new Devless.SearchResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("search response all==", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("search order by==", message);
            }
        });

        devless.orderBy("id").queryData(serviceName,tableName, new Devless.SearchResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("search response all==", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("search order by==", message);
            }
        });

        devless.orderBy("name").queryOrderedData(serviceName, tableName, new Devless.SearchResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("search order by==", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("search order by==", message);
            }
        });

        devless.orderBy("devless_user_id").queryOrderedData(serviceName, tableName, new Devless.SearchResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("search order by==", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("search order by==", message);
            }
        });


    */

        /*
        devless.getData(serviceName, tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Query response", response);
            }
        });


        devless.postData(serviceName, tableName, params, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Messge", response);
            }
        });

        devless.edit(serviceName, tableName, params, "13", new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Messge13", response);
            }
        });

        devless.delete(serviceName, tableName, "13", new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Delete13", response);
            }
        });

        devless.deleteAll(serviceName, tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("deletall", response);
            }
        });

        devless.logout(new Devless.LogoutResponse() {
            @Override
            public void OnLogOutSuccess(String response) {
                Log.e("Log Out", response);
            }
        });

        devless.signUpWithEmailAndPassword("meko@gmail.com", "password", sp, new Devless.SignUpResponse() {
            @Override
            public void OnSignUpSuccess(String payload) {
                //Toast your success message here
                Log.e("SignUp success", payload);
            }

            @Override
            public void OnSignUpFailed(String errorMessage) {
                Log.e("SignuPFailure", errorMessage);
            }
        });

        devless.signUpWithUsernameAndPassword("mikko", "password", sp, new Devless.SignUpResponse() {
            @Override
            public void OnSignUpSuccess(String payload) {
                Log.e("UsernamePassSignUpSucc", payload);
            }

            @Override
            public void OnSignUpFailed(String errorMessage) {
                Log.e("UnamPassSignUpFailed", errorMessage);
            }
        });


        devless.loginWithEmailAndPassword("meko@gmail.com", "password", sp, new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String payload) {
                Log.e("UsernamePassLogInSucc", payload);
            }

            @Override
            public void OnLogInFailed(String error) {
                Log.e("UsernamePassLoginSucc", error);
            }
        });

        devless.loginWithUsernameAndPassword("mikko", "password", sp, new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String payload) {
                Log.e("UsernamePassLogInSucc", payload);
            }

            @Override
            public void OnLogInFailed(String error) {
                Log.e("UsernamePassLoginSucc", error);
            }
        })
        */

        /*

        devless.loginWithUsernameAndPassword("mikko", "password", sp, new LoginResponse() {
            @Override
            public void OnLogInSuccess(String payload) {
                Log.e("UsernamePassLogInSucc", payload);
            }

            @Override
            public void OnLogInFailed(String error) {
                Log.e("UsernamePassLoginSucc", error);
            }
        });

        devless.getData(serviceName, tableName, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Query response", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("Query response", message);
            }
        });

        devless.postData(serviceName, tableName, params, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Messge", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("Message", message);
            }
        });


        devless.logout(new LogoutResponse() {
            @Override
            public void OnLogOutSuccess(String response) {
                Log.e("log out resp", response);
            }
        });



        devless.edit(serviceName, tableName, params, "24", new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Edit response", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("No Auth", message);
            }
        });


        devless.delete(serviceName, tableName, "25", new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Delete25", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("Delete25AuthFailed", message);
            }
        });


   }

 */

//        devless.loginWithEmailAndPassword("eben@mest.com", "password", sp, new LoginResponse() {
//            @Override
//            public void OnLogInSuccess(String payload) {
//                Log.e("UsernamePassLogInSucc", payload);
//            }
//
//            @Override
//            public void OnLogInFailed(String error) {
//                Log.e("UsernamePassLoginSucc", error);
//            }
//        });


        devless.getData(serviceName, tableName, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Response", response);
            }

            @Override
            public void UserNotAuthenticated(String message) {
                Log.e("Query response", message);
            }
        });

    }


}
