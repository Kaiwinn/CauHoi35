package com.example.cuhi345t;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Question mQuestion;
    private TextView tvQuestion, tvContentQuestion;
    private TextView tvAnswer1,tvAnswer2,tvAnswer3, tvAnswer4;
    private List<Question> mListQuestions;
    private int currentQuestion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        mListQuestions = getListQuestion();
        if(mListQuestions.isEmpty()){
            return;
        }
        setDataQuestion(mListQuestions.get(currentQuestion));
    }
    private void setDataQuestion(Question question) {
        if(question == null){
            return;
        }
        mQuestion = question;
//ReSetBackGround Khi Sang Câu Mới
        tvAnswer1.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer2.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer3.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer4.setBackgroundResource(R.drawable.bg_blue_corner_30);

        String titleQuestion = "Question "+ question.getNumber();
        tvQuestion.setText(titleQuestion);
        tvContentQuestion.setText(question.getContent());
        tvAnswer1.setText(question.getListAnswer().get(0).getContent());
        tvAnswer2.setText(question.getListAnswer().get(1).getContent());
        tvAnswer3.setText(question.getListAnswer().get(2).getContent());
        tvAnswer4.setText(question.getListAnswer().get(3).getContent());

        tvAnswer1.setOnClickListener(this);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4.setOnClickListener(this);
    }

    private void mapping() {
        tvQuestion = findViewById(R.id.tv_Questionn);
        tvContentQuestion = findViewById(R.id.tv_content_quétion);
        tvAnswer1 = findViewById(R.id.tvAnswer1);
        tvAnswer2 = findViewById(R.id.tvAnswer2);
        tvAnswer3 =findViewById(R.id.tvAnswer3);
        tvAnswer4 = findViewById(R.id.tvAnswer4);
    }
    private List<Question> getListQuestion(){
        List<Question>  list = new ArrayList<>();
        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer("Vương Phạm",false));
        answerList1.add(new Answer("Johnny Dang",true));
        answerList1.add(new Answer("Thỏ Bảy Màu",false));
        answerList1.add(new Answer("Cả 3 Người Trên",false));


        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer("0",false));
        answerList2.add(new Answer("4",false));
        answerList2.add(new Answer("8",true));
        answerList2.add(new Answer("12",false));

       List<Answer> answerList3 = new ArrayList<>();
        answerList3.add(new Answer("Vì Alaska có tôm hùm",false));
        answerList3.add(new Answer("Vì địa hình hiểm trở",false));
        answerList3.add(new Answer("Vì sát thủ rất sợ lạnh",true));
        answerList3.add(new Answer("Vì Vương Phạm yêu cầu",false));

        List<Answer> answerList4 = new ArrayList<>();
        answerList4.add(new Answer("Tiệm bán kem Chuối",false));
        answerList4.add(new Answer("Tiệm bán diamond",false));
        answerList4.add(new Answer("Tiệm bán Đá Bào",true));
        answerList4.add(new Answer("Tiệm bán trà sữa",false));

        list.add(new Question("Ai là người đã lừa đảo chiếm đoạt 30 Tỷ của Khoa Bug", 1, answerList1));
        list.add(new Question("Trong video VÁN BÀI LẬT NGỬA, Johnny Dang đã đảo mắt bao nhiêu lần", 2, answerList2 ));
        list.add(new Question("Tại sao Khoa Bug lại tháo chạy đến Alaska.", 3, answerList3 ));
        list.add(new Question("Trong video gần đây nhất, thì Vương Phạm đã gọi cơ sở kinh doanh Johnny Dang là gì", 4, answerList4 ));
        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvAnswer1:
                tvAnswer1.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer1, mQuestion, mQuestion.getListAnswer().get(0));
                break;
            case R.id.tvAnswer2:
                tvAnswer2.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer2, mQuestion, mQuestion.getListAnswer().get(1));
                break;
            case R.id.tvAnswer3:
                tvAnswer3.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer3, mQuestion, mQuestion.getListAnswer().get(2));
                break;
            case R.id.tvAnswer4:
                tvAnswer4.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer4, mQuestion, mQuestion.getListAnswer().get(3));
                break;
        }

    }

    private void checkAnswer( final TextView textView, Question question, Answer answer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(answer.isCorrect()){
                    textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                    nextQuestion();
                }else{
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    showAnswerCorect(question);
                    gameOver();
                }

            }
        }, 1000);
    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDL("Mày Chơi Ng* quá !");
            }
        }, 1000);
    }

    private void showAnswerCorect(Question question) {
        if(question == null || question.getListAnswer()==null || question.getListAnswer().isEmpty()){
            return;
        }
        if(question.getListAnswer().get(0).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);

        }else if(question.getListAnswer().get(1).isCorrect()){
            tvAnswer2.setBackgroundResource(R.drawable.bg_green_corner_30);

        }else if(question.getListAnswer().get(2).isCorrect()){
            tvAnswer3.setBackgroundResource(R.drawable.bg_green_corner_30);

        }else if(question.getListAnswer().get(3).isCorrect()){
            tvAnswer4.setBackgroundResource(R.drawable.bg_green_corner_30);

        }

    }

    private void nextQuestion() {
        if(currentQuestion == mListQuestions.size()-1){
            showDialog("Chúc mừng mày đã chiến thắng!");


        }else {
            currentQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataQuestion(mListQuestions.get(currentQuestion));
                }
            }, 1000);

        }
    }
    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("YOU WIN");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentQuestion = 0;
                setDataQuestion(getListQuestion().get(currentQuestion));
                dialogInterface.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private void showDL(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("GAME OVER");

        builder.setCancelable(false);

        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentQuestion = 0;
                setDataQuestion(getListQuestion().get(currentQuestion));
                dialogInterface.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}