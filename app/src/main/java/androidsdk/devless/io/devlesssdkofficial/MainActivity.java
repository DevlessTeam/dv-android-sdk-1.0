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


//        devless.signUpWithEmailAndPassword("marko@gmail", "password", sp, new SignUpResponse() {
//            @Override
//            public void OnSignUpSuccess(String payload) {
//                Log.e("===wins===", payload);
//            }
//
//            @Override
//            public void OnSignUpFailed(String errorMessage) {
//                Log.e("===error===", errorMessage);
//            }
//        });



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


//        devless.getData(serviceName, tableName, new RequestResponse() {
//            @Override
//            public void OnSuccess(String response) {
//                Log.e("Response", response);
//            }
//
//            @Override
//            public void UserNotAuthenticated(String message) {
//                Log.e("Query response", message);
//            }
//        });

//        devless.signUpWithEmailAndPassword("meko@gmail.com", "password", sp, new SignUpResponse() {
//            @Override
//            public void OnSignUpSuccess(String payload) {
//                Log.e("===Noko==", payload);
//            }
//
//            @Override
//            public void OnSignUpFailed(String errorMessage) {
//                Log.e("===Nokos==", errorMessage);
//            }
//        });

//        devless.getData(serviceName, tableName, new RequestResponse() {
//            @Override
//            public void OnSuccess(String response) {
//                Log.e("Response", response);
//            }
//
//            @Override
//            public void UserNotAuthenticated(String message) {
//                Log.e("Query response", message);
//            }
//        });

//        devless.signUpWithUsernameAndPassword("charles", "password", sp, new SignUpResponse() {
//            @Override
//            public void OnSignUpSuccess(String payload) {
//                Log.e("Response", payload);
//            }
//
//            @Override
//            public void OnSignUpFailed(String errorMessage) {
//                Log.e("Response", errorMessage);
//            }
//        });


        // create a hashmap of params you want to search by this way
        Map<String, Object> prams = new HashMap<>();
        prams.put( "size", 2);
        prams.put( "offset", 2);
//        prams.put( "orderBy", "id");
//        prams.put( "where",   "id,2");
//         prams.put( "search", "name,koobi");
//        prams.put( "orWhere", "id,3");
//        prams.put( "between", "id,1,2");
//        prams.put( "greaterThan", "id,1");
//        prams.put( "lessThanEqual", "id,2");
//        prams.put( "where", "name,koobi");

        //set the query params
        //devless.setParams(prams);

        Map<String, Object> paramsTwo = new HashMap<>();
        paramsTwo.put( "size",   1);
        //prams.put( "offset", 2);
        //prams.put( "orderBy", "id");





        //getData
        devless.getData(serviceName, tableName, new GetDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                Log.e("GetDataSuccessResponse", response.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                Log.e("GetDataFailedResponse", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage message) {
                Log.e("UserNotAuthError", message.toString());
            }
        });








        //PostData
        //hashMap
        /*
        Map<String, Object> data = new HashMap<>();
        data.put("name", "richard");

        devless.postData(serviceName, tableName, data, new PostDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                Log.e("PostDataSuccessResponse", response.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                Log.e("PostDataFailedResponse", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage message) {
                Log.e("UserNotAuthResponse", message.toString());
            }
        });
        */


        /*

        //Search devless

        devless.search(serviceName, tableName, prams, new SearchResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                Log.e("SearchResponse", response.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {
                Log.e("SearchResponse", errorMessage.toString());
            }
        });

        devless.search(serviceName, tableName, paramsTwo, new SearchResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                Log.e("SearchResponse", response.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {
                Log.e("SearchResponse", errorMessage.toString());
            }
        });

        */


        /* Edit papapaa

        Map<String, Object> patch = new HashMap<>();
        patch.put("name", "KofiNie");

        devless.edit(serviceName, tableName, patch, "1", new EditDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                Log.e("EditDataSuccessResponse", response.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                Log.e("EditDataFailedResponse", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage message) {
                Log.e("UserNotAuth", message.toString());
            }
        });
        */

    /*
        devless.delete(serviceName, tableName, "1", new DeleteResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                Log.e("DeleteDataSuccess", response.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                Log.e("DeleteDataSFailed", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage message) {
                Log.e("UserNotAuth", message.toString());
            }
        });

        */


        devless.signUpWithEmailAndPassword("charlesOne@gmail.com", "password", sp, new SignUpResponse() {
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
