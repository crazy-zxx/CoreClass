package com.me.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Random;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class Test {

    public static void main(String[] args) throws Exception {

        //String
        String s = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});
        System.out.println(s);
        s = s.toUpperCase();
        System.out.println(s);

        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        //StringBuilder
        var sb = new StringBuilder(1024);
        sb.append("Mr ")
                .append("Bob")
                .append("!")
                .insert(0, "Hello, ");
        System.out.println(sb.toString());

        //StringJoiner
        String[] names = {"Bob", "Alice", "Grace"};
        var sj1 = new StringJoiner(", ", "Hello ", "!");
        for (String name : names) {
            sj1.add(name);
        }
        System.out.println(sj1.toString());

        var sj2 = String.join(", ", names);
        System.out.println(sj2.toString());

        //WrapperClass
        int i = 100;
        // 通过new操作符创建Integer实例(不推荐使用,会有编译警告):
        Integer n1 = new Integer(i);
        System.out.println(n1.intValue());
        // 通过静态方法valueOf(int)创建Integer实例:
        Integer n2 = Integer.valueOf(i);
        System.out.println(n2.intValue());
        // 通过静态方法valueOf(String)创建Integer实例:
        Integer n3 = Integer.valueOf("100");
        System.out.println(n3.intValue());

        int x1 = Integer.parseInt("100"); // 100
        int x2 = Integer.parseInt("100", 16); // 256,因为按16进制解析

        System.out.println(Integer.toString(100)); // "100",表示为10进制
        System.out.println(Integer.toString(100, 36)); // "2s",表示为36进制
        System.out.println(Integer.toHexString(100)); // "64",表示为16进制
        System.out.println(Integer.toOctalString(100)); // "144",表示为8进制
        System.out.println(Integer.toBinaryString(100)); // "1100100",表示为2进制

        //装箱和拆箱(JDK>=1.5)会影响执行效率，且拆箱时可能发生NullPointerException
        Integer n = 100; // 编译器自动使用Integer.valueOf(int)
        int x = n; // 编译器自动使用Integer.intValue()

        // boolean只有两个值true/false，其包装类型只需要引用Boolean提供的静态字段:
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        // int可表示的最大/最小值:
        int max = Integer.MAX_VALUE; // 2147483647
        int min = Integer.MIN_VALUE; // -2147483648
        // long类型占用的bit和byte数量:
        int sizeOfLong = Long.SIZE; // 64 (bits)
        int bytesOfLong = Long.BYTES; // 8 (bytes)

        // 向上转型为Number:
        Number num = Integer.valueOf(999);
        // 获取byte, int, long, float, double:
        byte b = num.byteValue();
        int no = num.intValue();
        long ln = num.longValue();
        float fl = num.floatValue();
        double d = num.doubleValue();

        byte xb = -1;
        byte yb = 127;
        System.out.println(Byte.toUnsignedInt(xb)); // 255
        System.out.println(Byte.toUnsignedInt(yb)); // 127

        Weekday day = Weekday.SUN;
        if (day.dayValue == 6 || day.dayValue == 7) {
            System.out.println("Today is " + day + ". Work at home!");
        } else {
            System.out.println("Today is " + day + ". Work at office!");
        }

        //BigInteger
        BigInteger bi = new BigInteger("1234567890");
        System.out.println(bi.pow(5)); // 2867971860299718107233761438093672048294900000

        BigInteger i1 = new BigInteger("1234567890");
        BigInteger i2 = new BigInteger("12345678901234567890");
        BigInteger sum = i1.add(i2); // 12345678902469135780
        System.out.println(sum);

        //BigInteger是从Number继承的，是不可变对象。
        Number bi2 = new BigInteger("123456789000");
        System.out.println(bi2.longValue()); // 123456789000
        // java.lang.ArithmeticException: BigInteger out of long range
        //System.out.println(bi2.multiply(bi2).longValueExact());

        BigInteger bf = new BigInteger("999999").pow(99);
        float ft = bf.floatValue();
        System.out.println(ft);

        //BigDecimal
        BigDecimal bd = new BigDecimal("123.4567");
        System.out.println(bd.multiply(bd)); // 15241.55677489
        System.out.println(bd.scale()); //小数位数

        BigDecimal d1 = new BigDecimal("123.4500");
        BigDecimal d2 = d1.stripTrailingZeros();
        System.out.println(d1.scale()); // 4
        System.out.println(d2.scale()); // 2,因为去掉了00

        BigDecimal d3 = new BigDecimal("1234500");
        BigDecimal d4 = d3.stripTrailingZeros();
        System.out.println(d3.scale()); // 0
        System.out.println(d4.scale()); // 负数，例如，-2，表示这个数是个整数，并且末尾有2个0。

        BigDecimal d11 = new BigDecimal("123.456789");
        BigDecimal d22 = d11.setScale(4, RoundingMode.HALF_UP); // 四舍五入，123.4568
        BigDecimal d33 = d11.setScale(4, RoundingMode.DOWN); // 直接截断，123.4567
        System.out.println(d22);
        System.out.println(d33);

        BigDecimal d111 = new BigDecimal("123.456");
        BigDecimal d222 = new BigDecimal("23.456789");
        System.out.println(d111.divide(d222, 10, RoundingMode.HALF_UP)); // 保留10位小数并四舍五入
        //System.out.println(d111.divide(d222)); // 报错：ArithmeticException，因为除不尽

        BigDecimal nb = new BigDecimal("12.345");
        BigDecimal mb = new BigDecimal("0.12");
        BigDecimal[] dr = nb.divideAndRemainder(mb);    //除法的同时求余数
        System.out.println(dr[0]); // 102
        System.out.println(dr[1]); // 0.105

        if (dr[1].signum() == 0) {
            System.out.println("n%m");// n是m的整数倍
        }else {
            System.out.println("n!%m");
        }

        BigDecimal dd1 = new BigDecimal("123.456");
        BigDecimal dd2 = new BigDecimal("123.45600");
        System.out.println(dd1.equals(dd2)); // false,因为scale不同
        System.out.println(dd1.equals(dd2.stripTrailingZeros())); // true,因为d2去除尾部0后scale变为2
        //必须使用compareTo()方法来比较，它根据两个值的大小分别返回负数、正数和0，分别表示小于、大于和等于。
        //总是使用compareTo()比较两个BigDecimal的值，不要使用equals()！
        System.out.println(dd1.compareTo(dd2)); // 0

        //BigDecimal也是从Number继承的，也是不可变对象。
        Number nbd=new BigDecimal("4654656.487891445454332889320211");
        System.out.println(nbd);

        //Math
        System.out.println(Math.abs(-100)); // 100
        System.out.println(Math.pow(2, 10)); // 2的10次方=1024
        double pi = Math.PI; // 3.14159...
        double e = Math.E; // 2.7182818...
        System.out.println(pi);
        System.out.println(e);
        System.out.println(Math.sin(Math.PI / 6));// sin(π/6) = 0.5

        double rx = Math.random(); // rx的范围是[0,1)
        double rMin = 10;
        double rMax = 50;
        double ry = rx * (rMax - rMin) + rMin; // ry的范围是[10,50)
        long rn = (long) ry; // rn的范围是[10,50)的整数
        System.out.println(ry);
        System.out.println(rn);

        //Random
        Random r = new Random();
        System.out.println(r.nextInt()); // 每次都不一样
        System.out.println(r.nextInt(10)); // 生成一个[0,10)之间的int
        System.out.println(r.nextLong()); // 每次都不一样
        System.out.println(r.nextFloat()); //生成一个[0,1)之间的float
        System.out.println(r.nextDouble()); //生成一个[0,1)之间的double

        Random r2 = new Random(12345);
        for (int j = 0; j < 10; j++) {
            System.out.println(r2.nextInt(100));
        }

        //SecureRandom真随机数
        //需要使用安全随机数的时候，必须使用SecureRandom，绝不能使用Random！
        SecureRandom sr = new SecureRandom();
        System.out.println(sr.nextInt(100));

        //Exception
        /*
        try {
            process1();
        } catch (Exception ex) {
            ex.printStackTrace();//打印异常的传播栈
            ex.getCause();  //在代码中获取原始异常可以使用Throwable.getCause()方法。如果返回null，说明已经是“根异常”了。
        }
         */

        //通常不要在finally中抛出异常,finally抛出异常后，原来在catch中准备抛出的异常就“消失”了，因为只能抛出一个异常。
        //没有被抛出的异常称为“被屏蔽”的异常（Suppressed Exception）。
        //在极少数的情况下，我们需要获知所有的异常。如何保存所有的异常信息？
        //方法是先用origin变量保存原始异常，然后调用Throwable.addSuppressed()，把原始异常添加进来，最后在finally抛出
        /*
        Exception origin = null;
        try {
            System.out.println(Integer.parseInt("abc"));
        } catch (Exception ee) {
            origin = ee;
            throw ee;
        } finally {
            System.out.println("...");
            Exception eee = new IllegalArgumentException();
            if (origin != null) {
                eee.addSuppressed(origin);
            }
            throw eee;
        }
         */

        //assert断言
        //要执行assert语句，必须给Java虚拟机传递-enableassertions（可简写为-ea）参数启用断言。
        // 所以，程序必须在命令行下运行才有效果。实际开发中，很少使用断言。更好的方法是编写单元测试
        int xx = -1;
        assert xx > 0;
        System.out.println(xx);

        //logger
        //默认级别是INFO，因此，INFO级别以下的日志，不会被打印出来
        //Logging系统在JVM启动时读取配置文件并完成初始化，一旦开始运行main()方法，就无法修改配置；
        //Java标准库内置的Logging使用并不是非常广泛
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");

        //Commons Logging + log4j
        //如果静态方法中调用，一般在类中定义静态变量 static final Log log = LogFactory.getLog(Main.class)
        //在实例中调用，一般在类中定义实例变量，子类可以直接使用该log实例 protected final Log log = LogFactory.getLog(getClass());
        Log log= LogFactory.getLog(Test.class);
        log.info("start...");
        log.warn("end.");
        log.error("error");
        log.fatal("fatal");

        try {
            int ttt= 10/0;
        } catch (Exception ee) {
            log.error("got exception!", ee);
        }

        //slf4j + logback
        org.slf4j.Logger logger1= LoggerFactory.getLogger(Test.class);
        logger1.info("info");
        logger1.warn("warning");
        logger1.error("error");

    }

    static void process1() {
        try {
            process2();
        } catch (NullPointerException e) {
            //捕获到异常并再次抛出时，一定要留住原始异常，否则很难定位第一案发现场！
            throw new IllegalArgumentException(e);
        }
    }

    static void process2() {
        throw new NullPointerException();
    }

    //枚举
    enum Weekday {
        MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"),
        THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"),
        SUN(7, "星期日");

        //final来限制字段
        public final int dayValue;
        private final String chinese;

        //private来限制构造方法
        private Weekday(int dayValue, String chinese) {
            this.dayValue = dayValue;
            this.chinese = chinese;
        }

        @Override
        public String toString() {
            return this.chinese;
        }
    }
}

