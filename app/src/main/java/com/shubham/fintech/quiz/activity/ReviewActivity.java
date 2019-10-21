package com.shubham.fintech.quiz.activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shubham.fintech.R;
import com.shubham.fintech.quiz.fragment.FragmentPlay;
import com.shubham.fintech.quiz.model.QuizLevel;
import com.shubham.fintech.quiz.model.Review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewActivity extends AppCompatActivity {

    public CardView cardView_A, cardView_B, cardView_C, cardView_D;
    public TextView txtQuestion, btnOpt1, btnOpt2, btnOpt3, btnOpt4, tvLevel, tvQuestionNo;
    public ImageView prev, next, back, setting;
    public RelativeLayout layout_A, layout_B, layout_C, layout_D;
    private int questionIndex = 0;
    // QuizLevel level;
    private int NO_OF_QUESTION;

    public ArrayList<Review> reviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewq);
        btnOpt1 = (TextView) findViewById(R.id.btnOpt1);
        btnOpt2 = (TextView) findViewById(R.id.btnOpt2);
        btnOpt3 = (TextView) findViewById(R.id.btnOpt3);
        btnOpt4 = (TextView) findViewById(R.id.btnOpt4);
        cardView_A = (CardView) findViewById(R.id.cardView_A);
        cardView_B = (CardView) findViewById(R.id.cardView_B);
        cardView_C = (CardView) findViewById(R.id.cardView_C);
        cardView_D = (CardView) findViewById(R.id.cardView_D);
        txtQuestion = (TextView) findViewById(R.id.question);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        layout_A = (RelativeLayout) findViewById(R.id.a_layout);
        layout_B = (RelativeLayout) findViewById(R.id.b_layout);
        layout_C = (RelativeLayout) findViewById(R.id.c_layout);
        layout_D = (RelativeLayout) findViewById(R.id.d_layout);
        prev = (ImageView) findViewById(R.id.prev);
        next = (ImageView) findViewById(R.id.next);
        back = (ImageView) findViewById(R.id.back);
        setting = (ImageView) findViewById(R.id.setting);
        tvQuestionNo = (TextView) findViewById(R.id.questionNo);

        setting.setVisibility(View.GONE);
        tvLevel.setText(getString(R.string.review_answer));
        reviews = FragmentPlay.reviews;
        // level = FragmentPlay.level;
        NO_OF_QUESTION = reviews.size();
        ReviewQuestion();
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionIndex > 0) {
                    questionIndex--;
                    ReviewQuestion();
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionIndex < (reviews.size() - 1)) {
                    System.out.println("*** no " + questionIndex);
                    questionIndex++;

                    ReviewQuestion();

                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void ReviewQuestion() {
        if (questionIndex < reviews.size()) {
            int temp = questionIndex;

            //String imgName = level.getQuestion().get(questionIndex).getQuestion();

            txtQuestion.setText(reviews.get(questionIndex).getQuestion());
            ArrayList<String> options = new ArrayList<String>();
            options.addAll(reviews.get(questionIndex).getOptionList());
            Collections.shuffle(options);
            btnOpt1.setText("" + options.get(0).trim());
            btnOpt2.setText("" + options.get(1).trim());
            btnOpt3.setText("" + options.get(2).trim());
            btnOpt4.setText("" + options.get(3).trim());
           /* tvQuestionNo.setText("your : " + reviews.get(questionIndex).getWrongAns()
                    + " \n " + (questionIndex + 1)
                    + "\n right :  " + reviews.get(questionIndex).getRightAns());*/
            tvQuestionNo.setText(" " + (questionIndex + 1) + "/" + reviews.size());

            if (btnOpt1.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getRightAns().trim())) {

                layout_A.setBackgroundResource(R.drawable.right_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
                if (btnOpt2.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_B.setBackgroundResource(R.drawable.wrong_gradient);
                    // layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt3.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_C.setBackgroundResource(R.drawable.wrong_gradient);
                    //  layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt4.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_D.setBackgroundResource(R.drawable.wrong_gradient);
                    //   layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                }

            } else if (btnOpt2.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getRightAns().trim())) {

                layout_B.setBackgroundResource(R.drawable.right_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
                if (btnOpt1.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_A.setBackgroundResource(R.drawable.wrong_gradient);
                    // layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt3.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_C.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    //  layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt4.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_D.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    // layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                }

            } else if (btnOpt3.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getRightAns().trim())) {

                layout_C.setBackgroundResource(R.drawable.right_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
                if (btnOpt1.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_A.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    // layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt2.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_B.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    // layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt4.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_D.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    //  layout_C.setBackgroundResource(R.drawable.bg_gradient);
                }

            } else if (btnOpt4.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getRightAns().trim())) {

                layout_D.setBackgroundResource(R.drawable.right_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                if (btnOpt1.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_A.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    // layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt2.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_B.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    // layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else if (btnOpt3.getText().toString().trim().equalsIgnoreCase(reviews.get(questionIndex).getWrongAns())) {
                    layout_C.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    //layout_D.setBackgroundResource(R.drawable.bg_gradient);
                }
               /* layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);*/

            } /*else if (btnOpt1.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                if (btnOpt1.getText().toString().trim().equalsIgnoreCase(level.getQuestion().get(questionIndex).getTrueAns().trim())) {
                    layout_A.setBackgroundResource(R.drawable.right_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else {
                    layout_A.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                }

            } else if (btnOpt2.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                if (btnOpt2.getText().toString().trim().equalsIgnoreCase(level.getQuestion().get(questionIndex).getTrueAns().trim())) {
                    layout_B.setBackgroundResource(R.drawable.right_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else {
                    layout_B.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                }

            } else if (btnOpt3.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                if (btnOpt3.getText().toString().trim().equalsIgnoreCase(level.getQuestion().get(questionIndex).getTrueAns().trim())) {
                    layout_C.setBackgroundResource(R.drawable.right_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                } else {
                    layout_C.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_D.setBackgroundResource(R.drawable.bg_gradient);
                }

            } else if (btnOpt4.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                if (btnOpt4.getText().toString().trim().equalsIgnoreCase(level.getQuestion().get(questionIndex).getTrueAns().trim())) {
                    layout_D.setBackgroundResource(R.drawable.right_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                } else {
                    layout_D.setBackgroundResource(R.drawable.wrong_gradient);
                    layout_A.setBackgroundResource(R.drawable.bg_gradient);
                    layout_B.setBackgroundResource(R.drawable.bg_gradient);
                    layout_C.setBackgroundResource(R.drawable.bg_gradient);
                }

            }*/
          /* else if (btnOpt1.getText().toString().trim().equalsIgnoreCase(FragmentPlay.wrongList.get(questionIndex))) {
                layout_A.setBackgroundResource(R.drawable.wrong_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
            } else if (btnOpt2.getText().toString().trim().equalsIgnoreCase(FragmentPlay.wrongList.get(questionIndex))) {
                layout_B.setBackgroundResource(R.drawable.wrong_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
            } else if (btnOpt3.getText().toString().trim().equalsIgnoreCase(FragmentPlay.wrongList.get(questionIndex))) {
                layout_C.setBackgroundResource(R.drawable.wrong_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
            } else if (btnOpt4.getText().toString().trim().equalsIgnoreCase(FragmentPlay.wrongList.get(questionIndex))) {
                layout_D.setBackgroundResource(R.drawable.wrong_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
            }*/

           /* if (btnOpt1.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                layout_A.setBackgroundResource(R.drawable.wrong_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
            } if (btnOpt2.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                layout_B.setBackgroundResource(R.drawable.wrong_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
            }  if (btnOpt3.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                layout_C.setBackgroundResource(R.drawable.wrong_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_D.setBackgroundResource(R.drawable.bg_gradient);
            }  if (btnOpt4.getText().toString().trim().equals(FragmentPlay.wrongList.get(questionIndex))) {
                layout_D.setBackgroundResource(R.drawable.wrong_gradient);
                layout_A.setBackgroundResource(R.drawable.bg_gradient);
                layout_B.setBackgroundResource(R.drawable.bg_gradient);
                layout_C.setBackgroundResource(R.drawable.bg_gradient);
            }*/
        }

    }
}
