package com.zhang.filter;

/**
 * 简单的令牌桶限流
 */
public class RateLimiter {

    /**
     * 桶的大小
     */
    private Integer limit;

    /**
     * 桶当前的token
     */
    private static Integer tokens = 0;

    /**
     * 构造参数
     */
    public RateLimiter(Integer limit, Integer speed){
        //初始化桶的大小，且桶一开始是满的
        this.limit = limit;
        tokens = this.limit;

        //任务线程：每秒新增speed个令牌
        new Thread(() ->{
            while (true){
                try {
                    Thread.sleep(1000L);

                    int newTokens = tokens + speed;
                    if(newTokens > limit){
                        tokens = limit;
                        System.out.println("令牌桶满了！！！");
                    }else{
                        tokens = newTokens;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 根据令牌数判断是否允许执行，需要加锁
     */
    public synchronized boolean execute() {
        if (tokens > 0) {
            tokens = tokens - 1;
            return true;
        }
        return false;
    }
}
