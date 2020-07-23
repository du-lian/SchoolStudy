package com.example.democ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GradeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Grade> gradeArrayList;
    private IOnDataChangeListener listener;

    public GradeAdapter(Context context, ArrayList<Grade> gradeArrayList, IOnDataChangeListener listener) {
        this.context = context;
        this.gradeArrayList = gradeArrayList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return gradeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gradeArrayList.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_grade, parent,false);

            GradeViewHolder viewHold = new GradeViewHolder();
            viewHold.tvStuName = convertView.findViewById(R.id.tv_name);
            viewHold.tvAndorid = convertView.findViewById(R.id.tv_android);
            viewHold.tvEnglish  = convertView.findViewById(R.id.tv_english);
            viewHold.ivGradeDel  = convertView.findViewById(R.id.iv_del);

            convertView.setTag(viewHold);

        }

        final Grade grade = gradeArrayList.get(position);
        GradeViewHolder ViewHold = (GradeViewHolder) convertView.getTag();
        ViewHold.tvStuName.setText(grade.getStuname());

        ViewHold.tvAndorid.setText(String.valueOf(grade.getAndroid()));
        ViewHold.tvEnglish.setText(String.valueOf(grade.getEnglish()));
        ViewHold.ivGradeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.del(grade);
            }
        });

        return convertView;
    }
}
