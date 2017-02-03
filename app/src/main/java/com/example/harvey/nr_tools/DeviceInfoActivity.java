package com.example.harvey.nr_tools;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeviceInfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    String[] list={"aid: ","分变率:","网络状态:","brand:","mcc:","mnc:","系统版本：","model:"," ","提示：\n长按信息可发送，长按这里可将全部信息发送"};
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
        //list[2]=list[2]+"点击查询";
       // list[3]=list[3]+getIMSI();
     //   list[4]=list[4]+getMCC();
       // list[5]=list[5]+getMNC();
        list[6]=list[6]+ Build.VERSION.RELEASE;
        list[7]=list[7]+ Build.MODEL;
        list[8]=list[8]+ Build.FINGERPRINT;
        adp.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(this,"click",Toast.LENGTH_LONG).show();
        String msg=" ";
        switch (position){
            case 0:

               // msg=getAid();
                break;
            case 1:
               // msg=getWmsize();

                break;
            case 2:



                break;
        }
        //list[position]=list[position]+msg;
      //  adp.notifyDataSetChanged();
    }



    public String getWmsize(){



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        String s = "" + displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;


        return s;
    }

    public  String getAid(){
        String androidId=" ";
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return  androidId;


    }

    public String getIMSI(){

        String imsi=" ";
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        imsi=telManager.getSubscriberId();

        return imsi;
    }

    public String getMCC(){

        return  getIMSI().substring(0,3);
    }


    public String getMNC(){
        return  getIMSI().substring(3,5);
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
                send(list[0]);
                break;
            case 1:
                send(list[1]);
                break;
            case 2:

                send(list[2]);
                break;
            case 3:
                send(list[3]);
                break;
            case 4:
                send(list[4]);
                break;
            case 5:
                send(list[5]);
                break;
            case 6:
                send(list[6]);
                break;
            case 7:
                send(list[7]);
                break;
            case 8:
                send(list[8]);
                break;
            case 9:
                send(all);
                break;
        }
        return false;
    }

    public void send(String s){

            Intent it=new Intent(Intent.ACTION_SEND);
            it.setType("text/plain");

            it.putExtra(Intent.EXTRA_SUBJECT, "手机信息");
            it.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(it);


    }
}
