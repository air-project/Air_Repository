package com.spring.maven.test01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DecimalFormat;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.spring.maven.test01.proxy.CountService;
import com.spring.maven.test01.proxy.CountServiceImpl;
   
@SuppressWarnings("unused")   
public class DynamicProxyPerformanceTest {   
       
    public static void main(String[] args) throws Exception {   
        CountService delegate = new CountServiceImpl();   
           
        long time = System.currentTimeMillis();   
        CountService jdkProxy = createJdkDynamicProxy(delegate);   
        time = System.currentTimeMillis() - time;   
        System.out.println("Create JDK Proxy: " + time + " ms");   
           
        time = System.currentTimeMillis();   
        CountService cglibProxy = createCglibDynamicProxy(delegate);   
        time = System.currentTimeMillis() - time;   
        System.out.println("Create CGLIB Proxy: " + time + " ms");   
           
        for (int i = 0; i < 3; i++) {   
            test(jdkProxy, "Run JDK Proxy: ");   
            test(cglibProxy, "Run CGLIB Proxy: ");   
            System.out.println("-------------------");   
        }   
    }   
       
    private static void test(CountService service, String label) throws Exception {   
        service.count(); // warm up   
        int count = 10000000;   
        long time = System.currentTimeMillis();   
        for (int i = 0; i < count; i++) {   
            service.count();   
        }   
        time = System.currentTimeMillis() - time;   
        System.out.println(label + time + " ms, " + new DecimalFormat().format(count * 1000 / time) + " t/s");   
    }   
       
    private static CountService createJdkDynamicProxy(final CountService delegate) {   
        CountService jdkProxy = (CountService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),   
                new Class[] { CountService.class }, new JdkHandler(delegate));   
           
        // 反汇编字节码用，测试的时候注释掉这段代码，不然影响测试结果   
        // 下面一行代码参照java.lang.reflect.Proxy   
//        byte[] proxyClassFile =   
//                sun.misc.ProxyGenerator.generateProxyClass(   
//                        jdkProxy.getClass().getName(), jdkProxy.getClass().getInterfaces());   
//        try {   
//            FileOutputStream fos =   
//                    new FileOutputStream(new File(jdkProxy.getClass().getName() + ".class"));   
//            fos.write(proxyClassFile, 0, proxyClassFile.length);   
//        } catch (FileNotFoundException e) {   
//            e.printStackTrace();   
//        } catch (IOException e) {   
//            e.printStackTrace();   
//        }   
        return jdkProxy;   
    }   
       
    private static class JdkHandler implements InvocationHandler {   
           
        final Object delegate;   
           
        JdkHandler(Object delegate) {   
            this.delegate = delegate;   
        }   
           
        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {   
            return method.invoke(delegate, objects);   
        }   
    }   
       
    private static CountService createCglibDynamicProxy(final CountService delegate) throws Exception {   
        Enhancer enhancer = new Enhancer();   
        enhancer.setSuperclass(CountServiceImpl.class);   
        enhancer.setCallback(new MethodInterceptor() {   
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {   
                return proxy.invokeSuper(obj, args);   
            }   
        });   
        CountServiceImpl cglibProxy = (CountServiceImpl) enhancer.create();   
        // 反汇编字节码用，测试的时候注释掉这段代码，不然影响测试结果   
        // 下面一行代码参照net.sf.cglib.core.AbstractClassGenerator类中byte[] b = strategy.generate(this);   
//        byte[] proxyClassFile = new DefaultGeneratorStrategy().generate(enhancer);   
//        try {   
//            FileOutputStream fos =   
//                    new FileOutputStream(new File(cglibProxy.getClass().getName() + ".class"));   
//            fos.write(proxyClassFile, 0, proxyClassFile.length);   
//        } catch (FileNotFoundException e) {   
//            e.printStackTrace();   
//        } catch (IOException e) {   
//            e.printStackTrace();   
//        }   
        return cglibProxy;   
    }   
} 