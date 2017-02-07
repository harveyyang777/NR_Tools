package com.example.harvey.nr_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import Utils.ShellUtils;

public class ChangeAidActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String[] opt={"点击这里发送当前aid","点击这里修改aid（未实现)"};
    EditText edt;
    TextView txv;
    ArrayAdapter<String> adp;
    String query="sh:content query --uri content://settings/secure --where \"name='android_id'\"";
    String changeTo="content insert --uri content://settings/secure --bind name:s:android_id --bind value:s:1ede41a72c002573 ";

    String delete="delete --uri content://settings/secure --where \"name='android_id'\"";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_aid);

        txv=(TextView)findViewById(R.id.current);
        txv.setText(getAid());

        edt=(EditText)findViewById(R.id.editText);
        edt.setText(getAid());


        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,opt);
        ListView lv=(ListView)findViewById(R.id.aidlv);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(this);
    }

    public  String getAid(){
        String androidId=" ";
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return  androidId;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        /*
        switch (position){
            case 0:
                Toast.makeText(this,edt.getText().toString(),Toast.LENGTH_LONG).show();
                Utils.InfoUtils.send(this,edt.getText().toString());
                break;

        }
        */
        if(position==0){
           // Toast.makeText(this,edt.getText().toString(),Toast.LENGTH_LONG).show();
            Utils.InfoUtils.send(this,txv.getText().toString());

        }else if (position==1){
            //changeAid(edt.getText().toString());
            Toast.makeText(this,"to be continued...",Toast.LENGTH_SHORT).show();
        }

    }

    public void changeAid(String newId){

        if (newId.length()<1){
            Toast.makeText(this,"不能为空",Toast.LENGTH_LONG).show();



        }else{
         //   android.provider.Settings.Secure.putString(getContentResolver(),
          //          android.provider.Settings. Secure.ANDROID_ID,newId);

            String msgError= ShellUtils.execCommand(query,false).errorMsg;
            String msgSuccess=ShellUtils.execCommand(delete,false).successMsg;
            Toast.makeText(this,msgError,Toast.LENGTH_LONG).show();

        }


        /*
        Boolean rooted= ShellUtils.checkRootPermission();
        if (rooted){
            Toast.makeText(this,"rooted",Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(this,"未获取root权限，不能修改aid",Toast.LENGTH_LONG).show();
        }*/

    }
}
