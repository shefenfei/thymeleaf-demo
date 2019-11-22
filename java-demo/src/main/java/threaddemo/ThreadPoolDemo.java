package threaddemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
    }
}
