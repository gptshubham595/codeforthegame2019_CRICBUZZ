package com.shubham.fintech.quiz.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shubham.fintech.R;
import com.shubham.fintech.quiz.activity.SettingActivity;
import com.shubham.fintech.quiz.activity.ReviewActivity;
import com.shubham.fintech.quiz.AppController;
import com.shubham.fintech.quiz.helper.CircularProgressIndicator2;
import com.shubham.fintech.quiz.helper.SettingsPreferences;
import com.shubham.fintech.quiz.Constant;

public class FragmentComplete extends Fragment implements android.view.View.OnClickListener {
    private Button btnPlayAgain, btnShare, btnRateus, btnQuite, btnReview;
    private TextView txt_result_title, txt_score, txtLevelTotalScore, txtLevel, txt_right, txt_wrong, point, coin_count, tvLevel;
    ImageView setting, back;
    private CircularProgressIndicator2 result_prog;
    private SharedPreferences settings;
    public static Context mcontex;
    int levelNo = 1;
    int lastLevelScore = 0;
    int coin = 0;
    int totalScore = 0;
    private View v;
    public FragmentPlay fragmentPlay;
    public FragmentLock fragmentLevel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_completeq, container, false);
        final int[] CLICKABLES = new int[]{R.id.btn_playagain, R.id.btn_share, R.id.btn_quite};
        for (int i : CLICKABLES) {
            v.findViewById(i).setOnClickListener(this);
        }
        fragmentPlay = new FragmentPlay();
        fragmentLevel = new FragmentLock();
        mcontex = getActivity().getBaseContext();
        result_prog = (CircularProgressIndicator2) v.findViewById(R.id.result_progress);
        result_prog.SetAttributes1();
        settings = getActivity().getSharedPreferences(
                SettingsPreferences.SETTING_Quiz_PREF, 0);
        txt_result_title = (TextView) v.findViewById(R.id.txt_result_title);
        txt_right = (TextView) v.findViewById(R.id.right);
        txt_wrong = (TextView) v.findViewById(R.id.wrong);
        txt_score = (TextView) v.findViewById(R.id.txtScore);
        tvLevel = (TextView) v.findViewById(R.id.tvLevel);
        setting = (ImageView) v.findViewById(R.id.setting);
        back = (ImageView) v.findViewById(R.id.back);
        tvLevel.setText("Play Again");
        coin = SettingsPreferences.getPoint(mcontex);
        totalScore = settings.getInt(SettingsPreferences.TOTAL_SCORE, 0);
        txt_score.setText("" + totalScore);
        lastLevelScore = settings.getInt(
                SettingsPreferences.LAST_LEVEL_SCORE, 0);
        txtLevelTotalScore = (TextView) v.findViewById(R.id.total_score);
        point = (TextView) v.findViewById(R.id.earncoin);
        coin_count = (TextView) v.findViewById(R.id.coin_count);
        point.setText("" + Constant.level_coin);
        coin_count.setText("" + coin);
        txtLevelTotalScore.setText("" + lastLevelScore);
        txtLevel = (TextView) v.findViewById(R.id.txtLevel);
        btnPlayAgain = (Button) v.findViewById(R.id.btn_playagain);
        btnRateus = (Button) v.findViewById(R.id.btn_rate);
        btnQuite = (Button) v.findViewById(R.id.btn_quite);
        btnReview = (Button) v.findViewById(R.id.btn_review);
        btnPlayAgain.setOnClickListener(this);
        btnRateus.setOnClickListener(this);
        btnQuite.setOnClickListener(this);
        btnReview.setOnClickListener(this);
        if (FragmentPlay.reviews.size() == 0) {
            btnReview.setVisibility(View.GONE);
        }
        btnShare = (Button) v.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(this);
        boolean islevelcomplted = settings.getBoolean(
                SettingsPreferences.IS_LAST_LEVEL_COMPLETED, false);
        levelNo = settings.getInt(SettingsPreferences.LEVEL_COMPLETED, 0);

        txtLevel.setText("Level" + ": " + Constant.RequestlevelNo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {

                    try {
                        AppController.StopSound();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SettingsPreferences.getSoundEnableDisable(getActivity())) {
                    Constant.backSoundonclick(getActivity());
                }
                if (SettingsPreferences.getVibration(getActivity())) {
                    Constant.vibrate(getActivity(), Constant.VIBRATION_DURATION);
                }
                Intent playQuiz = new Intent(getActivity(), SettingActivity.class);
                startActivity(playQuiz);
                getActivity().overridePendingTransition(R.anim.open_next, R.anim.close_next);
            }
        });
        if (islevelcomplted) {

            levelNo--;
            if (Constant.NO_OF_QUIZ_LEVEL == Constant.RequestlevelNo) {
                btnPlayAgain.setText("Play Again");
                Constant.RequestlevelNo = 1;

            } else {
                txt_result_title.setText(getActivity().getString(R.string.completed));
                btnPlayAgain.setText(getResources().getString(R.string.next_play));
                Constant.RequestlevelNo = Constant.RequestlevelNo + 1;
                tvLevel.setText(getResources().getString(R.string.next_play));
            }
        } else {

            txt_result_title.setText(getActivity().getString(R.string.not_completed));
            btnPlayAgain.setText(getResources().getString(R.string.play_next));
        }
        result_prog.setCurrentProgress((double) getPercentageCorrect(Constant.TotalQuestion, Constant.CoreectQuetion));
        // result_prog.setUnit("%");
        txt_right.setText("" + Constant.CoreectQuetion);
        txt_wrong.setText("" + Constant.WrongQuation);
        return v;
    }

    public static float getPercentageCorrect(int questions, int correct) {
        float proportionCorrect = ((float) correct) / ((float) questions);
        return proportionCorrect * 100;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_playagain:

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.fragment_container, fragmentPlay, "fragment");
                ft.addToBackStack("tag");
                ft.commit();
                break;
            case R.id.btn_share:

                final String sharetext = "I have finished Level No : " + levelNo + " with " + lastLevelScore + " Score in Quiz";
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, sharetext);
                share.putExtra(Intent.EXTRA_TEXT, "" + sharetext + "+https://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
                startActivity(Intent.createChooser(share, "Share  Quiz !"));
                break;
            case R.id.btn_rate:

                shareClicked(getString(R.string.rateapp),
                        AppController.getAppUrl());
                break;
            case R.id.btn_quite:

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.btn_review:


                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    private void shareClicked(String subject, String text) {

    }

}