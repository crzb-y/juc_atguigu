package com.alibaba;


import java.math.BigDecimal;
import java.util.Objects;

public class Oop {
    static Object object;

    static Object object2;
    static Integer integer = 127;

    static float a = 1.0F - 0.9F;
    static float b = 0.9F - 0.8F;
    static float diff = 1e-6F;

    static Float x = Float.valueOf(a);
    static Float y = Float.valueOf(b);

    public static void main(String[] args) {
        "test".equals(object);//正例
        object.equals("test");//反例

        Objects.equals(object,object2);//推荐使用JDK7引入的工具类

        integer.equals(127);//Integer对象是在IntegerCache.cache产生，复用已有对象

        if (a == b){
            //预期进入此代码块，执行其他业务逻辑
            //但事实上a==b的结果为false
        }
        if (x.equals(y)){
            //预期进入此代码块，执行其他业务逻辑
            //但事实上equals的结果为false
        }
        //(1)指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的
        if (Math.abs(a-b)<diff){
            System.out.println("true");
        }

        //(2)使用BigDecimal来定义值，再进行浮点说的运算操作
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);

        if (x.compareTo(y)==0){
            System.out.println("true");
        }

        //推荐入参为string的构造方法，或使用BigDecimal的valueof 方法，BigDecimal(double)存在精度损失风险
        BigDecimal recommend1 = new BigDecimal("0.1");
        BigDecimal recommend2 = BigDecimal.valueOf(0.1);

    }

}
