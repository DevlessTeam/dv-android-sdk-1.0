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

        String appUrl = "http://lontins.herokuapp.com";
        String devlessToken = "5073c45a8b7f5dc0d73e42fdd0daebbb";

        Devless devless = new Devless(this, appUrl, devlessToken);

        devless.addUserToken(sp);


        devless.signUpWithPhoneNumberAndPassword("033445566", "password", sp, new SignUpResponse() {
            @Override
            public void onSignUpSuccess(Payload payload) {
                Log.e("solve", payload.toString());
            }

            @Override
            public void onSignUpFailed(ErrorMessage errorMessage) {
                Log.e("error", errorMessage.toString());
            }
        });

    }


}
