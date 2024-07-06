package enums;

import java.io.Serializable;

public enum SecurityQuestion implements Serializable {
    QUESTION_1("What was your first pet's name?"),
    QUESTION_2("What was your favorite sport in high school?"),
    QUESTION_3("What was your best friend's name in elementary school?"),
    QUESTION_4("What was the first meal you cooked?");

    private final String questionText;

    SecurityQuestion(String questionText) {
        this.questionText = questionText;
    }

    public static SecurityQuestion getSecurityQuestion(String questionText) {
        for (SecurityQuestion q : SecurityQuestion.values())
            if (q.questionText.equals(questionText)) return q;
        return null;
    }

    @Override
    public String toString() {
        return questionText;
    }
}