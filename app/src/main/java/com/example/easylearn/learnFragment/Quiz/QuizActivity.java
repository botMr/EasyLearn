package com.example.easylearn.learnFragment.Quiz;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.HideSystemUI;
import com.example.easylearn.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionText,quizText,quizResult;
    private Button mAnswerButton1, mAnswerButton2, mAnswerButton3, mAnswerButton4, mNextButton;
    private int currentQuestionIndex;
    private List<QuestionModel> questionList;
    private int score = 0;
    private Button selectedButton = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        HideSystemUI.hideSystemUI(this);

        questionText = findViewById(R.id.question);
        mAnswerButton1 = findViewById(R.id.btn_choose1);
        mAnswerButton2 = findViewById(R.id.btn_choose2);
        mAnswerButton3 = findViewById(R.id.btn_choose3);
        mAnswerButton4 = findViewById(R.id.btn_choose4);
        mNextButton = findViewById(R.id.btn_next);
        quizText = findViewById(R.id.quiz_number);

        int testNumber = getIntent().getIntExtra("int",0);
        String quizNumber = getIntent().getStringExtra("quizNumber");

        if (testNumber == 5){
            Test5();
            quizText.setText(quizNumber);
        } else if (testNumber == 22){
            Test22();
            quizText.setText(quizNumber);
        } else if (testNumber == 23){
            Test23();
            quizText.setText(quizNumber);
        } else if (testNumber == 24) {
            Test24();
            quizText.setText(quizNumber);
        }else if (testNumber == 26) {
            Test26();
            quizText.setText(quizNumber);
        }

    }
    private void Test26(){
        questionList = new ArrayList<>();
        questionList.add(new QuestionModel(getResources().getString(R.string.question26_1), "9825", "1234", "2345", "7892", "9825"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question26_2), "7892", "2935", "9825", "1234", "2935"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question26_3), "2345", "9825", "5764", "2935", "5764"));

        showNextQuestion();

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mNextButton.setOnClickListener(v -> {
            if (selectedButton == null){
                Toast.makeText(this,"Пожалуйста, выберите ответ перед переходом к следующему вопросу!",Toast.LENGTH_SHORT).show();
            } else {
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    showNextQuestion();
                } else {
                    showResult();
                }
                restColor();
            }
        });
    }
    private void Test24(){
        questionList = new ArrayList<>();
        questionList.add(new QuestionModel(getResources().getString(R.string.question24_1), "варварски", "исчезают", "уничтожили", "истребили.", "варварски"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question24_2), "доброльно", "благотворную", "стремиться", "воздействии", "благотворную"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question24_3), "зверниц", "что есть сил", "во весь дух", "что есть духу", "во весь дух"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question24_4), "выпадают", "ресурс", "занятых", "умудряюсь", "умудряюсь"));

        showNextQuestion();

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mNextButton.setOnClickListener(v -> {
            if (selectedButton == null){
                Toast.makeText(this,"Пожалуйста, выберите ответ перед переходом к следующему вопросу!",Toast.LENGTH_SHORT).show();
            } else {
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    showNextQuestion();
                } else {
                    showResult();
                }
                restColor();
            }
        });
    }
    private void Test23(){
        questionList = new ArrayList<>();
        questionList.add(new QuestionModel(getResources().getString(R.string.question23_1), "45", "13", "35", "23", "45"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question23_2), "24", "125", "12", "34", "125"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question23_3), "21", "234", "25", "23", "25"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question23_4), "145", "25", "12", "15", "25"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question23_5), "145", "124", "23", "145", "145"));

        showNextQuestion();

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mNextButton.setOnClickListener(v -> {
            if (selectedButton == null){
                Toast.makeText(this,"Пожалуйста, выберите ответ перед переходом к следующему вопросу!",Toast.LENGTH_SHORT).show();
            } else {
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    showNextQuestion();
                } else {
                    showResult();
                }
                restColor();
            }
        });
    }
    private void Test22(){
        questionList = new ArrayList<>();
        questionList.add(new QuestionModel(getResources().getString(R.string.question22_1), "12", "13", "15", "134", "134"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question22_2), "24", "13", "12", "34", "24"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question22_3), "21", "234", "14", "23", "234"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question22_4), "145", "234", "12", "15", "145"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question22_5), "145", "124", "23", "14", "124"));

        showNextQuestion();

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mNextButton.setOnClickListener(v -> {
            if (selectedButton == null){
                Toast.makeText(this,"Пожалуйста, выберите ответ перед переходом к следующему вопросу!",Toast.LENGTH_SHORT).show();
            } else {
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    showNextQuestion();
                } else {
                    showResult();
                }
                restColor();
            }
        });
    }
    private void Test5() {
        questionList = new ArrayList<>();
        questionList.add(new QuestionModel(getResources().getString(R.string.question5_1), "Боло́тистый", "Ледовом", "Дипломант", "Высотный", "Ледовом"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question5_2), "Артистический", "Артистичный", "Бедствующем", "Демократичное", "Демократичное"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question5_3), "Благотворительный", "Выгода", "Драматический", "Исполнительский", "Благотворительный"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question5_4), "Благотворительный", "Вражеская", "Наполнить", "Информационный", "Вражеская"));
        questionList.add(new QuestionModel(getResources().getString(R.string.question5_5), "Различие", "Признательный", "Исполнительная", "Унизить", "Исполнительная"));

        showNextQuestion();

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mAnswerButton1.setOnClickListener(v -> checkAnswer(mAnswerButton1));
        mAnswerButton2.setOnClickListener(v -> checkAnswer(mAnswerButton2));
        mAnswerButton3.setOnClickListener(v -> checkAnswer(mAnswerButton3));
        mAnswerButton4.setOnClickListener(v -> checkAnswer(mAnswerButton4));

        mNextButton.setOnClickListener(v -> {
            if (selectedButton == null){
                Toast.makeText(this,"Пожалуйста, выберите ответ перед переходом к следующему вопросу!",Toast.LENGTH_SHORT).show();
            } else {
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    showNextQuestion();
                } else {
                    showResult();
                }
                restColor();
            }
        });
    }
    private void checkAnswer(Button answerButton) {
        if (selectedButton != null) {
            selectedButton.setBackgroundColor(getResources().getColor(R.color.light));
        }
        if (answerButton == selectedButton) {
            selectedButton = null;
        } else {
            selectedButton = answerButton;
            selectedButton.setBackgroundColor(Color.GREEN);
            String selectedAnswer = answerButton.getText().toString();
            if (selectedAnswer.equals(questionList.get(currentQuestionIndex).getCorrectAnswer())) {
                score++;
            }
        };
    }
    private void showResult() {
        // Показать AlertDialog с результатом
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.show_quiz,null);
        builder.setView(dialogView);
        quizResult = dialogView.findViewById(R.id.quiz_result);
        String textResult = "Твой результат: " + score;
        quizResult.setText(textResult);
        builder.setPositiveButton("Отлично", (dialog, which) -> finish());
        builder.create().show();
    }

    private void showNextQuestion() {
        questionText.setText(questionList.get(currentQuestionIndex).getQuestion());
        mAnswerButton1.setText(questionList.get(currentQuestionIndex).getAnswer1());
        mAnswerButton2.setText(questionList.get(currentQuestionIndex).getAnswer2());
        mAnswerButton3.setText(questionList.get(currentQuestionIndex).getAnswer3());
        mAnswerButton4.setText(questionList.get(currentQuestionIndex).getAnswer4());
    }

    private void restColor(){
        mAnswerButton1.setBackgroundColor(getResources().getColor(R.color.light));
        mAnswerButton2.setBackgroundColor(getResources().getColor(R.color.light));
        mAnswerButton3.setBackgroundColor(getResources().getColor(R.color.light));
        mAnswerButton4.setBackgroundColor(getResources().getColor(R.color.light));
        selectedButton = null;
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}
