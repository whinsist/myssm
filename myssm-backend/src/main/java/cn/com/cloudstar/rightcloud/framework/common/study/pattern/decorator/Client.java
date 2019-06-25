package cn.com.cloudstar.rightcloud.framework.common.study.pattern.decorator;

/**
 * @author Hong.Wu
 * @date: 11:45 2019/06/21
 */
public class Client {
    public static void main(String[] args) {
        Component componet, componetSB, componetBB;

        componet = new Window();
        componetSB = new ScrollBarDecorator(componet);
        componetSB.display();
        System.out.println("---------");
        componetBB = new BlackBoarderDecorator(componet);
        componetBB.display();

    }
}
