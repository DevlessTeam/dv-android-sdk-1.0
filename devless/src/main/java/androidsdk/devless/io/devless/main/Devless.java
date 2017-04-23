package androidsdk.devless.io.devless.main;

import android.content.Context;

import java.io.IOException;
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
    private String rootUrl, serviceName, token;

    public Devless(Context mContext, String rootUrl, String serviceName, String token) {
        this.mContext = mContext;
        this.rootUrl = rootUrl;
        this.serviceName = serviceName;
        this.token = token;
    }

    public void get(String tableName, final RequestResponse requestResponseresponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APISERVICE service = retrofit.create(APISERVICE.class);
        Call<ResponseBody> result = service.getCalls("db?table="+tableName, token);

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


    public void post(String tableName,  Map<String, Object> dataToAdd, final RequestResponse requestResponseresponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DevlessBuilder.formUrl(rootUrl, serviceName))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        POSTAPI postapi = retrofit.create(POSTAPI.class);
        Call<ResponseBody> result = postapi.sendPosts("db?table="+tableName,
                token, DevlessBuilder.createPostBody(tableName, dataToAdd));

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
        Call<ResponseBody> result = deleteapiservice.sendPosts("db?table=" + tableName, token, DevlessBuilder.createPatchBody(tableName, update, id));

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
        Call<ResponseBody> result = deleteapiservice.sendPosts("db?table=" + tableName, token, DevlessBuilder.createDeleteBody(tableName, id));

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
        Call<ResponseBody> result = deleteapiservice.sendPosts("db?table=" + tableName, token, DevlessBuilder.createDeleteAllBody(tableName));

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


    public void signUserUp(String userName, String email, String password, final RequestResponse requestResponseresponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(rootUrl + "/api/v1/service/devless/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        POSTAPI postapi = retrofit.create(POSTAPI.class);
        Call<ResponseBody> result = postapi.sendPosts("rpc?action=signUp",
                token, DevlessBuilder.signUp(userName, email, password, "null", "null", "null"));

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

    public interface RequestResponse {
        void OnSuccess(String response);
    }

}

