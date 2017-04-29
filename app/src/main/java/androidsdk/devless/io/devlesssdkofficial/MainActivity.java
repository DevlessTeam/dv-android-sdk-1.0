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

import androidsdk.devless.io.devless.main.Devless;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String  DEVLESS_USER_TOKEN = "devlessUserToken";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appUrl = "http://afterpush.herokuapp.com";
        String serviceName = "new_service";
        String devlessToken = "f9372bad91503a3d4da8824ef6e9ebe6";
        String tableName = "names";

        Devless devless = new Devless(this, appUrl, serviceName, devlessToken);

        sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        editor = sp.edit();

        if (sp.contains(DEVLESS_USER_TOKEN )){
            String userName = sp.getString(DEVLESS_USER_TOKEN, "Error");
            //Log.e("DevlessUserToken", userName);
            devless.setDevlessUserToken(userName);
            //startActivity(new Intent(this, PinLogin.class));

        }

        Log.e("=-==DevlessUserToken==", devless.getDevlessUserToken());

        devless.getData(tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("==Query response==", response);
            }
        });

        /*
        String appUrl = "http://afterpush.herokuapp.com";
        String serviceName = "new_service";
        String devlessToken = "f9372bad91503a3d4da8824ef6e9ebe6";
        String tableName = "names";

        Map<String, Object> field = new HashMap<>();
        field.put("name", "Abigail");

        Devless devless = new Devless(this, appUrl, serviceName, devlessToken);


        devless.getData(tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.v("-----Success-----", response);
            }

        });


        devless.postData(tableName, field, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.wtf("-------Post------ " , response);
            }
        });


        devless.edit(tableName, field, "4", new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.wtf("--------edit------ " , response);
            }
        });

        devless.delete(tableName, "11", new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {

                Log.wtf("--------delete------ " , response);
            }
        });

        devless.getData(tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.wtf("--------Get After Delete------ " , response);
            }

        });


        devless.deleteAll(tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.wtf("--------Delete All Response------", response);
            }
        });


        List<String> params = new ArrayList<>();

        devless.call("devless", "signUp", params, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.v("signUP", response);
            }
        });



        devless.loginUserWithEmailAndPassword("micnkru@gmail.com", "password", new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String s) {
                Log.v("-----Payload-----", s);
            }

            @Override
            public void OnLogInFailed(String s) {
                Log.v("-----Error-----", s);
            }
        });


        devless.loginUserWithUserNameAndPassword("jeff", "password", new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String payload) {
                Log.v("-----PayloadUser-----", payload);
            }

            @Override
            public void OnLogInFailed(String error) {
                Log.v("-----ErrorUser-----", error);
            }
        });



        devless.signUpWithUserNameAndPassword("Charles Finne", "password", new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Response==Signup", response);
            }
        });




        devless.loginUserWithUserNameAndPassword("Charles Finne", "password", new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String payload) {
                Log.v("-----PayloadUser-----", payload);
            }

            @Override
            public void OnLogInFailed(String error) {
                Log.v("-----ErrorUser-----", error);
            }
        });
        */



//        devless.loginWithEmailAndPassword("micnkru@gmail2.com", "password", new Devless.LoginResponse() {
//            @Override
//            public void OnLogInSuccess(String payload, String userToken) {
//                Log.e("===userToken===", userToken);
//                editor.putString(DEVLESS_USER_TOKEN, userToken);
//                editor.commit();
//            }
//
//            @Override
//            public void OnLogInFailed(String error) {
//
//            }
//        });








    }



}
