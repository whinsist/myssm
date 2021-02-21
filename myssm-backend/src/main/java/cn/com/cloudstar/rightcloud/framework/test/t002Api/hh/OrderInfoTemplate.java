package cn.com.cloudstar.rightcloud.framework.test.t002Api.hh;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoTemplate {

        private ContractOrderRelHeadDTO headDTO;
        private String fileName;
        private XWPFTemplate template;

        public OrderInfoTemplate(ContractOrderRelHeadDTO headDTO) {
            this.headDTO = headDTO;
        }

        public String getFileName() {
            return fileName;
        }

        public XWPFTemplate getTemplate() {
            return template;
        }

        public OrderInfoTemplate invoke() {

            fileName = headDTO.getOrderNo();
            DetailData detailTable = new DetailData();
            List<RowRenderData> goods = new ArrayList<>();
            for (int i = 0; i < headDTO.getItemList().size(); i++) {
                ContractOrderRelItemDTO contractOrderRelItemDTO = headDTO.getItemList().get(i);
                RowRenderData row0 = RowRenderData.build(
                        contractOrderRelItemDTO.getOrderLineNo(),
                        contractOrderRelItemDTO.getGoodsCode(),
                        contractOrderRelItemDTO.getGoodsName(),
                        contractOrderRelItemDTO.getUnit(),
                        contractOrderRelItemDTO.getQty(),
                        //contractOrderRelItemDTO.getApplyDept(),
                        contractOrderRelItemDTO.getApplyUser(),
                        contractOrderRelItemDTO.getApplyUserTel(),
                        contractOrderRelItemDTO.getDeliveryDate(),
                        contractOrderRelItemDTO.getDeliveryFactoryName(),
                        contractOrderRelItemDTO.getLgobe(),
                        contractOrderRelItemDTO.getPlanTypeName(),
                        contractOrderRelItemDTO.getSupplyingAddress(),
                        contractOrderRelItemDTO.getRemark()
                );
                goods.add(row0);
            }

            detailTable.setGoods(goods);
            headDTO.setDetailTable(detailTable);
            Configure config = Configure.newBuilder().customPolicy("ITE", new DetailTablePolicy()).build();
            String path = "/template/orderInfo.docx";
            InputStream inputStream = this.getClass().getResourceAsStream(path);
            File targetFile = new File("temp/template/srm/orderInfo.docx");
            try {
                FileUtils.copyInputStreamToFile(inputStream, targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            template = XWPFTemplate.compile(targetFile, config).render(headDTO);
            targetFile.delete();

            return this;
        }
    }