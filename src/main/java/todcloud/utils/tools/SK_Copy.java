package todcloud.utils.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2016-9-18 上午10:29:45

 * @Description ：克隆对象(深克隆) *该对象必须序列化(包括子对象)

 */
public class SK_Copy {
    @SuppressWarnings("unchecked")
    /**

     * 4,深层复制<br>

     * ObjectInputStream 对以前使用 ObjectOutputStream 写入的基本数据和对象进行反序列化<br>

     * ObjectOutputStream 和 ObjectInputStream 分别与<br>

     * FileOutputStream 和FileInputStream <br>

     * 一起使用时，可以为应用程序提供对对象图形的持久存储。<br>

     * ObjectInputStream用于恢复那些以前序列化的对象。是一个反序列化的过程.<br>

     * 其他用途包括使用套接字流在主机之间传递对象，或者用于编组和解组远程通信系统中的实参和形参。<br>

     * ObjectInputStream 确保从流创建的图形中所有对象的类型与 Java 虚拟机中显示的类相匹配。<br>

     * 使用标准机制按需加载类。<br>

     * 只有支持 java.io.Serializable 或 java.io.Externalizable 接口的对象才能从流读取。

     */
    // ByteArrayOutputStream,ObjectOutputStream深度复制,读一个对象,写一个对象.

    public static <T> T deepCopy(Object obj) {
        try {
            // 利用内存的转换数组字节进行序列化和反序列化

            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            // 序列化

            ObjectOutputStream out = new ObjectOutputStream(buf);
            out.writeObject(obj);// 对象转换为对象流在内存中转化缓冲的输出流

            out.close();// 关闭

            byte[] data = buf.toByteArray();// 将对象在内存中的内部缓冲区字节转化为数组

            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(data));// 内存中的内部缓冲区字节数组字节读取

            Object copy = in.readObject();// 读取的对象流转换为对象,反序列化过程

            in.close();
            return (T) copy;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
