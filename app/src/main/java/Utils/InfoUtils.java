package Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

public class InfoUtils {

    public static final String crash= "http://trace-abord.cm.ijinshan.com/traceRate/";
    public  static final String mp="http://mp.cm.ksmobile.com/CloudMsgAdv?pkg=instanews&func_type=2&config_type=";
    public static final String mpt="http://test.mp.cm.ksmobile.com/CloudMsgAdv?pkg=instanews&func_type=2&config_type=INSTANEWS#";
    public static final String bugfree="http://bug.oa.ijinshan.com/bugfree/bug/list/50?productmodule_id=1003";
    public static final String pgy="https://www.pgyer.com/lnNR";
    public static final String path="http://10.60.80.70/cmnewsindia/";
    public static final String hudson="http://scm.bj.ijinshan.com/hudson/";


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
        context.startActivity(Intent.createChooser(it,"将手机信息发送到..."));
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


    public static void sendHost(Context context){

        Intent it=new Intent(Intent.ACTION_SEND);
     //   Intent it=new Intent(Intent.ACTION_VIEW);
        it.setType("*/*");
        String filePath="/etc/hosts";

        File file=new File(filePath);
      //  Toast.makeText(context,file.getPath().toString(),Toast.LENGTH_LONG).show();
        it.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        // it.putExtra(Intent.EXTRA_STREAM,uri);

        it.putExtra(Intent.EXTRA_SUBJECT, "hosts");
        context.startActivity(Intent.createChooser(it,"发送hosts文件到..."));

    }

    public static void sendFile(Context context,String Path){

        Intent it=new Intent(Intent.ACTION_SEND);
        it.setType("*/*");
        File file=new File(path);

        it.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        it.putExtra(Intent.EXTRA_SUBJECT, "文件");
        context.startActivity(Intent.createChooser(it,"发送文件到..."));

    }

    public static String viewHost(Context context){

        String msg= ShellUtils.execCommand("cat /etc/hosts",false).successMsg;
        return msg;
    }

    public static String viewFile(Context context,String path){

        String msg= ShellUtils.execCommand("cat "+path,false).successMsg;
        return msg;
    }

    public static void installApk(Context context){


        Uri uri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_VIEW,uri);

       context.startActivity(intent);


    }

    public static void crashPath(Context context){


        Uri uri = Uri.parse(crash);

        Intent intent = new Intent(Intent.ACTION_VIEW,uri);

        context.startActivity(intent);

    }

    public static void pgy(Context context){

        Uri uri = Uri.parse(pgy);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }

    public static void mpPath(Context context){

        Uri uri = Uri.parse(mp);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }



    public static void mpTestPath(Context context){

        Uri uri = Uri.parse(mpt);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }

    public static void bugfreePath(Context context){

        Uri uri = Uri.parse(bugfree);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }

    public static void hudsonPath(Context context){

        Uri uri = Uri.parse(hudson);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }

    public static void pkgPath(Context context){

        Uri uri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }


    public static void openSettings(Context context){
        // Intent intent = new Intent(Settings.ACTION_SETTINGS);

        Intent intent = new Intent();
       // ComponentName cm = new ComponentName(android.provider.Settings, Settings.ACTION_DEVICE_INFO_SETTINGS);
      //  intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        context.startActivity(intent);
    }










}
