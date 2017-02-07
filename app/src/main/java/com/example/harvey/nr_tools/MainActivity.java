package com.example.harvey.nr_tools;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String[] functions={"1.手机信息","2.性能信息（需root权限）","3.生成host文件","4.发送一条通知","5.发送ANR","6.查看aid"};

    ArrayAdapter<String> adpFunc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adpFunc=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,functions);
        ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adpFunc);
        lv.setOnItemClickListener(this);

        requestPermission();//申请运行时权限

       // InfoUtils.checkRoot();


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
            case 3:
                sendaNotification();
                break;
            case 4:
                Utils.InfoUtils.sendAnr(this);
                break;
            case 5:
                changeAid();
                break;

            case 6:
                site();
                break;


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

    public void changeAid(){

        Intent it=new Intent();
        it.setClass(this,ChangeAidActivity.class);
        startActivity(it);

    }

    public void requestPermission( )
    {




        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},10001);
        } else
        {

        }

        /*
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},10002);
        } else
        {

        }
        */
    }

    public void sendaNotification(){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);


        //1.获取系统通知的管理者
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //2.用notification工厂 创建一个notification
        Notification noti = new Notification.Builder(this)
                .setContentTitle("一条系统通知")
                .setContentText("nr测试")
                .setSmallIcon(R.drawable.icon1)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                .setContentIntent(contentIntent)
                .build();
        //3.把notification显示出来
        nm.notify(1, noti);

    }

}
