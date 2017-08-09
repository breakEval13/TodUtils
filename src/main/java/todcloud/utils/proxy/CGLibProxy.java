package todcloud.utils.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangjianxin on 2017/8/9.
 */
public class CGLibProxy implements MethodInterceptor {
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable{
        before();
        Object result = proxy.invokeSuper(obj,args);
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
        CGLibProxy cgLibProxy = new CGLibProxy();
        Humen humenProxy = cgLibProxy.getProxy(HumenImpl.class);
        humenProxy.eat("rice");
    }
}