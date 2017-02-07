package com.example.harvey.nr_tools;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeviceInfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    String[] list={"aid:","分变率:","网络状态:","brand:","系统版本：","model:"," ","提示：\n长按信息可发送，长按这里可将全部信息发送"};
    ArrayAdapter<String> adp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);




        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);

        initList();
    }

    public void initList(){

        list[0]=list[0]+getAid();
        list[1]=list[1]+getWmsize();
        list[2]=list[2]+Utils.InfoUtils.getNetInfo(this);
        list[3]=list[3]+Build.BRAND;
        list[4]=list[4]+ Build.VERSION.RELEASE;
        list[5]=list[5]+ Build.MODEL;
        list[6]=list[6]+ Build.FINGERPRINT;
        adp.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){


            case 2:
                list[2]="网络状态："+Utils.InfoUtils.getNetInfo(this);
                adp.notifyDataSetChanged();
                break;
        }
    }

    public  String getAid(){
        String androidId=" ";
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return  androidId;
    }

    public String getWmsize(){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        String s = "" + displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        return s;
    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String all=" ";
        for (String e :list){
           if (e.contains("提示"))
               continue;
            else
               all+=e+"\n";

        }
        switch (position){
            case 0:
                Utils.InfoUtils.send(this,list[0]);
                break;
            case 1:
                Utils.InfoUtils.send(this,list[1]);
                break;
            case 2:

                Utils.InfoUtils.send(this,list[2]);
                break;
            case 3:
                Utils.InfoUtils.send(this,list[3]);
                break;
            case 4:
                Utils.InfoUtils.send(this,list[4]);
                break;
            case 5:
                Utils.InfoUtils.send(this,list[5]);
                break;
            case 6:
                Utils.InfoUtils.send(this,list[6]);
                break;
            case 7:
                Utils.InfoUtils.send(this,all);
                break;
        }
        return false;
    }

}
