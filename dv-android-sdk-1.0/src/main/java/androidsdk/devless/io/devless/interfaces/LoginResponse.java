package androidsdk.devless.io.devless.interfaces;

import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.ResponsePayload;

/**
 * Created by pianoafrik on 6/8/17.
 */

public interface LoginResponse {
    void OnLogInSuccess(ResponsePayload response);
    void OnLogInFailed (ErrorMessage errorMessage);
}
