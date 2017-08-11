package todcloud.utils.tools;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2016-9-17 下午5:48:16

 * @Description ：Please describe this document

 */
public class SK_Boolean {

    public static boolean toBool(int i) {
        return i == 1;
    }

    public static boolean toBool(String str) {
        // if (str == null || "".equals(str)) {

        // return false;

        // }

        // String s = str.toLowerCase();

        // if (s == null) {

        // return false;

        // }

        // return s.equals(str);

        return Boolean.valueOf(str);
    }
}