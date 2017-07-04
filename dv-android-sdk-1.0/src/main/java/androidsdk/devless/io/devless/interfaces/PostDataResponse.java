package androidsdk.devless.io.devless.interfaces;

import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.ResponsePayload;

/**
 * Created by pianoafrik on 7/4/17.
 */

public interface PostDataResponse {
    void OnSuccess(ResponsePayload response);
    void OnFailed (ErrorMessage errorMessage);
    void UserNotAuthenticated(ErrorMessage message);
}
