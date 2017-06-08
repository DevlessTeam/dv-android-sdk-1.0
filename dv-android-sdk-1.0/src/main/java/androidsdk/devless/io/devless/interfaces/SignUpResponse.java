package androidsdk.devless.io.devless.interfaces;

/**
 * Created by pianoafrik on 6/8/17.
 */

public interface SignUpResponse {
    void OnSignUpSuccess (String payload);
    void OnSignUpFailed  (String errorMessage);
}
