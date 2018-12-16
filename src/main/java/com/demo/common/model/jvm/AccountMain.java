package com.demo.common.model.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by chunchengpeng on 2018/12/15.
 */
public class AccountMain {
    public static void main(String[] args)
            throws ClassNotFoundException, InterruptedException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {

        while (true) {
            ClassLoader loader = new ClassLoader() {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                    try {
                        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                        InputStream is = getClass().getResourceAsStream(fileName);
                        System.out.println(new Date());
                        if (is == null) {
                            return super.loadClass(name);
                        }

                        byte[] b = new byte[is.available()];

                        is.read(b);

                        return defineClass(name, b, 0, b.length);

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ClassNotFoundException(name);
                    }
                }
            };

            Class clazz = loader.loadClass("cn.think.in.java.clazz.loader.asm.Account");
            Object account = clazz.newInstance();
            account.getClass().getMethod("operation", new Class[]{}).invoke(account);
            Thread.sleep(20000);
        }
    }
}
