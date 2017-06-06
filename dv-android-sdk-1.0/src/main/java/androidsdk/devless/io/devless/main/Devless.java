package androidsdk.devless.io.devless.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    private String rootUrl, token, devlessUserToken="", where = "", orderBy="id";
    private int size = -1 ;


    public Devless(Context mContext, String rootUrl, String token) {
        this.mContext = mContext;
        this.rootUrl = rootUrl;
        this.token = token;
    }

    public void getData(String serviceName, String tableName, final RequestResponse requestResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APISERVICE service = retrofit.create(APISERVICE.class);
        final Call<ResponseBody> result = service.getCalls("db?table="+tableName, token, devlessUserToken);

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                     requestResponse.OnSuccess(response.body().string());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponse.OnSuccess(t.toString());
            }
        });
    }

    public void postData(String serviceName, String tableName,  Map<String, Object> dataToAdd, final RequestResponse requestResponseresponse) {
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
                    requestResponseresponse.OnSuccess(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponseresponse.OnSuccess(t.toString());
            }
        });
    }

    public void edit(String serviceName, String tableName, Map<String, Object> update,  String id, final RequestResponse requestResponseresponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PATCHAPISERVICE deleteapiservice = retrofit.create(PATCHAPISERVICE.class);
        Call<ResponseBody> result = deleteapiservice.sendPosts("db?table=" + tableName, token, devlessUserToken ,DevlessBuilder.createPatchBody(tableName, update, id));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    requestResponseresponse.OnSuccess(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponseresponse.OnSuccess(t.toString());
            }
        });
    }


    public void delete(String serviceName, String tableName,  String id, final RequestResponse requestResponse) {
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
                    requestResponse.OnSuccess(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponse.OnSuccess(t.toString());
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
                    requestResponse.OnSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponse.OnSuccess(t.toString());
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
            public void OnSuccess(String response) {
                try {
                    JSONObject JO = new JSONObject(response);
                    String payload = JO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject resultObject = new JSONObject(result);
                    if(resultObject.length() ==  2){
                        signUpResponse.OnSignUpSuccess(payload);
                        String token  =  resultObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                    } else if (resultObject.length() == 3) {
                        signUpResponse.OnSignUpFailed("Seems Email already exists");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
            public void OnSuccess(String response) {

                try {
                    JSONObject JO = new JSONObject(response);
                    String payload = JO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject resultObject = new JSONObject(result);
                    if(resultObject.length() ==  2){
                        signUpResponse.OnSignUpSuccess(payload);
                        String token  =  resultObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                    } else if (resultObject.length() == 3) {
                        signUpResponse.OnSignUpFailed("Seems UserName already exists");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }

    public void methodCall(String serviceName, String actionName, List<String> params, final RequestResponse requestResponseresponse) {
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
                    requestResponseresponse.OnSuccess(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestResponseresponse.OnSuccess(t.toString());
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
            public void OnSuccess(String response) {

                try {
                    JSONObject jO = new JSONObject(response);
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String resultValue = loopJson(payloadObject).get(1);
                    if(!resultValue.equalsIgnoreCase("false")){
                        String result = payloadObject.getString("result");
                        JSONObject payloadReturnedObject = new JSONObject(result);
                        String token = payloadReturnedObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                        loginResponse.OnLogInSuccess(result);
                    } else {
                        loginResponse.OnLogInFailed("Wrong Email or Password");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
            public void OnSuccess(String response) {
                try {
                    JSONObject jO = new JSONObject(response);
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String resultValue = loopJson(payloadObject).get(1);
                    if(!resultValue.equalsIgnoreCase("false")){
                        String result = payloadObject.getString("result");
                        JSONObject payloadReturnedObject = new JSONObject(result);
                        String token = payloadReturnedObject.getString("token");
                        editor.putString("devlessUserToken", token);
                        editor.commit();
                        loginResponse.OnLogInSuccess(result);
                    } else {
                        loginResponse.OnLogInFailed("Wrong UserName or Password");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void logout(final LogoutResponse logoutResponse){
        final List<String> logOutParams  = new ArrayList<>();
        methodCall("devless", "logout", logOutParams, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                logoutResponse.OnLogOutSuccess(response);
            }
        });
    }


    public interface RequestResponse {
        void OnSuccess(String response);

    }

    public interface LoginResponse {
        void OnLogInSuccess(String payload);
        void OnLogInFailed(String error);
    }

    public interface LogoutResponse {
        void OnLogOutSuccess(String response);

    }

    public interface SignUpResponse{
        void OnSignUpSuccess (String payload);
        void OnSignUpFailed  (String errorMessage);
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
                        searchResponse.OnSuccess(response.body().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    searchResponse.OnSuccess(t.toString());
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
                        searchResponse.OnSuccess(response.body().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    size= -1;
                    where = "";
                    orderBy = "";
                    searchResponse.OnSuccess(t.toString());
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
                        searchResponse.OnSuccess(response.body().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    searchResponse.OnSuccess(t.toString());
                }
            });

        return this;
    }


    public interface SearchResponse{
        void OnSuccess(String response);
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

}

