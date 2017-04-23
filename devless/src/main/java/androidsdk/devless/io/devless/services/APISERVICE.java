package androidsdk.devless.io.devless.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;


public interface APISERVICE {
    @GET
    Call<ResponseBody> getCalls(@Url String url, @Header("devless-token") String auth);
}
