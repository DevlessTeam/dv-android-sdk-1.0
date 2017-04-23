package androidsdk.devless.io.devlesssdkofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import androidsdk.devless.io.devless.main.Devless;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appUrl = "http://newerapper.herokuapp.com";
        String serviceName = "Grocery";
        String devlessToken = "5c820eeb7b60679ad14bf2aa57aa2b95";
        String tableName = "grocery_table";

//        Map<String, Object> field = new HashMap<>();
//        field.put("name", "Abigail");

        Devless devless = new Devless(this, appUrl, serviceName, devlessToken);


//        devless.getData(tableName, new Devless.RequestResponse() {
//            @Override
//            public void OnSuccess(String response) {
//                Log.v("-----Success-----", response);
//            }
//
//        });

        /*
        devless.post(tableName, field, new Devless.RequestResponse() {
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

        devless.get(tableName, new Devless.RequestResponse() {
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

        */



//        devless.signUpUserWithEmailAndPassword("kkkaaaakk@gmail.com", "ssdadas", new Devless.RequestResponse() {
//                    @Override
//                    public void OnSuccess(String response) {
//                        Log.v("signUP", response);
//                    }
//                });


//        devless.call("devless", "signUp", params, new Devless.RequestResponse() {
//            @Override
//            public void OnSuccess(String response) {
//                Log.v("signUP", response);
//            }
//        });


//        devless.loginUserWithEmailAndPassword("micnkru@gail.com", "password", new Devless.LoginResponse() {
//            @Override
//            public void OnLogInSuccess(String payload) {
//                Log.v("-----Payload-----", payload);
//            }
//
//            @Override
//            public void OnLogInFailed(String error) {
//                Log.v("-----Error-----", error);
//            }
//        });


    }


}
