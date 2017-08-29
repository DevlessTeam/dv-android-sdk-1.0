package androidsdk.devless.io.devlesssdkofficial;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidsdk.devless.io.devless.main.Devless;


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



    }


}
