package com.example.democ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private EditText edtStuname;
    private EditText edtAndroid;
    private EditText edtEnglish;

    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtStuname = findViewById(R.id.edt_name);
        edtAndroid = findViewById(R.id.edt_android);
        edtEnglish = findViewById(R.id.edt_english);

        btnSave = findViewById(R.id.btn_save);

        String stuname = getIntent().getStringExtra("STUNAME");

        int android = getIntent().getIntExtra("ANDROID", 60);
        int english = getIntent().getIntExtra("ENGLISH", 60);

        edtStuname.setText(stuname);

        edtAndroid.setText(String.valueOf(android));
        edtEnglish.setText(String.valueOf(english));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("STUNAME", edtStuname.getText().toString());

                intent.putExtra("ANDROID", Integer.parseInt(edtAndroid.getText().toString()));
                intent.putExtra("ENGLISH", Integer.parseInt(edtEnglish.getText().toString()));
                setResult(3001, intent);
                finish();
            }
        });
    }
}
