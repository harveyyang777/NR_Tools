package Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by hanxiaochen on 17/2/5.
 */

public class HostFileUtils {

    public static String LOG_TAG="hostTag";

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /*创建hostfile,0代表无host配置，1代表170环境，2代表152环境*/
    public static void creatHostFile(Context context,int type){

        if (isExternalStorageWritable()){

            Log.e(LOG_TAG,"调用creatHostFile方法，且获取到可写权限");

         //  File file= getAlbumStorageDir("host");//在download下创建文件夹和文件
            File file=createSDCardDir("hosts");
            writeHosts(file,type);//写文件内容


        }else if(isExternalStorageReadable()){

            Toast.makeText(context,"无写文件权限",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(context,"无权限做此操作",Toast.LENGTH_LONG).show();
        }
    }

    /*在公共download目录中创建了一个文件夹并创建host文件*/
    public static File getAlbumStorageDir(String fileName) {

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), fileName);

        if (!file.exists()) {
            try {
                //按照指定的路径创建文件夹
                file.mkdirs();
                Log.e(LOG_TAG,"mkdir");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        File dir = new File(file.getPath().toString()+File.separator+"hosts");

        if (dir.exists())
            dir.delete();

        try {
            //在指定的文件夹中创建文件
            dir.createNewFile();
        } catch (Exception e) {
        }


        if (!dir.exists()) {
            Log.e(LOG_TAG, "host file not created");
        }


        return dir;



    }

    /*在SD卡上创建一个文件夹*/
    public static File createSDCardDir(String fileName){

        Log.e(LOG_TAG,"已调用创建sdcard中文件的方法");
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.e(LOG_TAG,"已获取到sdcard中创建文件的权限");

            String sdcardPath=Environment.getExternalStorageDirectory().getPath().toString();//获取sdcard目录地址

            File hostFile=new File(sdcardPath+File.separator+fileName);

            if (hostFile.exists())
                hostFile.delete();

            try {
                //创建文件
                hostFile.createNewFile();
            } catch (Exception e) {
            }

            Log.e(LOG_TAG,"已在sdcard中创建文件："+hostFile.getAbsolutePath());

            return hostFile;

        }else {
            Log.e(LOG_TAG,"未获取到sdcard中创建文件的权限");
            return null;
        }
    }


    public static void writeHosts(File file,int type){
        FileWriter fw = null;
        BufferedWriter bw = null;
        String datetime = "";
        try {

            fw = new FileWriter(file, true);//
            // 创建FileWriter对象，用来写入字符流
            bw = new BufferedWriter(fw); // 将缓冲对文件的输出
            String [] test={"127.0.0.1       localhost","::1             ip6-localhost","119.147.146.170 ws.ksmobile.net","119.147.146.170 ups.ksmobile.net","119.147.146.170 ws.ksmobile.net"};
            String [] testp={"127.0.0.1       localhost","::1             ip6-localhost","119.147.146.152 ws.ksmobile.net","119.147.146.152 ups.ksmobile.net","119.147.146.152 ws.ksmobile.net"};
            String [] product={"127.0.0.1       localhost","::1             ip6-localhost"};
            String[]  current=null;


            switch (type){
                case 0:
                    current=product;
                    break;
                case 1:
                    current=test;
                    break;
                case 2:
                    current=testp;
                    break;
                default:
                    current=product;
                    break;
            }


            for (String s:current){
                bw.write(s + "\n"); // 写入文件

            }
            bw.newLine();
            bw.flush(); // 刷新该流的缓冲
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                bw.close();
                fw.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
            }
        }


    }

    //将文件放人指定文件夹
    public static void copyFile(String fromPath,String desPath){
        Log.e(LOG_TAG,"copyFile method:"+"fronPath"+fromPath+"desPath"+desPath);
        String cmd="cp -rp";
        cmd=cmd+" "+fromPath+" "+desPath;
        Log.e(LOG_TAG,cmd);
        ShellUtils.execCommand(cmd,true);
    }








}
