package com.example.briankenjiaguilar.timestableapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SeekBar numberSlider;
    private ListView timesTable;
    private ArrayList<Integer> numbers;
    private ArrayAdapter<Integer> numbersList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNumbersList();
        numbersList = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,numbers);

        timesTable = (ListView) findViewById(R.id.timesTableView);
        timesTable.setAdapter(numbersList);


        numberSlider = (SeekBar) findViewById(R.id.numberSlider);
        numberSlider.setMax(19);

        numberSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                for(int j=0;j< numbersList.getCount();++j){
                    numbers.set(j,(j+1)*(i+1));
                    numbersList.notifyDataSetChanged();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void createNumbersList(){
        numbers = new ArrayList<Integer>();
        for(int i=1;i<=9;++i){numbers.add(i);}
    }
}
