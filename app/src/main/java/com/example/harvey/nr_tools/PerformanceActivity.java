package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import Utils.ShellUtils;

public class PerformanceActivity extends AppCompatActivity {
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        txv=(TextView)findViewById(R.id.textView);

    }

    public void mem(View v){
        //Toast.makeText(this,"11",Toast.LENGTH_SHORT).show();

        String cmd="cat /proc/meminfo ";
        String s1=" ";
        String s2=" ";
        s1=String.valueOf(ShellUtils.execCommand(cmd,true).errorMsg);
        s2=String.valueOf(ShellUtils.execCommand(cmd,true).successMsg);
        txv.setText(s2);

    }

    public void cpu(View v){

        String cmd="cat /proc/cpuinfo ";
        String s1=" ";
        String s2=" ";
        s1=String.valueOf(ShellUtils.execCommand(cmd,true).errorMsg);
        s2=String.valueOf(ShellUtils.execCommand(cmd,true).successMsg);
        txv.setText(s2);

    }



}
