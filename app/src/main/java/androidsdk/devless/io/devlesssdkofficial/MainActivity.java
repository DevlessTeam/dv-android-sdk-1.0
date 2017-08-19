package androidsdk.devless.io.devlesssdkofficial;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidsdk.devless.io.devless.interfaces.DeleteResponse;
import androidsdk.devless.io.devless.interfaces.EditDataResponse;
import androidsdk.devless.io.devless.interfaces.GetDataResponse;
import androidsdk.devless.io.devless.interfaces.LoginResponse;
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

        String appUrl = "http://buildgallery.herokuapp.com";  // remember no slash after the com just the absolute URL
        String devlessToken = "41b1f8b6dd4823f63f8eeed626bfafa8";

        Devless devless = new Devless(this, appUrl, devlessToken);

        devless.addUserToken(sp);

       // Log.e("token",devless.getDevlessUserToken());


//        devless.signUpWithUsernameAndPassword("omotwor", "password", sp, new SignUpResponse() {
//            @Override
//            public void onSignUpSuccess(Payload payload) {
//                Log.e("solve", payload.toString());
//            }
//
//            @Override
//            public void onSignUpFailed(ErrorMessage errorMessage) {
//                Log.e("error", errorMessage.toString());
//            }
//        });

//        devless.loginWithEmailAndPassword("example@mail.com", "password", sp, new LoginResponse() {
//            @Override
//            public void onLogInSuccess(ResponsePayload response) {
//                Log.e("Success==", response.toString());
//            }
//
//            @Override
//            public void onLogInFailed(ErrorMessage errorMessage) {
//                Log.e("Failure==", errorMessage.toString());
//            }
//        });



//        List<String> params =  new ArrayList<>(Arrays.asList(
//                "","password","omotourchanged","","", "", ""
//        ));
//        devless.methodCall("devless", "updateProfile", params, new RequestResponse() {
//            @Override
//            public void onSuccess(ResponsePayload response) {
//                Log.e("Success==", response.toString());
//            }
//
//            @Override
//            public void userNotAuthenticated(ErrorMessage message) {
//                Log.e("Failure==", message.toString());
//            }
//        });


        devless.updateProfile("", "password", "omoomo", "", "", "", "", new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                Log.e("passed", response.toString());
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                Log.e("failed", message.toString());
            }
        });



    }


}
