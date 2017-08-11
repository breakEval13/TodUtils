package todcloud.utils.tools;

import java.util.ArrayList;
import java.util.List;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2016-9-5 下午12:06:28

 * @Description ：字符串工具类

 */
public class SK_String {

    /**

     * 字符串是否为空

     *

     * @param str

     * @return

     */
    public static final boolean isEmpty(final String str) {
        if (str == null || str.trim().length() < 1) {
            return true;
        }
        return false;
    }

    /**

     * 字符串转Int数组

     *

     * @param str

     * @param regex

     * @return

     */
    public static final int[] StringToIntArray(final String str, String regex) {
        regex = ".".equals(regex) ? "//." : regex;
        String[] stringArray = str.split(regex);
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i].trim());
        }
        return intArray;
    }

    /**

     * 字符串转Int集合

     *

     * @param str

     * @param regex

     * @return

     */
    public static final List<Integer> StringToIntList(final String str,
                                                      String regex) {
        regex = ".".equals(regex) ? "//." : regex;
        String[] stringArray = str.split(regex);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < stringArray.length; i++) {
            list.add(Integer.parseInt(stringArray[i].trim()));
        }
        return list;
    }

    /**

     * 首字符转大写

     *

     * @param s

     * @return

     */
    public static final String upperFirst(final String s) {
        int len = s.length();
        if (len <= 0)
            return "";
        StringBuffer sb = new StringBuffer();
        sb.append(s.substring(0, 1).toUpperCase());
        sb.append(s.substring(1, len));
        return sb.toString();
    }

    /**

     * 首字符转小写

     *

     * @param s

     * @return

     */
    public static final String lowerFirst(final String s) {
        int len = s.length();
        if (len <= 0)
            return "";

        StringBuffer sb = new StringBuffer();
        sb.append(s.substring(0, 1).toLowerCase());
        sb.append(s.substring(1, len));
        return sb.toString();
    }

    /**

     * 全角转半角

     *

     * @param input

     * @return

     */
    public static String QtoB(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**

     * 半角转全角

     *

     * @param input

     * @return

     */
    public static String BtoQ(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }
}