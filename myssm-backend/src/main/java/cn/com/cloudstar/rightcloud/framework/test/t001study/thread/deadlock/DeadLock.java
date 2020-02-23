package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.deadlock;
/**
 你好，我是老齐，在学习过程中遇到任何问题可以加我的QQ群722570599
 在这里老齐将给你直接提供帮助与支持，只为你可以学的更轻松。
 群里还有更多干货等你来学习。
 */
public class DeadLock {
    private static String fileA = "A文件";
    private static String fileB = "B文件";

    public static void main(String[] args) {
        new Thread(){ //线程1
            public void run(){
                while(true) {
                    synchronized (fileA) {//打开文件A，线程独占
                        System.out.println(this.getName() + ":文件A写入");
                        synchronized (fileB) {
                            System.out.println(this.getName() + ":文件B写入");
                        }
                        System.out.println(this.getName() + ":所有文件保存");
                    }
                }
            }
        }.start();

        new Thread(){ //线程2
            public void run(){
                while(true) {
                    synchronized (fileB) {//打开文件A，线程独占
                        System.out.println(this.getName() + ":文件B写入");
                        synchronized (fileA) {
                            System.out.println(this.getName() + ":文件A写入");
                        }
                        System.out.println(this.getName() + ":所有文件保存");
                    }
                }
            }
        }.start();
    }
}
