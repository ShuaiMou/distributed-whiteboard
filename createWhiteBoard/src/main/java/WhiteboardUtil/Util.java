package WhiteboardUtil;

public class Util {
    public static boolean checkIp(String ip) {
        if ("localhost".equalsIgnoreCase(ip)) return true;

        if(ip.length()<7 || ip.length() >15) return false;

        String[] arr = ip.split("\\.");
        if (arr.length != 4) return false;
        int temp;
        for(String num : arr) {
            if(!checkIntNumber(num)) return false;
            temp = Integer.parseInt(num);
            if (temp < 0 || temp > 255) return false;
        }
        return true;
    }

    private static boolean checkIntNumber(String num) {
        char temp;
        for(int i = 0; i < num.length(); i++) {
            temp = num.charAt(i);
            if ( temp > '9' || temp < '0')
                return false;
        }
        return true;
    }



}
