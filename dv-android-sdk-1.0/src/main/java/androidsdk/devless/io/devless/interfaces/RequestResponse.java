package androidsdk.devless.io.devless.interfaces;

/**
 * Created by pianoafrik on 6/8/17.
 */

public interface RequestResponse {
    void OnSuccess(String response);
    void UserNotAuthenticated(String message);
}
