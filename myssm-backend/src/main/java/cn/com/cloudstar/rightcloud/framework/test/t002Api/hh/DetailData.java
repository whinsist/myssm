package cn.com.cloudstar.rightcloud.framework.test.t002Api.hh;

import java.util.List;

import com.deepoove.poi.data.RowRenderData;

public class DetailData {
    
    // 货品数据
    private List<RowRenderData> goods;
    
    // 人工费数据
    private List<RowRenderData> labors;

    public List<RowRenderData> getGoods() {
        return goods;
    }

    public void setGoods(List<RowRenderData> goods) {
        this.goods = goods;
    }

    public List<RowRenderData> getLabors() {
        return labors;
    }

    public void setLabors(List<RowRenderData> labors) {
        this.labors = labors;
    }

    private RowRenderData last;

    public RowRenderData getLast() {
        return last;
    }

    public void setLast(RowRenderData last) {
        this.last = last;
    }
}
