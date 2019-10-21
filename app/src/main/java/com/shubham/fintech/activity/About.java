package com.shubham.fintech.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.browser.browseractions.BrowserActionsIntent;

import com.shubham.fintech.R;
import com.shubham.fintech.Url;

import de.hdodenhof.circleimageview.CircleImageView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView name=findViewById(R.id.username);
        TextView dev=findViewById(R.id.dev);
        CircleImageView shubham=findViewById(R.id.pic);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }
        });
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }
        });
        shubham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }
        });
    }

    private void go() {
        String url="https://www.fb.com/gptshubham595";

        Intent browser=new Intent(getApplicationContext(), Url.class);
        browser.putExtra("heading","MEET SHUBHAM");
        browser.putExtra("url",url);
        startActivity(browser);
    }
}
