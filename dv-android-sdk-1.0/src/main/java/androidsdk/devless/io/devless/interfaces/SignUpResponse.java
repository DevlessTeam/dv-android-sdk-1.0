package androidsdk.devless.io.devless.interfaces;

import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.Payload;

/**
 * Created by pianoafrik on 6/8/17.
 */

public interface SignUpResponse {
    void onSignUpSuccess(Payload payload);
    void onSignUpFailed(ErrorMessage errorMessage);
}
