package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lee.test.R;

import java.util.List;

import activity.MainActivity;

import model.Music;
import utils.MusicUtils;

public class MusicAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragmentList;
    public MusicAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
        super(supportFragmentManager);
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView ==null){
            holder = new ViewHolder();
            //引入布局
            convertView = View.inflate(context, R.layout.activity_main, null);
        //实例化对象
            holder.song = convertView.findViewById(R.id.item_mymusic_song);
            holder.singer = convertView.findViewById(R.id.item_mymusic_singer);
            holder.duration = convertView.findViewById(R.id.item_mymusic_duration);
            holder.position = convertView.findViewById(R.id.item_mymusic_postion);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.song.setText(musiclist.get(position).title.toString());
        holder.singer.setText(musiclist.get(position).artist.toString());
        //时间需要转换一下
        int duration = (int) musiclist.get(position).duration;
        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(time);
        holder.position.setText(position+1+"");

        return convertView;

    }

    class ViewHolder{
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号

    }*/
}
