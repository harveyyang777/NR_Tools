package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import Utils.HostFileUtils;
import Utils.InfoUtils;

import static Utils.ShellUtils.execCommand;

public class ChangeHostActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,View.OnLongClickListener,View.OnClickListener{

    TextView txv;
    TextView txvNew;
    TextView hostsPath;
    TextView newPath;
    RadioGroup rg;

    public static String LOG_TAG="hostTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_host);
        txv =(TextView) findViewById(R.id.textView2);
        hostsPath=(TextView)findViewById(R.id.txvPath);
        newPath=(TextView)findViewById(R.id.newPath);
        hostsPath.setOnLongClickListener(this);
        newPath.setOnLongClickListener(this);

        rg=(RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);


        txv.setOnClickListener(this);

        txv.setText(InfoUtils.viewHost(this));

        txvNew=(TextView)findViewById(R.id.newcontent);

    }


    public void changeToTest(){


       // Toast.makeText(this,"",Toast.LENGTH_LONG).show();

        HostFileUtils.creatHostFile(this,1);

        Toast.makeText(this,"已生成新hosts文件，请手动替换来修改手机hosts配置",Toast.LENGTH_LONG);
    }

    public void changeToPTest(){

        HostFileUtils.creatHostFile(this,2);
        Toast.makeText(this,"已生成新hosts文件，请手动替换来修改手机hosts配置",Toast.LENGTH_LONG);
    }

    public void changeToPro(){
        HostFileUtils.creatHostFile(this,0);
        Toast.makeText(this,"已生成新hosts文件，请手动替换来修改手机hosts配置",Toast.LENGTH_LONG);
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
        if (HostFileUtils.isExternalStorageWritable()){
            txvNew.setText(InfoUtils.viewFile(this, Environment.getExternalStorageDirectory().getPath().toString()+"/hosts"));
            Log.e(LOG_TAG,"读新host文件："+Environment.getExternalStorageDirectory().getPath().toString()+"/hosts");
        }

    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId()){
            case R.id.txvPath:
                InfoUtils.sendHost(this);
                break;
            case R.id.newPath:
                InfoUtils.sendFile(this,Environment.getExternalStorageDirectory().getPath().toString()+"/hosts");
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        //txv.setText("click");
       String msg= execCommand("cat /etc/hosts",false).successMsg;
        txv.setText(msg);

    }
}
