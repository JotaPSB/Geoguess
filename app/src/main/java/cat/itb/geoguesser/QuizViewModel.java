package cat.itb.geoguesser;


import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizViewModel extends ViewModel {
    int[] answers = new int[]{
            R.string.uk,
            R.string.dinamarca,
            R.string.españa,
            R.string.macedonia,
            R.string.noruega,
            R.string.RepublicaCheca,
            R.string.suiza,
            R.string.irlanda,
            R.string.turquia,
            R.string.ucrania
    };
    ArrayList<QuestionModel> questions =new ArrayList<>(Arrays.asList(
            new QuestionModel(R.drawable.dinamarca,randomAnswer(),answers[1]),
            new QuestionModel(R.drawable.republicacheca,randomAnswer(),answers[5]),
            new QuestionModel(R.drawable.spain,randomAnswer(),answers[2]),
            new QuestionModel(R.drawable.macedonia,randomAnswer(),answers[3]),
            new QuestionModel(R.drawable.turkey,randomAnswer(),answers[8]),
            new QuestionModel(R.drawable.ucrania,randomAnswer(),answers[9]),
            new QuestionModel(R.drawable.uk,randomAnswer(),answers[0]),
            new QuestionModel(R.drawable.irlanda,randomAnswer(),answers[7]),
            new QuestionModel(R.drawable.noruega,randomAnswer(),answers[4]),
            new QuestionModel(R.drawable.suiza,randomAnswer(),answers[6])
    ));

    double nota;
    int randomIndex=-1;
    int cont=0;
    public int randomAnswer(){
        int[] buttons = {R.id.buttonOption1,R.id.buttonOption2,R.id.buttonOption3,R.id.buttonOption4};
        return  buttons[randomNumber(buttons.length)];
    }
    public void cambiarNota(double n){
        nota+=n;
    }
    public int getQuestionFlag(){
        return questions.get(randomIndex).getQuestion();
    }
    public int getAnswer(){
        return questions.get(randomIndex).getAnswer();
    }
    public int getButton(){
        return questions.get(randomIndex).getButton();
    }
    public void moveIndex(){
        if(randomIndex==-1) {
            randomIndex = randomNumber(questions.size());
            cont++;
        }else {
            questions.remove(randomIndex);
            randomIndex = randomNumber(randomNumber(questions.size()));
            cont++;
        }

    }
    public int randomNumber(int limit){
        return ((int) (Math.random()*(limit-1)));
    }
}
