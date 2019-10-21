package com.shubham.fintech.quiz.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shubham.fintech.quiz.activity.MainActivity;
import com.shubham.fintech.R;
import com.shubham.fintech.quiz.activity.SettingActivity;
import com.shubham.fintech.quiz.AppController;
import com.shubham.fintech.quiz.helper.SettingsPreferences;
import com.shubham.fintech.quiz.Constant;
import com.shubham.fintech.quiz.model.SetLevel;

import java.util.ArrayList;
import java.util.List;

public class FragmentLock extends Fragment {
    LevelListAdapter adapter;

    public View v;
    private static int levelNo = 1;
    public static Context mcontex;
    List<SetLevel> levelList;
    RecyclerView recyclerView;
    ImageView back, setting;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.lock_fragmentq, container, false);
        mcontex = getActivity().getBaseContext();

        back = (ImageView) v.findViewById(R.id.back);
        setting = (ImageView) v.findViewById(R.id.setting);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        levelNo = SettingsPreferences.getNoCompletedLevel(mcontex);
        getActivity().setTitle(getString(R.string.select_level));
        levelList = new ArrayList<>();
        for (int i = 0; i < Constant.NO_OF_QUIZ_LEVEL; i++) {
            SetLevel card = new SetLevel("" + i, "Level" + " : " + (i + 1), levelNo);
            levelList.add(card);
        }

        adapter = new LevelListAdapter(getActivity(), levelList);
        recyclerView.setAdapter(adapter);


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


        return v;
    }

    public class LevelListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public int resourceLayout;
        public Activity activity;
        private List<SetLevel> cardList;
        public FragmentPlay fragmentPlay = new FragmentPlay();
        private int levelNo = 1;

        public LevelListAdapter(Activity activity, List<SetLevel> cardList) {
            this.cardList = cardList;
            this.activity = activity;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lock_itemq, parent, false);
            return new LevelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            LevelViewHolder viewHolder = (LevelViewHolder) holder;
            levelNo = SettingsPreferences.getNoCompletedLevel(activity);
            SetLevel card = cardList.get(position);
            viewHolder.title.setText(card.getEnglishTitle());
            viewHolder.question_no.setText("que : " + 10);
            if (card.getlock() >= position + 1) {
                viewHolder.lock.setImageResource(R.drawable.unlock);
            } else {
                viewHolder.lock.setImageResource(R.drawable.lock);
            }

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (SettingsPreferences.getSoundEnableDisable(activity)) {
                        Constant.backSoundonclick(activity);
                    }
                    if (SettingsPreferences.getVibration(activity)) {
                        Constant.vibrate(activity, Constant.VIBRATION_DURATION);
                    }
                    Constant.RequestlevelNo = position + 1;
                    if (levelNo >= position + 1) {
                        FragmentTransaction ft = ((MainActivity) activity).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, fragmentPlay, "fragment");
                        ft.addToBackStack("tag");
                        ft.commit();
                    } else {
                        Toast.makeText(activity, "Level is Locked", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return cardList.size();
        }

        public class LevelViewHolder extends RecyclerView.ViewHolder {
            TextView title, question_no;
            ImageView lock;
            CardView cardView;

            public LevelViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.item_title);
                question_no = (TextView) itemView.findViewById(R.id.question_no);
                lock = (ImageView) itemView.findViewById(R.id.lock);
                cardView = (CardView) itemView.findViewById(R.id.cardView);
            }
        }
    }


}
