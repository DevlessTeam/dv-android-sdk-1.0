package androidsdk.devless.io.devlesssdkofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<String, Object> field = new HashMap<>();
        field.put("name", "Abigail");

        Devless devless = new Devless(this, appUrl, serviceName, devlessToken);

        devless.get(tableName, new Devless.RequestResponse() {
           @Override
           public void OnSuccess(String response) {

               Log.wtf("--------Get------ " , response);
           }

        });

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





        String email =    "micnkru@gmail2.com";
        String password =  "password";
        String userName  =  "jeff4";


        devless.signUserUp(userName, email, password, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.wtf("-------signupjson--------",  response);
            }
        });






    }


}
