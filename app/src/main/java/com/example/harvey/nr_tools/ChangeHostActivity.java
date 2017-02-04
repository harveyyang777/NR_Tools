package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ChangeHostActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,View.OnLongClickListener,View.OnClickListener{

    TextView txv;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_host);
        txv =(TextView) findViewById(R.id.textView2);

        rg=(RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);

        txv.setOnLongClickListener(this);
        txv.setOnClickListener(this);

        showHost();
    }


    public void changeToTest(){

        txv.setText("170");

    }

    public void changeToPTest(){
        txv.setText("152");

    }

    public void changeToPro(){
        txv.setText("pro");

    }

    public void showHost(){
        txv.setText(" pro");

    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.radioButton:
                changeToTest();
                break;
            case R.id.radioButton2:
                changeToPTest();
                break;
            case R.id.radioButton3:
                changeToPro();
                break;

        }

    }

    @Override
    public boolean onLongClick(View v) {

       // txv.setText("longclick");
        Utils.InfoUtils.sendHost(this);

        return false;
    }

    @Override
    public void onClick(View v) {
        //txv.setText("click");
        Utils.InfoUtils.viewHost(this);

    }
}
