package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.threadlocal;

/**
 * @author Hong.Wu
 * @date: 14:58 2020/01/08
 */
public class SeqCount {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int nextSeq() {
        seqCount.set(seqCount.get() +1);
        return seqCount.get();
    }




}