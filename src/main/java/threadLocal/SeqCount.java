package threadLocal;

import java.util.concurrent.CountDownLatch;

public class SeqCount {
    private static CountDownLatch countDownLatch = new CountDownLatch(4);
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int nextInt(){
        threadLocal.set(threadLocal.get()+1);
        return threadLocal.get();
    }
    public static void main(String[] args){
        SeqCount seqCount = new SeqCount();
        SeqThread seqThread = new SeqThread(seqCount);
        SeqThread seqThread2 = new SeqThread(seqCount);
        SeqThread seqThread3 = new SeqThread(seqCount);
        SeqThread seqThread4 = new SeqThread(seqCount);
        seqThread.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();
    }

    private static class SeqThread extends Thread{
        private SeqCount seqCount;

        public SeqThread(SeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" into run");
            try {
                countDownLatch.countDown();
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" running");
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+",count:"+seqCount.nextInt());
            }
        }
    }
}
