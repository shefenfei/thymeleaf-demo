package threaddemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {


    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d-").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100),
                threadFactory
        );

        for (int i =0; i < 20; i ++) {
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "打印数据");
            });
        }

        threadPoolExecutor.shutdown();



        int i = 5 >> 2;
        System.out.println(i);

        double floor = Math.floor(1.9999);
        System.out.println(floor);

        double ceil = Math.ceil(1.999);
        System.out.println(ceil);


        Object o = new Object();

        int[] arr1 = {1,2,3,4,6};
        for (int i1 = 0; i1 < arr1.length; i1++) {
            // TODO: 2020-01-03

        }


        String string = Objects.toString(null, "12121");
        System.out.println(string);

    }
}
