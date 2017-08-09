package todcloud.utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangjianxin on 2017/8/9.
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    private void before(){
        System.out.println("cook");
    }

    private void after(){
        System.out.println("swap");
    }

    public static void main(String[] args){
        Humen humen = new HumenImpl();

        DynamicProxy dynamicProxy = new  DynamicProxy(humen);

        Humen HumenProxy = (Humen) Proxy.newProxyInstance(
                humen.getClass().getClassLoader(),
                humen.getClass().getInterfaces(),
                dynamicProxy
        );

        HumenProxy.eat("rice");
    }
}
