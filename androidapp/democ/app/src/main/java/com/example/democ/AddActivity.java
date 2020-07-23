package com.example.democ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText edtStuname;
    private EditText edtAndroid;
    private EditText edtEnglish;

    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtStuname = findViewById(R.id.edt_name);
        edtAndroid = findViewById(R.id.edt_android);
        edtEnglish = findViewById(R.id.edt_english);

        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stuname = edtStuname.getText().toString();

                int android = Integer.parseInt(edtAndroid.getText().toString());
                int english = Integer.parseInt(edtEnglish.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("STUNAME", edtStuname.getText().toString());

                intent.putExtra("ANDROID", Integer.parseInt(edtAndroid.getText().toString()));
                intent.putExtra("ENGLISH", Integer.parseInt(edtEnglish.getText().toString()));

                setResult(2001, intent);
                finish();
            }
        });
    }
}
