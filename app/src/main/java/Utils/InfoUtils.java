package Utils;

public class InfoUtils {

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
}
