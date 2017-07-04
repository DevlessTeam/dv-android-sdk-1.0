package androidsdk.devless.io.devless.messages;

/**
 * Created by pianoafrik on 7/4/17.
 */

public class ErrorMessage {

    private String erroMessage;

    public ErrorMessage(String erroMessage) {
        this.erroMessage = erroMessage;
    }

    public String toString(){
        return erroMessage;
    }
}
