package androidsdk.devless.io.devlesssdkofficial;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import androidsdk.devless.io.devless.interfaces.DeleteResponse;
import androidsdk.devless.io.devless.interfaces.EditDataResponse;
import androidsdk.devless.io.devless.interfaces.GetDataResponse;
import androidsdk.devless.io.devless.interfaces.PostDataResponse;
import androidsdk.devless.io.devless.interfaces.RequestResponse;
import androidsdk.devless.io.devless.interfaces.SignUpResponse;
import androidsdk.devless.io.devless.main.Devless;
import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.Payload;
import androidsdk.devless.io.devless.messages.ResponsePayload;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);


        String appUrl = "http://devlesssdktest.herokuapp.com";
        String localUrl = "http://10.0.2.2:4545";
        String ec2Url  = "http://34.228.145.55:4545";
        String serviceName = "temp";
        String devlessToken = "daa847c6b5692635acb8feb45562e4dd";
        String localToken  = "8217910aed62a383f5e599b640808896";
        String ec2Toke  = "52a5f8535a486209e87afe3cb7389a52";
        String tableName = "tester";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "finney");
        params.put("stage", 0);

        Devless devless = new Devless(this, appUrl, devlessToken);
        Devless devless1 = new Devless(this, localUrl, localToken);
        Devless devlessEc2 = new Devless(this, ec2Url, ec2Toke);
        devless.addUserToken(sp);

        Log.e("User token", devless.getDevlessUserToken());



        devless.signUpWithUsernameAndPassword("charles", "password", sp, new SignUpResponse() {
            @Override
            public void OnSignUpSuccess(Payload payload) {
                Log.e("marko", payload.toString());
            }

            @Override
            public void OnSignUpFailed(ErrorMessage errorMessage) {
                Log.e("missed", errorMessage.toString());
            }
        });
    }


}
