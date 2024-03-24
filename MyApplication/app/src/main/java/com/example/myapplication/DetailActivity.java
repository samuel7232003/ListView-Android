package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityDetailBinding;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

public class DetailActivity extends AppCompatActivity {
    //private EditText tvDetail;
    //private Button btnChance;
    private Integer position;

    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //setContentView(R.layout.activity_detail);
        //tvDetail = findViewById(R.id.tv_detail);
        Intent receivedIntent = getIntent();

        if(receivedIntent != null) {
            String data = receivedIntent.getStringExtra("number");
            position = receivedIntent.getIntExtra("position", -8);
            binding.tvDetail.setText(data);
        }

        binding.btnChance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("number_", binding.tvDetail.getText().toString());
                returnIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}