package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.threadlocal;

public  class SeqThread extends Thread {

        private SeqCount seqCount;

        public SeqThread(SeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(Thread.currentThread().getName()+" seqCount:"+seqCount.nextSeq());
            }
        }
    }