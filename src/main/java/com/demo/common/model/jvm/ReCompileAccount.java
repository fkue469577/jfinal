package com.demo.common.model.jvm;

import cn.think.in.java.clazz.loader.asm.Account;

/**
 * Created by chunchengpeng on 2018/12/15.
 */
public class ReCompileAccount {
    public static void main(String[] args) {
        new Account().operation();
    }
}
