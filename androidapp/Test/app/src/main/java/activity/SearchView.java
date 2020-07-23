package activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lee.test.R;

public class SearchView extends LinearLayout implements View.OnClickListener {
    private EditText etInput;
    private ImageView ivSearch;
    private Button btnBack;
    private Context mContext;
    private ListView lvTips;
    private ArrayAdapter<String> mHintAdapter;
    private ArrayAdapter<String> mAutoCompleteAdapter;
    private SearchViewListener mListener;

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onClick(View v) {
        btnBack=findViewById(R.id.search_btn_back);

        btnBack.setOnClickListener(this);
        Intent intent = new Intent();
        intent.setClassName("MainActivity.this","MainActivity.class");



    }
}
