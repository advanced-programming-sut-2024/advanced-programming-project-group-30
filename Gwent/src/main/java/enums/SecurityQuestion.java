package enums;

public enum SecurityQuestion {
    QUESTION("", 0);

    private final String questionText;
    private final int questionNumber;

    SecurityQuestion(String questionText, int questionNumber) {
        this.questionText = questionText;
        this.questionNumber = questionNumber;
    }

    public static String getListOfQuestions() {
        return null;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
}
