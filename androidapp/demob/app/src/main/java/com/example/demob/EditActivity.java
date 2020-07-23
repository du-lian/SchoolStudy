package com.example.demob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private EditText edtStuno;
    private EditText edtName;
    private EditText edtAge;
    private EditText edtSex;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtStuno = findViewById(R.id.edt_stuno);
        edtName = findViewById(R.id.edt_name);
        edtAge = findViewById(R.id.edt_age);
        edtSex = findViewById(R.id.edt_sex);
        btnSave = findViewById(R.id.btn_save);

        String stuno = getIntent().getStringExtra("STUNO");
        String name = getIntent().getStringExtra("NAME");
        int age = getIntent().getIntExtra("AGE", 0);
        int sex = getIntent().getIntExtra("SEX", 1);

        edtStuno.setText(stuno);
        edtName.setText(name);
        edtAge.setText(String.valueOf(age));
        edtSex.setText(String.valueOf(sex));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("STUNO", edtStuno.getText().toString());
                intent.putExtra("NAME", edtName.getText().toString());
                intent.putExtra("AGE", Integer.parseInt(edtAge.getText().toString()));
                intent.putExtra("SEX", Integer.parseInt(edtSex.getText().toString()));
                setResult(3001, intent);
                finish();
            }
        });
    }
}
