package todcloud.utils.proxy;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhangjianxin on 2017/8/9.
 */
public class FlxProxy {


    /**得到某个对象的属性*/
    public Object getProperty(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();

        Field field = ownerClass.getField(fieldName);

        Object property = field.get(owner);

        return property;
    }

    /**得到某个类的静态属性*/
    public Object getStaticProperty(String className, String fieldName)
            throws Exception {
        Class ownerClass = Class.forName(className);

        Field field = ownerClass.getField(fieldName);

        Object property = field.get(ownerClass);

        return property;
    }

    /**执行某对象的方法*/
    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {

        Class ownerClass = owner.getClass();

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(owner, args);
    }
    /**执行某个类的静态方法*/
    public Object invokeStaticMethod(String className, String methodName,
                                     Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(null, args);
    }
    /** 新建实例*/
    public Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor cons = newoneClass.getConstructor(argsClass);

        return cons.newInstance(args);

    }

    public boolean isInstance(Object obj, Class cls) {
            return cls.isInstance(obj);
         }

    public Object getByArray(Object array, int index) {
        return Array.get(array,index);
    }

    public static void main(String[] args) {
        FlxProxy flxProxy = new FlxProxy();

        try {
            System.out.println(flxProxy.getProperty(new staticData(),"NUM"));
            System.out.println(flxProxy.getStaticProperty("todcloud.utils.proxy.staticData","NUM"));
            System.out.println(flxProxy.invokeMethod(new staticData(),"v2",new Class[]{}));
            System.out.println(flxProxy.invokeStaticMethod("todcloud.utils.proxy.staticData","v1",new Class[0]));
            Object o = flxProxy.newInstance("todcloud.utils.proxy.staticData",new Class[]{});
            staticData a = (staticData)o;
            a.v2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
