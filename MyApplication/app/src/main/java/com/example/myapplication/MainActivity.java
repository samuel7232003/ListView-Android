package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewModel model;

    private ArrayList<Integer> arrayList;
    private ArrayAdapter<Integer> arrayAdapter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        binding.lvCount.setAdapter(arrayAdapter);

        model.getNumber().observe(this, new Observer<Integer>(){
            public void onChanged(Integer integer){
                binding.tvCount.setText("" + integer);
                arrayAdapter.clear();
                arrayAdapter.addAll(model.getArrayList());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.increaseNumber();
            }
        });

        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.reduceNumber();
            }
        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class );
                intent.putExtra("number", arrayList.get(position).toString());
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });

//      CACH 1:
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                tvCount.setText(""+ ++count);
//            }
//        });
//
//        btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                tvCount.setText(""+ --count);
//            }
//        });

//        CACH 2:
//        btnAdd.setOnClickListener(v -> {
//            int count = Integer.parseInt(tvCount.getText().toString());
//            tvCount.setText(""+ ++count);
//        });
//        btnRemove.setOnClickListener(v -> {
//            int count = Integer.parseInt(tvCount.getText().toString());
//            tvCount.setText(""+ --count);
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("number_");
                int positon = data.getIntExtra("position", -7);
                model.chanceNumber(positon, Integer.parseInt(result));
            }
        }
    }


//        CACH 3:
//    public void add(View view) {
//        int count = Integer.parseInt(tvCount.getText().toString());
//        tvCount.setText(""+ ++count);
//    }
//
//    public void remove(View view) {
//        int count = Integer.parseInt(tvCount.getText().toString());
//        tvCount.setText(""+ --count);
//    }

}