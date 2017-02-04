package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SitesActivity extends AppCompatActivity {

    String[] list={"崩溃平台 ","魔方正式地址:","魔方测试地址:","bugfree:","蒲公英取包：","内网取包地址","hudson"};
    ArrayAdapter<String> adp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        adp =new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        ListView lv=(ListView)findViewById(R.id.lvs);
        lv.setAdapter(adp);
    }
}
