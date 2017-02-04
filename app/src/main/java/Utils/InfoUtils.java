package Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

public class InfoUtils {

    public  static  String getAid(){
        String androidId=" ";
      //  androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return  androidId;
    }

    public static  String getNetInfo(Context c){
        int status;
        String msg=" ";
        status=Utils.NetworkInfoUtils.getAPNType(c);
        switch (status){
            case 0:
                msg="没有网络";
                break;
            case 1:
                msg="wifi网络";
                break;
            case 4:
                msg="4G网络";
                break;
            case 3:
                msg="3G网络";
                break;
            case 2:
                msg="2G网络";
                break;
        }

        return msg;
    }


    public static String getIMSI(){

        String imsi=" ";
        //TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
       // imsi=telManager.getSubscriberId();
        return imsi;
    }

    public static String getMCC(){

        return  getIMSI().substring(0,3);
    }


    public static String getMNC(){
        return  getIMSI().substring(3,5);
    }

    public static String checkRoot(){

        String msg=" ";
        if (ShellUtils.checkRootPermission()){
            msg="已获取root权限";
        }else{
            msg="未获取root权限";
        }
        return msg;
    }

    public static void send(Context context,String s){
        Intent it=new Intent(Intent.ACTION_SEND);
        it.setType("text/plain");

        it.putExtra(Intent.EXTRA_SUBJECT, "手机信息");
        it.putExtra(Intent.EXTRA_TEXT, s);
        context.startActivity(Intent.createChooser(it,"deviceInfo"));
    }

    public static void sendAnr(Context context){

        Intent it=new Intent(Intent.ACTION_SEND);
        it.setType("*/*");
        String filePath="/data/anr/traces.txt";

        //String filePath= Environment.getExternalStorageDirectory().getPath()+File.separator+str;

      //  String filePath=Environment.getRootDirectory().getPath()+File.separator+"data/anr/traces.txt";

        File file=new File(filePath);

       // Uri uri=Uri.parse("file://"+filePath);

        Toast.makeText(context,file.getPath().toString(),Toast.LENGTH_LONG).show();
        it.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
       // it.putExtra(Intent.EXTRA_STREAM,uri);

        it.putExtra(Intent.EXTRA_SUBJECT, "anr");
        context.startActivity(Intent.createChooser(it,"发送anr到..."));


    }

}
