package com.example.harvey.nr_tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String[] functions={"1.手机信息","2.性能信息（需root权限）","3.切换host（需root权限）","4.发送图片","5.发送ANR","6.修改aid（需root权限）","7.运营商信息","8.常用地址","点击查看是否已获取root权限"};

    ArrayAdapter<String> adpFunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adpFunc=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,functions);
        ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adpFunc);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        switch (position){
            case 0:
                deviceInfo();
            break;
            case 1:
                performanceInfo();
                break;
            case 2:
                changeHost();
                break;
            case 4:
                Utils.InfoUtils.sendAnr(this);
                break;
            case 6:
                IMSIInfo();
                break;
            case 7:
                site();
                break;
            case 8:
                String sRoot=Utils.InfoUtils.checkRoot();
                functions[7]="root权限："+sRoot;
                adpFunc.notifyDataSetChanged();

        }
    }

    public void deviceInfo(){
      //  Toast.makeText(this,"0",Toast.LENGTH_LONG).show();
        Intent it=new Intent();
        it.setClass(this,DeviceInfoActivity.class);
        startActivity(it);
    }

    public void  performanceInfo(){
        Intent it=new Intent();
        it.setClass(this,PerformanceActivity.class);
        startActivity(it);

    }

    public void IMSIInfo(){
        Intent it=new Intent();
        it.setClass(this,IMSIActivity.class);
        startActivity(it);

    }

    public void changeHost(){

        Intent it=new Intent();
        it.setClass(this,ChangeHostActivity.class);
        startActivity(it);

    }

    public void site(){

        Intent it=new Intent();
        it.setClass(this,SitesActivity.class);
        startActivity(it);

    }

}
