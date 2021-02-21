package cn.com.cloudstar.rightcloud.framework.test.t002Api.hh;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * 付款通知书 明细表格的自定义渲染策略<br/>
 * 1. 填充货品数据 <br/>
 * 2. 填充人工费数据 <br/>
 *
 * @author Sayi
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {

    // 填充数据所在行数
    int goodsStartRow = 1;

    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        DetailData detailData = (DetailData) data;

        List<RowRenderData> goods = detailData.getGoods();
        if (null != goods) {
            table.removeRow(goodsStartRow);
            for (int i = 0; i < goods.size(); i++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(goodsStartRow);
                for (int j = 0; j < goods.get(i).getRowData().size(); j++) insertNewTableRow.createCell();
                MiniTableRenderPolicy.renderRow(table, goodsStartRow, goods.get(i));
            }
        }
    }
}