package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.R.attr.y;

public class SitesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String[] list={"蒲公英取包","魔方正式地址","魔方测试地址","bugfree:","崩溃平台 ","内网取包地址","hudson"};
    ArrayAdapter<String> adp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        adp =new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        ListView lv=(ListView)findViewById(R.id.lvs);
        lv.setAdapter(adp);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Utils.InfoUtils.pgy(this);
                break;
            case 1:
                Utils.InfoUtils.mpPath(this);
                break;
            case 2:
                Utils.InfoUtils.mpTestPath(this);
                break;
            case 3:
                Utils.InfoUtils.bugfreePath(this);
                break;
            case 4:
                Utils.InfoUtils.crashPath(this);
                break;
            case 5:
                Utils.InfoUtils.pkgPath(this);
                break;
           case 6:
                Utils.InfoUtils.hudsonPath(this);
               break;
        }
    }
}
