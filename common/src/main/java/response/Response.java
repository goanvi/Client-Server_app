package response;

import java.io.Serializable;

public class Response implements Serializable {
    private boolean answer;
    private String text;

    public Response (boolean answer, String text){
        this.answer = answer;
        this.text = text;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
