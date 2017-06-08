package androidsdk.devless.io.devless.interfaces;

/**
 * Created by pianoafrik on 6/8/17.
 */

public interface LoginResponse {
    void OnLogInSuccess(String payload);
    void OnLogInFailed(String error);
}
