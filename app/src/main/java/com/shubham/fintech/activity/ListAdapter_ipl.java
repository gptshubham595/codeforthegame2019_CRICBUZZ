package com.shubham.fintech.activity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shubham.fintech.R;
import com.squareup.picasso.Picasso;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_ipl extends BaseAdapter {

    iplschedule main;

    ListAdapter_ipl(iplschedule main) {

        this.main = main;
    }


    @Override
    public int getCount() {
        return main.live_data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolderItem {
        TextView team1_vs_team2, about, date;
        TextView vs;
        TextView location;
        TextView time;
        ImageView img_datapath;
        ImageView img_datapath1;
        String team1_id, team2_id, team1_name, team2_name, match_desc;
        public String flag_url1;
        public String flag_url2;
        public String start_time;
        public String end_time;
        public String out;
        public TextView team1_name_ipl;
        public TextView team2_name_ipl;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ipc_cell, null);
            holder.img_datapath = (ImageView) convertView.findViewById(R.id.team1_img);
            holder.img_datapath1 = (ImageView) convertView.findViewById(R.id.team2_img);
            holder.team1_vs_team2 = (TextView) convertView.findViewById(R.id.team1_vs_team2);
            holder.vs = (TextView) convertView.findViewById(R.id.vs);
            holder.team1_name_ipl = (TextView) convertView.findViewById(R.id.team1_name_ipl);
            holder.team2_name_ipl = (TextView) convertView.findViewById(R.id.team2_name_ipl);
            holder.about = (TextView) convertView.findViewById(R.id.about);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.location = (TextView) convertView.findViewById(R.id.location);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        holder.team1_id = this.main.live_data_list.get(position).team1_id;
        holder.team2_id = this.main.live_data_list.get(position).team2_id;
        holder.team1_name = this.main.live_data_list.get(position).team1_name_new;
        holder.team2_name = this.main.live_data_list.get(position).team2_name_new;
        holder.match_desc = this.main.live_data_list.get(position).match_desc;
        holder.start_time = this.main.live_data_list.get(position).start_time;
        holder.end_time = this.main.live_data_list.get(position).start_time;
        holder.flag_url1 = "http://i.cricketcb.com/cbzandroid/2.0/flags/team_" + holder.team1_id + ".png";
        holder.flag_url2 = "http://i.cricketcb.com/cbzandroid/2.0/flags/team_" + holder.team2_id + ".png";
        Picasso.get().load(holder.flag_url1).into(holder.img_datapath);
        Picasso.get().load(holder.flag_url2).into(holder.img_datapath1);
        holder.team1_vs_team2.setText(holder.team1_name + " VS " + holder.team2_name + "," + holder.match_desc);
        holder.vs.setText(holder.team1_name + " VS " + holder.team2_name + "," + holder.match_desc);
        holder.about.setText(this.main.live_data_list.get(position).series_name);
        holder.location.setText(this.main.live_data_list.get(position).location);
        holder.team1_name_ipl.setText(holder.team1_name);
        holder.team2_name_ipl.setText(holder.team2_name);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return convertView;
    }
}
