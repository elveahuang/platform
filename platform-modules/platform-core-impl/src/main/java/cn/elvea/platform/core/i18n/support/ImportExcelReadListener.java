package cn.elvea.platform.core.i18n.support;

import cn.elvea.platform.commons.utils.SpringUtils;
import cn.elvea.platform.core.i18n.domain.entity.LabelEntity;
import cn.elvea.platform.core.i18n.manager.LabelManager;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ImportExcelReadListener
 *
 * @author elvea
 * @since 0.01
 */
public class ImportExcelReadListener extends AnalysisEventListener<LabelExcelRecord> {

    private static final int BATCH_COUNT = 100;

    private List<LabelExcelRecord> data = new ArrayList<>();

    @Override
    public void invoke(LabelExcelRecord record, AnalysisContext context) {
        data.add(record);

        if (data.size() >= BATCH_COUNT) {
            this.handleData();
            this.data.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        this.handleData();
    }

    private void handleData() {
        LabelManager labelManager = SpringUtils.getBean(LabelManager.class);
        List<LabelEntity> labelEntityList = new ArrayList<>();
        this.data.forEach(record -> {
            LabelEntity entity = labelManager.findByCode(record.getCode());
            if (entity != null) {
                entity.setZhCnLabel(record.getZhCnLabel());
                entity.setZhCnFinalInd(Boolean.TRUE);
                entity.setZhHkLabel(record.getZhHkLabel());
                entity.setZhHkFinalInd(Boolean.TRUE);
                entity.setEnUsLabel(record.getEnUsLabel());
                entity.setEnUsFinalInd(Boolean.TRUE);
            }
            labelEntityList.add(entity);
        });
        labelManager.save(labelEntityList);
    }

}
