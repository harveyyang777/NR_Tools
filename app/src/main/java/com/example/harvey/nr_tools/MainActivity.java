package com.example.harvey.nr_tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String[] functions={"1.查看手机信息","2.发送ANR"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adpFunc=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,functions);
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
            Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
            break;
        }
    }

    public void deviceInfo(){
      //  Toast.makeText(this,"0",Toast.LENGTH_LONG).show();
        Intent it=new Intent();
        it.setClass(this,DeviceInfoActivity.class);
        startActivity(it);
    }
}
