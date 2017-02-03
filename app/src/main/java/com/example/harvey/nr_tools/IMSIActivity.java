package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IMSIActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    String[] list={"imsi:","mcc:","mnc:"};
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
        return false;
    }

    public void init(){
        list[0]=list[0]+Utils.InfoUtils.getIMSI();
        list[1]=list[1]+Utils.InfoUtils.getMCC();
        list[2]=list[2]+ Utils.InfoUtils.getMNC();
        adp.notifyDataSetChanged();
    }
}
