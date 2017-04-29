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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        String appUrl = "http://afterpush.herokuapp.com";
        String serviceName = "new_service";
        String devlessToken = "f9372bad91503a3d4da8824ef6e9ebe6";
        String tableName = "names";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "newer");

        Devless devless = new Devless(this, appUrl, serviceName, devlessToken);
        devless.addUserToken(sp);

        devless.getData(tableName, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                Log.e("Query response", response);
            }
        });




    }




}
