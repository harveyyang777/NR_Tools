package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DeviceInfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"click",Toast.LENGTH_LONG).show();

    }

    public static synchronized  String runCmd(String[] cmd, String workdirectory) throws IOException{

        StringBuffer result=new StringBuffer();
        try {
            // 创建操作系统进程（也可以由Runtime.exec()启动）
            ProcessBuilder builder = new ProcessBuilder(cmd);
            InputStream in=null;

            if (workdirectory != null) {

                // 设置工作目录（同上）

                builder.directory(new File(workdirectory));

                // 合并标准错误和标准输出

                builder.redirectErrorStream(true);

                // 启动一个新进程

                Process process = builder.start();



                // 读取进程标准输出流

                in = process.getInputStream();

                byte[] re = new byte[1024];

                while (in.read(re) != -1) {

                    result = result.append(new String(re));

                }

            }

            // 关闭输入流

            if (in != null) {

                in.close();

            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  result.toString();
    }
}
