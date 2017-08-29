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
import androidsdk.devless.io.devless.interfaces.LoginResponse;
import androidsdk.devless.io.devless.interfaces.PostDataResponse;
import androidsdk.devless.io.devless.interfaces.SearchResponse;
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

        //Create a shared preference like this
        SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        //Now go ahead and set devless up
        String appUrl = "http://buildgallery.herokuapp.com";  // remember no slash after the com just the absolute URL
        String devlessToken = "41b1f8b6dd4823f63f8eeed626bfafa8"; //this is my token

        //This is how to create a devless instance
        Devless devless = new Devless(this, appUrl, devlessToken);

          /*
          setUpDevlessUserToken Right under the instance and pass in the
          name of your shared preference variable. Im my case I called my shared preference *sp*
          */
        devless.addUserToken(sp);


        // Sign Up
        /*
        devless.signUpWithEmailAndPassword("email1@email.com", "password", sp, new SignUpResponse() {
            @Override
            public void onSignUpSuccess(Payload payload) {
                Log.e("==Success==", payload.toString());
            }

            @Override
            public void onSignUpFailed(ErrorMessage errorMessage) {
                Log.e("==Failure==", errorMessage.toString());
            }
        });
        */


        /*
        devless.loginWithEmailAndPassword("email@email.com", "password", sp, new LoginResponse() {
            @Override
            public void onLogInSuccess(ResponsePayload response) {
                Log.e("==Success==", response.toString());
            }

            @Override
            public void onLogInFailed(ErrorMessage errorMessage) {
                Log.e("==Failure==", errorMessage.toString());
            }
        });
        */



        /* Post Data
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "koobi");

        devless.postData("plans", "test_table", data, new PostDataResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                Log.e("success", response.toString());
            }

            @Override
            public void onFailed(ErrorMessage errorMessage) {
                Log.e("Failed", errorMessage.toString());
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                Log.e("UnAuth", message.toString());
            }

            @Override
            public void fullPostDataResponse(ResponsePayload response) {
                Log.e("FullPostdata", response.toString());
            }
        });
        */

        /* get data
        devless.getData("plans", "test_table", new GetDataResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                Log.e("success", response.toString());
            }

            @Override
            public void onFailed(ErrorMessage errorMessage) {
                Log.e("Failed", errorMessage.toString());
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                Log.e("UnAuth", message.toString());
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                Log.e("FullPostData", response.toString());
            }
        });
        */


        /*
        Map<String, Object> change = new HashMap<>();
        change.put("name", "kulee");

        devless.edit("plans", "test_table", change, "1", new EditDataResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                Log.e("success", response.toString());
            }

            @Override
            public void onFailed(ErrorMessage errorMessage) {
                Log.e("Failed", errorMessage.toString());
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                Log.e("UnAuth", message.toString());
            }

            @Override
            public void fullEditDataResponse(ResponsePayload response) {
                Log.e("FullPostData", response.toString());
            }
        });
        */

        /* Delete Message
        devless.delete("plans", "test_table", "1", new DeleteResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                Log.e("success", response.toString());
            }

            @Override
            public void onFailed(ErrorMessage errorMessage) {
                Log.e("Failed", errorMessage.toString());
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                Log.e("UnAuth", message.toString());
            }

            @Override
            public void fullDeleteResponse(ResponsePayload response) {
                Log.e("FullPostData", response.toString());
            }
        });
        */



    }


}
