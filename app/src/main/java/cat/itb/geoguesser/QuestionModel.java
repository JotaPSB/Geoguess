package cat.itb.geoguesser;

public class QuestionModel {
    private int question;
    private int button;
    private int answer;

    public QuestionModel(int question, int button, int answer) {
        this.question = question;
        this.button = button;
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public int getButton() {
        return button;
    }

    public int getAnswer() {
        return answer;
    }

}
