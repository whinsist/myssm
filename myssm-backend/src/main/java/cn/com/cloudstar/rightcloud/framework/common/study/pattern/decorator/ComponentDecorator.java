package cn.com.cloudstar.rightcloud.framework.common.study.pattern.decorator;

/**
 * @author Hong.Wu
 * @date: 11:36 2019/06/21
 */
public class ComponentDecorator extends Component {
    private Component component;

    public ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        this.component.display();
    }
}
