package todcloud.utils.io;

/**
 * Created by zhangjianxin on 2017/8/11.
 */
import com.bowlong.bio2.B2InputStream;
import com.bowlong.bio2.B2OutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class Test {
    public static void main(String[] args) throws Exception {
         long t1 = System.currentTimeMillis();

         for (int i = 0; i < 1000; i++) {

         sk();

         }

         long t2 = System.currentTimeMillis();

         System.out.println("----------------------------");

         long t3 = System.currentTimeMillis();

         for (int i = 0; i < 1000; i++) {

         b2();

         }

         long t4 = System.currentTimeMillis();

         System.out.println("sk time = " + (t2 - t1));

         System.out.println("b2 time = " + (t4 - t3));

        fileTest();
    }

    public static void sk() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SK_OutputStream.writeByte(os, (byte) 1);
        SK_OutputStream.writeShort(os, (short) 10);
        SK_OutputStream.writeInt(os, 99);
        // SK_OutputStream.writeFloat(os, 0.5559f);

        SK_OutputStream.writeDouble(os, 9.1212);
        SK_OutputStream.writeString(os,
                "ffffffsafdsafsdgggggggggggggggggggggaaaaaaaaaaaa");
        Map map = new HashMap();
        map.put("dengying", 1);
        map.put("sk", "zhangjianxin ");
        map.put(0.56, "sophie");
        SK_OutputStream.writeMap(os, map);
        SK_OutputStream.writeDate(os, new Date());
        SK_OutputStream.writeBoolean(os, false);
        // System.out.println("sk = " + os.toByteArray().length);

        InputStream in = new ByteArrayInputStream(os.toByteArray());
        byte i1 = SK_InputStream.readByte(in, null);
        short i2 = SK_InputStream.readShort(in, null);
        int i3 = SK_InputStream.readInt(in, null);
        // float i4 = SK_InputStream.readFloat(in, null);

        double i5 = SK_InputStream.readDouble(in, null);
        String i6 = SK_InputStream.readString(in, null);
        // System.out.println(i1);

        // System.out.println(i2);

        // System.out.println(i3);

        // System.out.println(i4);

        // System.out.println(i5);

        // System.out.println(i6);

        // System.out.println(SK_InputStream.readMap(in, null));

        // System.out.println(SK_InputStream.readDate(in, null));

        // System.out.println(SK_InputStream.readBoolean(in, null));

    }

    public static void b2() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        B2OutputStream.writeByte(os, (byte) 1);
        B2OutputStream.writeShort(os, (short) 10);
        B2OutputStream.writeInt(os, 99);
        // B2OutputStream.writeFloat(os, 0.5559f);

        B2OutputStream.writeDouble(os, 9.1212);
        B2OutputStream.writeString(os,
                "ffffffsafdsafsdgggggggggggggggggggggaaaaaaaaaaaa");
        Map map = new HashMap();
        map.put("dengying", 1);
        map.put("sk", "zhangjianxin ");
        map.put(0.56, "sophie");
        B2OutputStream.writeMap(os, map);
        B2OutputStream.writeDate(os, new Date());
        B2OutputStream.writeBoolean(os, false);
        // System.out.println("b2 = " + os.toByteArray().length);

        InputStream in = new ByteArrayInputStream(os.toByteArray());
        byte i1 = (byte) B2InputStream.readObject(in);
        short i2 = (short) B2InputStream.readObject(in);
        int i3 = (int) B2InputStream.readObject(in);
        // float i4 = B2InputStream.readFloat(in, null);

        double i5 = (double) B2InputStream.readObject(in);
        String i6 = (String) B2InputStream.readObject(in);
        // System.out.println(i1);

        // System.out.println(i2);

        // System.out.println(i3);

        // System.out.println(i4);

        // System.out.println(i5);

        // System.out.println(i6);

        // System.out.println(B2InputStream.readObject(in));

        // System.out.println(B2InputStream.readObject(in));

        // System.out.println(B2InputStream.readObject(in));

    }

    public static void fileTest() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Map map = new HashMap<>();
        map.put(1, 2);
        map.put("a", "b");
        SK_OutputStream.writeObject(os, map);


        InputStream in = new ByteArrayInputStream(os.toByteArray());
        System.out.println(SK_InputStream.readObject(in).toString());
    }
}