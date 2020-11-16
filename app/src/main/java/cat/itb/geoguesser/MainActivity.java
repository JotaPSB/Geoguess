package cat.itb.geoguesser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonOption1;
    Button buttonOption2;
    Button buttonOption3;
    Button buttonOption4;
    ProgressBar progressBar;
    ImageView flagQuestion;
    QuizViewModel viewModel;
    TextView numberQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);
        buttonOption3 = findViewById(R.id.buttonOption3);
        buttonOption4 = findViewById(R.id.buttonOption4);
        buttonOption1.setOnClickListener(this);
        buttonOption2.setOnClickListener(this);
        buttonOption3.setOnClickListener(this);
        buttonOption4.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        flagQuestion = findViewById(R.id.flagsQuestion);
        numberQuestion=findViewById(R.id.numberQuestions);
        nextQuestion();
    }
    public void setButton(Button button, int answer){
        button.setText(answer);
        Button[] buttons = new Button[]{buttonOption1,buttonOption2,buttonOption3,buttonOption4};
        for (Button value : buttons) {
            int name;
            if (value != button) {
                do {
                    name = viewModel.answers[viewModel.randomNumber(viewModel.answers.length)];
                    if (name != answer) {
                        value.setText(name);
                    }
                } while (name == answer);
            }

        }
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == viewModel.getButton()){
            Toast.makeText(this, "Correcto",Toast.LENGTH_SHORT).show();
            viewModel.cambiarNota(1);
        }else{
            Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
            viewModel.cambiarNota(-0.25);
        }
        if(viewModel.questions.size()>1) {
            nextQuestion();
        }else {
            finalQuestion();
        }
    }
    public void nextQuestion(){
        viewModel.moveIndex();
        numberQuestion.setText("Pregunta "+ viewModel.cont+" de 10 preguntas");
        flagQuestion.setImageResource(viewModel.getQuestionFlag());
        switch (viewModel.getButton()){
            case R.id.buttonOption1:
                setButton(buttonOption1, viewModel.getAnswer());
                break;
            case R.id.buttonOption2:
                setButton(buttonOption2,viewModel.getAnswer());
                break;
            case R.id.buttonOption3:
                setButton(buttonOption3,viewModel.getAnswer());
                break;
            case R.id.buttonOption4:
                setButton(buttonOption4,viewModel.getAnswer());
                break;
        }
    }
    public void finalQuestion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ha finalizador el test!");
        builder.setMessage("Tu nota es: "+ viewModel.nota);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}