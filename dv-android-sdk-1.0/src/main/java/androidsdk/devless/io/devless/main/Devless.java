package androidsdk.devless.io.devless.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidsdk.devless.io.devless.interfaces.DeleteResponse;
import androidsdk.devless.io.devless.interfaces.EditDataResponse;
import androidsdk.devless.io.devless.interfaces.GetDataResponse;
import androidsdk.devless.io.devless.interfaces.LoginResponse;
import androidsdk.devless.io.devless.interfaces.LogoutResponse;
import androidsdk.devless.io.devless.interfaces.PostDataResponse;
import androidsdk.devless.io.devless.interfaces.RequestResponse;
import androidsdk.devless.io.devless.interfaces.SearchResponse;
import androidsdk.devless.io.devless.interfaces.SignUpResponse;
import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.Payload;
import androidsdk.devless.io.devless.messages.ResponsePayload;
import androidsdk.devless.io.devless.services.APISERVICE;
import androidsdk.devless.io.devless.services.DELETEAPISERVICE;
import androidsdk.devless.io.devless.services.PATCHAPISERVICE;
import androidsdk.devless.io.devless.services.POSTAPI;
import androidsdk.devless.io.devless.utils.DevlessBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Devless extends AppCompatActivity implements Serializable{

    Context mContext;
    private String rootUrl, token, devlessUserToken="", where = "", orderBy="id", empty;
    private int size = -1 ;


    public Devless(Context mContext, String rootUrl, String token) {
        this.mContext = mContext;
        this.rootUrl = rootUrl;
        this.token = token;
    }

    public void getData(String serviceName, String tableName, final GetDataResponse requestResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APISERVICE service = retrofit.create(APISERVICE.class);
        final Call<ResponseBody> result = service.getCalls("db?table="+tableName, token, devlessUserToken);

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO::Display Full Result and let the dev manipulate it to his suit

                try {
                    String result = response.body().string();
                    requestResponse.fullRequestResponse(new ResponsePayload(result));
                    boolean bool = DevlessBuilder.checkAuth(result);
                    if (bool){

                        boolean successful = DevlessBuilder.checkGetSuccess(result);
                        if(successful){
                            requestResponse.onSuccess(new ResponsePayload(result));
                        } else{
                            String errorMessage = "Error: The ServiceName or TableName doesn't exist";
                            requestResponse.onFailed(new ErrorMessage(errorMessage));
                        }

                    }  else{
                        requestResponse.userNotAuthenticated(new ErrorMessage("Token expired please log in again"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponse.onSuccess(new ResponsePayload(t.toString()));
            }
        });
    }

    public void postData(String serviceName, String tableName,  Map<String, Object> dataToAdd, final PostDataResponse postDataResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        POSTAPI postapi = retrofit.create(POSTAPI.class);
        Call<ResponseBody> result = postapi.sendPosts("db?table="+tableName,
                token, devlessUserToken ,DevlessBuilder.createPostBody(tableName, dataToAdd));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String result = response.body().string();
                    boolean bool = DevlessBuilder.checkAuth(result);
                    postDataResponse.fullPostDataResponse(new ResponsePayload(result));
                    if (bool){

                        int successPull = DevlessBuilder.checkPostSuccess(result);
                        if(successPull == 1){
                            // successful
                            postDataResponse.onSuccess(new ResponsePayload(result));

                        } else if (successPull == 0){
                            //wrong fieldname
                            String errorMessage = "Error: Post failed because there was a  wrong fieldName please check it";
                            postDataResponse.onFailed(new ErrorMessage(errorMessage));

                        } else {
                            // errorMessage
                            String errorMessage = "Error: The ServiceName or TableName doesn't exist";
                            postDataResponse.onFailed(new ErrorMessage(errorMessage));
                        }


                    }  else{
                        postDataResponse.userNotAuthenticated( new ErrorMessage("Token expired please log in again"));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                postDataResponse.onSuccess( new ResponsePayload( t.toString()));
            }
        });
    }

    public void edit(String serviceName, String tableName, Map<String, Object> update,  String id, final EditDataResponse editDataResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PATCHAPISERVICE patchapiservice = retrofit.create(PATCHAPISERVICE.class);
        final Call<ResponseBody> result = patchapiservice.sendPosts("db?table=" + tableName, token, devlessUserToken ,DevlessBuilder.createPatchBody(tableName, update, id));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    boolean bool = DevlessBuilder.checkAuth(result);
                    editDataResponse.fullEditDataResponse(new ResponsePayload(result));

                    if (bool){
                        int successPull = DevlessBuilder.checkPostSuccess(result);
                        if(successPull == 1){
                            // successful
                            editDataResponse.onSuccess(new ResponsePayload(result));

                        } else if (successPull == 0){
                            //wrong fieldname
                            String errorMessage = "Error: Edit failed because there was a  wrong fieldName please check it";
                            editDataResponse.onFailed(new ErrorMessage(errorMessage));
                        } else if (successPull == 2){
                            //wrong fieldname
                            String errorMessage = "Error: Edit Failed Because Id does not exist";
                            editDataResponse.onFailed(new ErrorMessage(errorMessage));

                        } else {
                            // errorMessage
                            String errorMessage = "Error: The ServiceName or TableName doesn't exist";
                            editDataResponse.onFailed(new ErrorMessage(errorMessage));
                        }


                    }  else{
                        editDataResponse.userNotAuthenticated(new ErrorMessage("Token expired please log in again"));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                editDataResponse.onSuccess( new ResponsePayload(t.toString()));
            }
        });
    }


    public void delete(String serviceName, String tableName,  String id, final DeleteResponse deleteResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DELETEAPISERVICE deleteapiservice = retrofit.create(DELETEAPISERVICE.class);
        Call<ResponseBody> result = deleteapiservice.sendPosts("db?table=" + tableName, token, devlessUserToken,DevlessBuilder.createDeleteBody(tableName, id));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String result = response.body().string();
                    boolean bool = DevlessBuilder.checkAuth(result);
                    deleteResponse.fullDeleteResponse(new ResponsePayload(result));

                    if (bool){
                        int successPull = DevlessBuilder.checkPostSuccess(result);
                        if(successPull == 1){
                            // successful
                            deleteResponse.onSuccess(new ResponsePayload(result));
                        } else if (successPull == 2){
                            //wrong fieldName
                            String errorMessage = "Error: Delete Failed Because Id does not exist";
                            deleteResponse.onFailed(new ErrorMessage(errorMessage));

                        } else {
                            // errorMessage
                            String errorMessage = "Error: The ServiceName or TableName doesn't exist";
                            deleteResponse.onFailed(new ErrorMessage(errorMessage));
                        }
                    }  else{
                        deleteResponse.userNotAuthenticated(new ErrorMessage("Token expired please log in again"));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                deleteResponse.onSuccess(new ResponsePayload(t.toString()));
            }
        });
    }

    public void deleteAll(String serviceName, String tableName,  final RequestResponse requestResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DELETEAPISERVICE deleteapiservice = retrofit.create(DELETEAPISERVICE.class);
        Call<ResponseBody> result = deleteapiservice.sendPosts("db?table=" + tableName, token, devlessUserToken ,DevlessBuilder.createDeleteAllBody(tableName));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String result = response.body().string();
                    boolean bool = DevlessBuilder.checkAuth(result);
                    requestResponse.fullRequestResponse(new ResponsePayload(result));

                    if (bool){
                        requestResponse.onSuccess(new ResponsePayload(result));
                    }  else{
                        requestResponse.userNotAuthenticated(new ErrorMessage( "Token expired please log in again"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponse.onSuccess( new ResponsePayload( t.toString()));
            }
        });
    }


    public void signUpWithEmailAndPassword(String email, final String password, final SharedPreferences sp, final SignUpResponse signUpResponse) {
        final SharedPreferences.Editor editor = sp.edit();
        List<String> signUpEmailANdPasswordDetails  = new ArrayList<>(Arrays.asList(
                email, password, "", "", "", "", ""
        ));

        methodCall("devless", "signUp", signUpEmailANdPasswordDetails, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {

                try {
                      setDevlessUserToken(token);
                    //Log.e("respo", response.toString());
                    JSONObject JO = new JSONObject(response.toString());
                    String payload = JO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject resultObject = new JSONObject(result);
                    if(resultObject.length() ==  2){
                        Payload resultPayload = new Payload(payload);
                        signUpResponse.onSignUpSuccess(resultPayload);
                        String token  =  resultObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                    } else if (resultObject.length() == 3) {
                        ErrorMessage errorMessage = new ErrorMessage("Seems Email already exists wool");
                        signUpResponse.onSignUpFailed(errorMessage);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorMessage errorMessage = new ErrorMessage("Seems User already exist or email is not valid");
                    signUpResponse.onSignUpFailed(errorMessage);



                }

            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                // Do Nothing Here
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                // Do Nothing Here
            }

        });

    }

    public void signUpWithPhoneNumberAndPassword(String phoneNumber, final String password, final SharedPreferences sp, final SignUpResponse signUpResponse) {
        final SharedPreferences.Editor editor = sp.edit();
        List<String> signUpEmailANdPasswordDetails  = new ArrayList<>(Arrays.asList(
                null, password, null, phoneNumber, "", "", ""
        ));

        methodCall("devless", "signUp", signUpEmailANdPasswordDetails, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {

                try {
                    Log.e("phone number", response.toString());
                    JSONObject JO = new JSONObject(response.toString());
                    String payload = JO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject resultObject = new JSONObject(result);
                    if(resultObject.length() ==  2){
                        Payload resultPayload = new Payload(payload);
                        signUpResponse.onSignUpSuccess(resultPayload);
                        String token  =  resultObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                        setDevlessUserToken(token);
                    } else if (resultObject.length() == 3) {
                        ErrorMessage errorMessage = new ErrorMessage("Seems PhoneNumber already exists");
                        signUpResponse.onSignUpFailed(errorMessage);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorMessage errorMessage = new ErrorMessage("Seems PhoneNumber already exists");
                    signUpResponse.onSignUpFailed(errorMessage);
                }

            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                // Do Nothing
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                //Do Nothing
            }

        });

    }

    public void signUpWithUsernameAndPassword(String userName, String password, SharedPreferences sp, final SignUpResponse signUpResponse) {
        final SharedPreferences.Editor editor = sp.edit();
        List<String> signUpEmailANdPasswordDetails  = new ArrayList<>(Arrays.asList(
                "", password, userName, "", "", "", ""
        ));

        methodCall("devless", "signUp", signUpEmailANdPasswordDetails, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {

                try {
                    setDevlessUserToken(token);
                    //Log.e("oooo", response.toString());
                    JSONObject JO = new JSONObject(response.toString());
                    String payload = JO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject resultObject = new JSONObject(result);
                    if(resultObject.length() ==  2){
                        Payload resultPayload = new Payload(payload);
                        signUpResponse.onSignUpSuccess(resultPayload);
                        String token  =  resultObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                    } else if (resultObject.length() == 3) {
                        ErrorMessage errorMessage = new ErrorMessage("Seems UserName already exists");
                        signUpResponse.onSignUpFailed(errorMessage);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    signUpResponse.onSignUpFailed(new ErrorMessage ("User already exists"));
                }

            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                //signUpResponse.onSignUpFailed(message);
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                // Do Nothing
            }

        });

    }

    public void methodCall(String serviceName, String actionName, List<String> params, final RequestResponse requestResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(rootUrl + "/api/v1/service/"+ serviceName + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        POSTAPI postapi = retrofit.create(POSTAPI.class);
        Call<ResponseBody> result = postapi.sendPosts("rpc?action="+ actionName,
                token,devlessUserToken, DevlessBuilder.callBodyBuilder(serviceName, params));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    requestResponse.onSuccess( new ResponsePayload( response.body().string()));
                    requestResponse.fullRequestResponse(new ResponsePayload( response.body().string()));

                } catch (IOException e) {
                    e.printStackTrace();
                    requestResponse.fullRequestResponse(new ResponsePayload(e.toString()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponse.onSuccess(new ResponsePayload(t.toString()));
                requestResponse.fullRequestResponse(new ResponsePayload(t.toString()));
            }
        });
    }


    public void loginWithEmailAndPassword(String email, final String password, final SharedPreferences sp, final LoginResponse loginResponse){
        final SharedPreferences.Editor editor = sp.edit();
        List<String> loginParams  = new ArrayList<>(Arrays.asList(
                "",
                 email,
                "",
                password
        ));
        methodCall("devless", "login", loginParams, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {

                try {

                    JSONObject jO = new JSONObject(response.toString());
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String resultValue = loopJson(payloadObject).get(1);
                    if(!resultValue.equalsIgnoreCase("false")){
                        String result = payloadObject.getString("result");
                        JSONObject payloadReturnedObject = new JSONObject(result);
                        String token = payloadReturnedObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                        setDevlessUserToken(token);
                        ResponsePayload responsePayload = new ResponsePayload(result);

                        loginResponse.onLogInSuccess(responsePayload);
                    } else {
                        ErrorMessage errorMessage = new ErrorMessage("Wrong Email or Password");
                        loginResponse.onLogInFailed(errorMessage);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                //Do nothing
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                //DO NOTHING
            }
        });

    }

    public void loginWithUsernameAndPassword(String userName, final String password, final SharedPreferences sp ,final LoginResponse loginResponse){
        final SharedPreferences.Editor editor = sp.edit();
        List<String> loginParams  = new ArrayList<>(Arrays.asList(
                userName,
                "",
                "",
                password
        ));
        methodCall("devless", "login", loginParams, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                try {
                    JSONObject jO = new JSONObject(response.toString());
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String resultValue = loopJson(payloadObject).get(1);
                    if(!resultValue.equalsIgnoreCase("false")){
                        String result = payloadObject.getString("result");
                        JSONObject payloadReturnedObject = new JSONObject(result);
                        String token = payloadReturnedObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                        setDevlessUserToken(token);
                        ResponsePayload responsePayload = new ResponsePayload(result);
                        loginResponse.onLogInSuccess(responsePayload);
                    } else {
                        ErrorMessage errorMessage = new ErrorMessage("Wrong UserName or Password");
                        loginResponse.onLogInFailed(errorMessage);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                // Do Nothing
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                // Do Nothing
            }


        });

    }

    public void loginWithPhoneNumberAndPassword(String phoneNumber, final String password, final SharedPreferences sp, final LoginResponse loginResponse){
        final SharedPreferences.Editor editor = sp.edit();
        List<String> loginParams  = new ArrayList<>(Arrays.asList(
                "",
                "",
                phoneNumber,
                password
        ));
        methodCall("devless", "login", loginParams, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {

                try {
                    JSONObject jO = new JSONObject(response.toString());
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String resultValue = loopJson(payloadObject).get(1);
                    if(!resultValue.equalsIgnoreCase("false")){
                        String result = payloadObject.getString("result");
                        JSONObject payloadReturnedObject = new JSONObject(result);
                        String token = payloadReturnedObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                        setDevlessUserToken(token);
                        ResponsePayload responsePayload = new ResponsePayload(result);
                        loginResponse.onLogInSuccess(responsePayload);
                    } else {
                        ErrorMessage errorMessage = new ErrorMessage("Wrong PhoneNumber or Password");
                        loginResponse.onLogInFailed(errorMessage);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                //Do Nothing
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                //Do Nothing
            }
        });

    }

    public void logout(final LogoutResponse logoutResponse){
        final List<String> logOutParams  = new ArrayList<>();
        methodCall("devless", "logout", logOutParams, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                logoutResponse.onLogOutSuccess(new ResponsePayload( response.toString()));
            }

            @Override
            public void userNotAuthenticated(ErrorMessage errorMessage) {
                // Do Nothing
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                logoutResponse.fullLogoutResponse(response);
            }
        });
    }



    public String getDevlessUserToken() {
        return devlessUserToken;
    }

    private void setDevlessUserToken(String devlessUserToken) {
        this.devlessUserToken = devlessUserToken;
    }

    public void addUserToken (SharedPreferences sharedPreferences) {
        if (sharedPreferences.contains("devlessUserToken" )){
            String devlessUserToken = sharedPreferences.getString("devlessUserToken", "Error");
            this.setDevlessUserToken(devlessUserToken);
            //Log.e("Token added as header", devlessUserToken);
        } else {
            //Log.e("No header", "user has not logged in or he has been logged out of the system");
        }
    }

    public Devless queryData(String serviceName, final String tableName, final SearchResponse searchResponse){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final APISERVICE service = retrofit.create(APISERVICE.class);



        if(TextUtils.isEmpty(this.where)){

            final Call<ResponseBody> result = service.getCalls("db?table=" + tableName + "&size="+this.size+ "&orderBy=" + this.orderBy, token, devlessUserToken);
            result.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        //searchResponse.onSuccess(response.body().string());
                        String result = response.body().string();
                        searchResponse.fullSearchResponse(new ResponsePayload(result));

                        boolean bool = DevlessBuilder.checkAuth(result);
                        if (bool){
                            ResponsePayload responsePayload = new ResponsePayload(result);
                            searchResponse.onSuccess(responsePayload);
                        }  else{
                            ErrorMessage  errorMessage = new ErrorMessage("Token expired please log in again");
                            searchResponse.userNotAuthenticated(errorMessage);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        searchResponse.fullSearchResponse(new ResponsePayload(e.toString()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    searchResponse.onSuccess(new ResponsePayload(t.toString()));
                    searchResponse.fullSearchResponse(new ResponsePayload(t.toString()));
                }
            });
        } else {

            final Call<ResponseBody> result = service.getCalls("db?table=" + tableName + "&size="+this.size+ "&where=" + this.where +"&orderBy=" + this.orderBy, token, devlessUserToken);
            result.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        size= -1;
                        where = "";
                        orderBy = "";
                        String result = response.body().string();

                        boolean bool = DevlessBuilder.checkAuth(result);
                        searchResponse.fullSearchResponse(new ResponsePayload(result));
                       if (bool){
                            searchResponse.onSuccess(new ResponsePayload(result));
                       }  else{
                           searchResponse.userNotAuthenticated(new ErrorMessage("Token expired please log in again"));
                       }

                    } catch (IOException e) {
                        e.printStackTrace();
                        searchResponse.fullSearchResponse(new ResponsePayload(e.toString()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    size= -1;
                    where = "";
                    orderBy = "";
                    searchResponse.onSuccess(new ResponsePayload(t.toString()));
                }
            });

        }


        return this;
    }


    public Devless queryOrderedData(String serviceName, final String tableName, final SearchResponse searchResponse){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final APISERVICE service = retrofit.create(APISERVICE.class);

            final Call<ResponseBody> result = service.getCalls("db?table=" + tableName + "&size="+this.size+ "&orderBy=" + this.orderBy, token, devlessUserToken);
            result.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {

                        String result = response.body().string();

                        boolean bool = DevlessBuilder.checkAuth(result);
                        searchResponse.fullSearchResponse(new ResponsePayload(result));
                        if (bool){
                            searchResponse.onSuccess(new ResponsePayload(result));
                        }  else{
                            searchResponse.userNotAuthenticated(new ErrorMessage("Token expired please log in again") );
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                        searchResponse.fullSearchResponse(new ResponsePayload(e.toString()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    searchResponse.onSuccess(new ResponsePayload(t.toString()));
                    searchResponse.fullSearchResponse(new ResponsePayload(t.toString()));
                }
            });

        return this;
    }

    public  void search(String serviceName, final String tableName, final Map<String, Object> queryParams, final SearchResponse searchResponse){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final APISERVICE service = retrofit.create(APISERVICE.class);

        String paramUrl = "";
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            paramUrl += "&" + entry.getKey() + "=" + entry.getValue().toString();
        }

        final Call<ResponseBody> result = service.getCalls("db?table=" + tableName + paramUrl, token, devlessUserToken);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String result = response.body().string();

                    boolean bool = DevlessBuilder.checkAuth(result);
                    searchResponse.fullSearchResponse(new ResponsePayload(result));
                    if (bool){
                        searchResponse.onSuccess(new ResponsePayload(result) );
                        queryParams.clear();

                    }  else{
                        searchResponse.userNotAuthenticated(new ErrorMessage("Token expired please log in again"));
                        //searchResponse.userNotAuthenticated(result);
                        queryParams.clear();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    searchResponse.fullSearchResponse(new ResponsePayload(e.toString()));
                    queryParams.clear();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                searchResponse.onSuccess(new ResponsePayload(t.toString()));
                searchResponse.fullSearchResponse(new ResponsePayload(t.toString()));
                queryParams .clear();
            }
        });

    }



    private List<String> loopJson (JSONObject jsonObject){
        List<String> ele = new ArrayList<>();
        int i = 0;
        for(Iterator<String> keys=jsonObject.keys();keys.hasNext();) {
            try {
                ele.add(String.valueOf(jsonObject.get(keys.next())));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }

        return ele;
    }

    public Devless size (int size){
        this.size = size;
        return  this;
    }

    public Devless where (String fieldName, String value){
        this.where = fieldName + "," + value;
        return this;
    }

    public Devless orderBy (String orderBy){
        this.orderBy = orderBy;
        return this;
    }

    public void updateProfile(
            String email,
            String password,
            String username,
            String phoneNumber,
            String firstname,
            String lastname,
            String others,
            final RequestResponse requestResponse)
    {
        List<String> updateDetails = new ArrayList<>(Arrays.asList(
                email, password, username, phoneNumber, firstname, lastname, others
        ));

        methodCall("devless", "updateProfile", updateDetails, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                requestResponse.onSuccess(response);
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                requestResponse.userNotAuthenticated(message);
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                requestResponse.fullRequestResponse(response);
            }
        });
    }


    public void updateUser(
            String email,
            String password,
            String username,
            String phoneNumber,
            String firstname,
            String lastname,
            String others,
            final RequestResponse requestResponse)
    {
        List<String> updateDetails = new ArrayList<>(Arrays.asList(
                email, password, username, phoneNumber, firstname, lastname, others
        ));

        methodCall("devless", "updateProfile", updateDetails, new RequestResponse() {
            @Override
            public void onSuccess(ResponsePayload response) {
                requestResponse.onSuccess(response);
            }

            @Override
            public void userNotAuthenticated(ErrorMessage message) {
                requestResponse.userNotAuthenticated(message);
            }

            @Override
            public void fullRequestResponse(ResponsePayload response) {
                requestResponse.fullRequestResponse(response);
            }
        });
    }



}
