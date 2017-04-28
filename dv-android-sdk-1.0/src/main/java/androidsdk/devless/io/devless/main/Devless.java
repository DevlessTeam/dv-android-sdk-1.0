package androidsdk.devless.io.devless.main;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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


public class Devless {

    Context mContext;
    private String rootUrl, serviceName, token, devlessUserToken="";

    public Devless(Context mContext, String rootUrl, String serviceName, String token, String devlessUserToken) {
        this.mContext = mContext;
        this.rootUrl = rootUrl;
        this.serviceName = serviceName;
        this.token = token;
        this.devlessUserToken = devlessUserToken;
    }

    public Devless(Context mContext, String rootUrl, String serviceName, String token) {
        this.mContext = mContext;
        this.rootUrl = rootUrl;
        this.serviceName = serviceName;
        this.token = token;
    }

    public void getData(String tableName, final RequestResponse requestResponse) {
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

    public void postData(String tableName,  Map<String, Object> dataToAdd, final RequestResponse requestResponseresponse) {
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

    public void edit( String tableName, Map<String, Object> update,  String id, final RequestResponse requestResponseresponse) {
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


    public void delete( String tableName,  String id, final RequestResponse requestResponse) {
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

    public void deleteAll( String tableName,  final RequestResponse requestResponse) {
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


    public void signUpWithEmailAndPassword(String email, String password, final RequestResponse requestResponse) {
        List<String> signUpEmailANdPasswordDetails  = new ArrayList<>(Arrays.asList(
                email, password, "", "", "", "", ""
        ));

        call("devless", "signUp", signUpEmailANdPasswordDetails, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                requestResponse.OnSuccess(response);
            }

        });

    }

    public void signUpWithUsernameAndPassword(String userName, String password, final RequestResponse requestResponse) {
        List<String> signUpEmailANdPasswordDetails  = new ArrayList<>(Arrays.asList(
                "", password, userName, "", "", "", ""
        ));

        call("devless", "signUp", signUpEmailANdPasswordDetails, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                requestResponse.OnSuccess(response);
            }

        });

    }

    public void call(String serviceName, String actionName, List<String> params, final RequestResponse requestResponseresponse) {
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


    public void loginWithEmailAndPassword(String email, final String password, final LoginResponse loginResponse){
        List<String> loginParams  = new ArrayList<>(Arrays.asList(
                "",
                 email,
                "",
                password
        ));
        call("devless", "login", loginParams, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {
                try {
                    JSONObject jO = new JSONObject(response);
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject payloadReturnedObject = new JSONObject(result);
                    String token = payloadReturnedObject.getString("token");
                    setDevlessUserToken(token);
                    if (result.equalsIgnoreCase("false")){
                        //Wrong Email or Password
                        loginResponse.OnLogInFailed("Wrong Email or Password");

                    } else {
                        //Login Success
                        loginResponse.OnLogInSuccess(result, token);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void loginWithUsernameAndPassword(String userName, final String password, final LoginResponse loginResponse){
        List<String> loginParams  = new ArrayList<>(Arrays.asList(
                userName,
                "",
                "",
                password
        ));
        call("devless", "login", loginParams, new RequestResponse() {
            @Override
            public void OnSuccess(String response) {

                try {
                    JSONObject jO = new JSONObject(response);
                    String payload = jO.getString("payload");
                    JSONObject payloadObject = new JSONObject(payload);
                    String result = payloadObject.getString("result");
                    JSONObject payloadReturnedObject = new JSONObject(result);
                    String token = payloadReturnedObject.getString("token");
                    if (result.equalsIgnoreCase("false")){
                        //Wrong Email or Password
                        loginResponse.OnLogInFailed("Wrong UserName or Password");

                    } else {
                        //Login Success
                        loginResponse.OnLogInSuccess(result, token);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public interface RequestResponse {
        void OnSuccess(String response);

    }

    public interface LoginResponse {
        void OnLogInSuccess(String payload, String userToken);
        void OnLogInFailed(String error);
    }


    public interface SignUpResponse{
        void OnSignUpSuccess (String payload);
        void OnSignUpFailed  (String error);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDevlessUserToken() {
        return devlessUserToken;
    }

    public void setDevlessUserToken(String devlessUserToken) {
        this.devlessUserToken = devlessUserToken;
    }
}

