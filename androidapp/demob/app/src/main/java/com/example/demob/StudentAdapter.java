package com.example.demob;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;


public class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> studentArrayList;
    private IOnDataChangeListener listener;

    public StudentAdapter(Context context, ArrayList<Student> studentArrayList, IOnDataChangeListener listener) {
        this.context = context;
        this.studentArrayList = studentArrayList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_student, parent,false);

            StuViewHold viewHold = new StuViewHold();
            viewHold.tvStuId = convertView.findViewById(R.id.tv_id);
            viewHold.tvStuName = convertView.findViewById(R.id.tv_name);
            viewHold.tvStuAge  = convertView.findViewById(R.id.tv_age);
            viewHold.tvStuSex  = convertView.findViewById(R.id.tv_sex);
            viewHold.ivStuDel  = convertView.findViewById(R.id.iv_del);

            convertView.setTag(viewHold);

        }

        final Student student = studentArrayList.get(position);
        StuViewHold stuViewHold = (StuViewHold) convertView.getTag();
        stuViewHold.tvStuId.setText(student.getStuid());
        stuViewHold.tvStuName.setText(student.getStuname());
        stuViewHold.tvStuAge.setText(String.valueOf(student.getStuage()));
        stuViewHold.tvStuSex.setText(String.valueOf(student.getStusex()));
        stuViewHold.ivStuDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.del(student);
            }
        });

        return convertView;
    }
}
