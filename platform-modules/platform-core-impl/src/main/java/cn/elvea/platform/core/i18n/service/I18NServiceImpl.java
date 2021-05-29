package cn.elvea.platform.core.i18n.service;

import cn.elvea.platform.commons.translater.TranslatorFactory;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.core.i18n.domain.entity.LabelEntity;
import cn.elvea.platform.core.i18n.domain.entity.LangEntity;
import cn.elvea.platform.core.i18n.manager.LabelManager;
import cn.elvea.platform.core.i18n.manager.LangManager;
import cn.elvea.platform.core.i18n.support.ImportExcelReadListener;
import cn.elvea.platform.core.i18n.support.LabelExcelRecord;
import com.alibaba.excel.EasyExcel;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static cn.elvea.platform.core.i18n.I18NConstants.*;

/**
 * 国际化多语言服务
 *
 * @author elvea
 * @see I18NService
 * @since 0.0.1
 */
@Slf4j
@Service
public class I18NServiceImpl implements I18NService {

    private final LabelManager labelManager;

    private final LangManager langManager;

    private final TranslatorFactory translatorFactory;

    private final PlatformTransactionManager transactionManager;

    public I18NServiceImpl(LabelManager labelManager, LangManager langManager, TranslatorFactory translatorFactory, PlatformTransactionManager transactionManager) {
        this.labelManager = labelManager;
        this.langManager = langManager;
        this.translatorFactory = translatorFactory;
        this.transactionManager = transactionManager;
    }

    /**
     * @see I18NService#generateI18NFile()
     */
    @Override
    public void generateI18NFile() {
        // 清理已有临时目录
        File targetDir = new File("../temp");
        File targetDistDir = new File("./src/main/resources/messages");
        try {
            log.debug("delete temp directory - {}", targetDir.getAbsolutePath());
            FileUtils.deleteDirectory(targetDir);
        } catch (Exception e) {
            log.error("delete temp directory - {}", targetDir.getAbsolutePath(), e);
            e.printStackTrace();
        }

        Iterable<LabelEntity> labelEntityList = this.labelManager.findAll(Sort.by(Sort.Direction.ASC, "code"));
        Iterable<LangEntity> langEntityList = this.langManager.findAll();
        langEntityList.forEach((langEntity -> {
            String locale = langEntity.getLocale();
            log.debug("generate {} i18n file.", locale);

            Properties properties = new Properties();
            JsonObject json = new JsonObject();

            labelEntityList.forEach(labelEntity -> {
                String key = labelEntity.getCode();
                String label = this.getLangLabel(langEntity, labelEntity);

                properties.put(key, label);
                json.addProperty(key, label);
            });

            // 生成多语言JSON文件
            File jsonFile = new File(targetDir, "label_" + locale + ".json");
            log.debug("generate {} json file {}.", locale, jsonFile.getAbsolutePath());
            try {
                FileUtils.writeStringToFile(jsonFile, json.toString(), StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.error("generate {} json file {} failed.", locale, jsonFile.getAbsolutePath());
                e.printStackTrace();
            }

            // 生成多语言PROPERTIES文件
            File propertiesFile = new File(targetDir, "messages_" + locale + ".properties");
            log.debug("generate {} properties file {}.", locale, jsonFile.getAbsolutePath());
            try (OutputStream output = new FileOutputStream(propertiesFile)) {
                properties.store(output, null);
                FileUtils.copyFileToDirectory(propertiesFile, targetDistDir);
            } catch (Exception e) {
                log.error("generate {} properties file {} failed.", locale, jsonFile.getAbsolutePath(), e);
                e.printStackTrace();
            }
        }));
    }

    /**
     * @see I18NService#translate()
     */
    @Override
    @Transactional
    public void translate() {
        log.debug("translate label...");
        Iterable<LabelEntity> labelEntityList = this.labelManager.findAll(Sort.by(Sort.Direction.ASC, "code"));
        labelEntityList.forEach(label -> {
            String sourceText = getSourceLabel(label);
            String sourceLang = getSourceLang(label);
            log.debug("translate label [{}] source lang [{}] source text [{}] ...", label.getCode(), sourceLang, sourceText);
            // 每一个多语言文本都采用独立的数据库事务
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    try {
                        if (!label.getZhCnFinalInd()) {
                            label.setZhCnLabel(translatorFactory.getTranslator().translate(sourceLang, LOCALE_ZH_CN_CODE, sourceText));
                            label.setZhCnFinalInd(Boolean.TRUE);
                            Thread.sleep(1000);
                        }
                        if (!label.getZhHkFinalInd()) {
                            label.setZhHkLabel(translatorFactory.getTranslator().translate(sourceLang, LOCALE_ZH_TW_CODE, sourceText));
                            label.setZhHkFinalInd(Boolean.TRUE);
                            Thread.sleep(1000);
                        }
                        if (!label.getEnUsFinalInd()) {
                            label.setEnUsLabel(translatorFactory.getTranslator().translate(sourceLang, LOCALE_EN_US_CODE, sourceText));
                            label.setEnUsFinalInd(Boolean.TRUE);
                            Thread.sleep(1000);
                        }
                        labelManager.save(label);
                        log.debug("translate label [{}] source lang [{}] source text [{}] finished.", label.getCode(), sourceLang, sourceText);
                    } catch (Exception e) {
                        log.error("translate label [{}] source lang [{}] source text [{}] failed.", label.getCode(), sourceLang, sourceText, e);
                        status.setRollbackOnly();
                        e.printStackTrace();
                    }
                }
            });
        });
        log.debug("translate label finished.");
    }

    /**
     * @see I18NService#exportExcel()
     */
    @Override
    public void exportExcel() {
        log.debug("export label...");

        Iterable<LabelEntity> labelEntityList = this.labelManager.findAll(Sort.by(Sort.Direction.ASC, "code"));

        List<LabelExcelRecord> data = new ArrayList<>();
        labelEntityList.forEach(label -> {
            LabelExcelRecord record = new LabelExcelRecord();
            record.setCode(label.getCode());
            record.setZhCnLabel(label.getZhCnLabel());
            record.setZhHkLabel(label.getZhHkLabel());
            record.setEnUsLabel(label.getEnUsLabel());
            data.add(record);
        });

        File targetDir = new File("../temp");
        File targetFile = new File(targetDir, "label.xlsx");
        EasyExcel.write(targetFile, LabelExcelRecord.class).sheet("Data").doWrite(data);

        log.debug("export label finished.");
    }

    /**
     * @see I18NService#importExcel()
     */
    @Override
    public void importExcel() {
        File targetDir = new File("../temp");
        File targetFile = new File(targetDir, "label.xlsx");
        EasyExcel.read(targetFile, LabelExcelRecord.class, new ImportExcelReadListener()).sheet().doRead();
    }

    /**
     * 获取对应语言的多语言文本
     */
    private String getSourceLabel(LabelEntity label) {
        if (label.getZhCnSourceInd()) {
            return label.getZhCnLabel();
        } else if (label.getZhCnSourceInd()) {
            return label.getZhHkLabel();
        } else if (label.getZhCnSourceInd()) {
            return label.getEnUsLabel();
        }
        return label.getZhCnLabel();
    }

    /**
     * 获取对应语言的多语言文本
     */
    private String getSourceLang(LabelEntity label) {
        if (label.getZhCnSourceInd()) {
            return LOCALE_ZH_CN_CODE;
        } else if (label.getZhCnSourceInd()) {
            return LOCALE_ZH_TW_CODE;
        } else if (label.getZhCnSourceInd()) {
            return LOCALE_EN_US_CODE;
        }
        return LOCALE_ZH_CN_CODE;
    }

    /**
     * 获取对应语言的多语言文本
     */
    private String getLangLabel(LangEntity lang, LabelEntity label) {
        if (LOCALE_ZH_CN_CODE.equalsIgnoreCase(lang.getLocale()) && StringUtils.hasLength(label.getZhCnLabel())) {
            return label.getZhCnLabel();
        } else if (LOCALE_ZH_TW_CODE.equalsIgnoreCase(lang.getLocale()) && StringUtils.hasLength(label.getZhHkLabel())) {
            return label.getZhHkLabel();
        } else if (LOCALE_EN_US_CODE.equalsIgnoreCase(lang.getLocale()) && StringUtils.hasLength(label.getEnUsLabel())) {
            return label.getEnUsLabel();
        }
        return label.getZhCnLabel();
    }

}
