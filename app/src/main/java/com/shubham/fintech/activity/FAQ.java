package com.shubham.fintech.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shubham.fintech.R;
public class FAQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        TextView github=findViewById(R.id.lic7);
        TextView feed=findViewById(R.id.lic9);
        TextView main=findViewById(R.id.lic10);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                github();
            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail();
            }
        });
    }

    private void mail() {
        Intent i=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","gptshubham595@gmail.com",null));
        i.putExtra(Intent.EXTRA_SUBJECT,"Regarding DREAMIITG");
        i.putExtra(Intent.EXTRA_TEXT,"Hello Shubham, This is reagarding your DREAMIITG app, I wanted to contact you .");
        startActivity(Intent.createChooser(i,"Send email.."));
    }

    private void github() {
        String url="https://bit.ly/gitshubham";
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }

    private void feed() {
        String url="https://bit.ly/feediitg";
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }
}
