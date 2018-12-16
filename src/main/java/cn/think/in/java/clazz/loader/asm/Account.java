package cn.think.in.java.clazz.loader.asm;

import java.util.Date;

/**
 * Created by chunchengpeng on 2018/12/15.
 */
public class Account {
    public void operation() {
        System.out.println("Account->operation("+new Date()+")(new)");
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}