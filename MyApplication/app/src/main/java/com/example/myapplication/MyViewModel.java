package com.example.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;
    private ArrayList arrayList;

    public LiveData<Integer> getNumber(){
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public ArrayList<Integer> getArrayList(){
        if(arrayList == null){
            arrayList = new ArrayList();
            arrayList.add(number.getValue());
        }
        return arrayList;
    }

    public void increaseNumber(){
        arrayList.add(number.getValue() + 1);
        number.setValue(number.getValue() + 1);
    }
    public void reduceNumber(){
        arrayList.add(number.getValue() - 1);
        number.setValue(number.getValue() - 1);
    }
    public void chanceNumber(int position, int newNumber){
        arrayList.remove(position);
        arrayList.add(position, newNumber);
        number.setValue(number.getValue());
    }
}
