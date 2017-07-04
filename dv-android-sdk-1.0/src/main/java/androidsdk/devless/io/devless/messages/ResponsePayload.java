package androidsdk.devless.io.devless.messages;

/**
 * Created by pianoafrik on 7/4/17.
 */

public class ResponsePayload {

    private String responsePayload;

    public ResponsePayload(String responsePayload) {
        this.responsePayload = responsePayload;
    }

    @Override
    public String toString() {
        return responsePayload;
    }
}
