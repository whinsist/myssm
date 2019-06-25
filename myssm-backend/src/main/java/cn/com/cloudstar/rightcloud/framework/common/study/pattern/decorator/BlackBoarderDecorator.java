package cn.com.cloudstar.rightcloud.framework.common.study.pattern.decorator;

/**
 * @author Hong.Wu
 * @date: 11:43 2019/06/21
 */
public class BlackBoarderDecorator extends ComponentDecorator {

    public BlackBoarderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setBlackBoarder();
        super.display();
    }

    private void setBlackBoarder() {
        System.out.println("为构件增加黑色边框！");
    }

}
