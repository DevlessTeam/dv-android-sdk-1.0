package androidsdk.devless.io.devless.services;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface DELETEAPISERVICE {
    @Headers({"X-HTTP-Method-Override: DELETE"})
    @POST
    Call<ResponseBody> sendPosts(@Url String url, @Header("devless-token") String token, @Body Map<String, Object> field);
}

