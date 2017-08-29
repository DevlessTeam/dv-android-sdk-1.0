package androidsdk.devless.io.devless.interfaces;

import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.ResponsePayload;

/**
 * Created by pianoafrik on 7/4/17.
 */

public interface PostDataResponse {
    void onSuccess(ResponsePayload response);
    void onFailed(ErrorMessage errorMessage);
    void userNotAuthenticated(ErrorMessage message);
    void fullPostDataResponse(ResponsePayload response);
}
