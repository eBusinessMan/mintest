package com.mory.interfaces.impl;

import com.mory.interfaces.LettersComposeCalculator;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 抽象的平行"字母组合"计算器
 * @param <T>
 */
public abstract class ParallelLettersComposeCalculator<T> implements LettersComposeCalculator<T> {

    /**
     * 线程池, 用于并行计算每个数字组合下的 字母组合
     */
    private ThreadPoolExecutor threadPool;

    public ParallelLettersComposeCalculator() {
        this(null);
    }

    public ParallelLettersComposeCalculator(Integer maxThreads) {
        if (maxThreads == null || maxThreads <= 0) {
            maxThreads = Runtime.getRuntime().availableProcessors() * 2;//cpu核数*2
        }
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxThreads);
    }

    @Override
    public Map<T, Set> calculate(Set<T> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }

        int size = set.size();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        //并发Map
        ConcurrentHashMap<T, Set> dlMap = new ConcurrentHashMap<>(size);

        ParallelLettersComposeCalculator obj = this;

        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();

            threadPool.execute(() -> {
                try {
                    obj.doCalculate(dlMap, t);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            //主线程等待
            countDownLatch.await();
            //关闭线程池
            threadPool.shutdownNow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //所有计算任务都完成, 返回结果
        return dlMap;
    }

    public abstract void doCalculate(ConcurrentHashMap<T, Set> dlMap, T digitCompose);

}
