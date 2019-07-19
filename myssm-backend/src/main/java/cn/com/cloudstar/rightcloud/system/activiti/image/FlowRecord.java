package cn.com.cloudstar.rightcloud.system.activiti.image;

/**
 * 连接线记录
 * @author yuz
 * @date 2018/12/7
 */
public class FlowRecord {
    int[] xPoints;
    int[] yPoints;
    boolean drawConditionalIndicator;
    boolean isDefault;
    boolean highLighted;
    double scaleFactor;

    public FlowRecord() {}

    public FlowRecord(int[] xPoints, int[] yPoints, boolean drawConditionalIndicator, boolean isDefault, boolean highLighted, double scaleFactor) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.drawConditionalIndicator = drawConditionalIndicator;
        this.isDefault = isDefault;
        this.highLighted = highLighted;
        this.scaleFactor = scaleFactor;
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public boolean isDrawConditionalIndicator() {
        return drawConditionalIndicator;
    }

    public void setDrawConditionalIndicator(boolean drawConditionalIndicator) {
        this.drawConditionalIndicator = drawConditionalIndicator;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isHighLighted() {
        return highLighted;
    }

    public void setHighLighted(boolean highLighted) {
        this.highLighted = highLighted;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
}
