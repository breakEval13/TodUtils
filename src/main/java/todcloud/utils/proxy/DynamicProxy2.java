package todcloud.utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangjianxin on 2017/8/9.
 */
public class DynamicProxy2 implements InvocationHandler {
    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }
    private Object target;

    public DynamicProxy2(Object target){
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
        DynamicProxy2 dynamicProxy = new DynamicProxy2(new HumenImpl());
        Humen HumenProxy = dynamicProxy.getProxy();

        HumenProxy.eat("rice");
    }
}