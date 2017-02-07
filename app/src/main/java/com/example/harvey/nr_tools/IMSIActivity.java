package com.example.harvey.nr_tools;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class IMSIActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    String[] list={"imsi:","mcc:","mnc:","提示：\n长按发送"};
    ArrayAdapter<String> adp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imsi);

        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adp);
        lv.setOnItemLongClickListener(this);
        init();
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
                Utils.InfoUtils.send(this,all);
                break;
        }
        return false;
    }

    public void init(){

        if(getIMSI().length()!=0){
            list[0]=list[0]+getIMSI();
            list[1]=list[1]+getMCC();
            list[2]=list[2]+ getMNC();
            adp.notifyDataSetChanged();

        }else {
            Toast.makeText(this,"未插卡",Toast.LENGTH_SHORT).show();
        }
    }


    public  String getIMSI(){

        String imsi=" ";

        TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);


        imsi=telManager.getSubscriberId();



        if(imsi.length()!=0){
            return imsi;
        }else {
            return "";
        }
    }

    public String getMCC(){

        return  getIMSI().substring(0,3);
    }


    public String getMNC(){
        return  getIMSI().substring(3,5);
    }
}
