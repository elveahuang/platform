package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.commons.utils.ConvertUtils;
import cn.elvea.platform.xapi.entity.StatementEntity;
import cn.elvea.platform.xapi.model.Statement;
import cn.elvea.platform.xapi.model.StatementsResult;
import cn.elvea.platform.xapi.service.StatementService;
import cn.elvea.platform.xapi.utils.XApiUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * StatementServiceImpl
 *
 * @author elvea
 */
@Service
public class StatementServiceImpl extends AbstractXApiService implements StatementService {

    /**
     * @see StatementService#saveStatement(String, Statement)
     */
    @Override
    public void saveStatement(String statementId, Statement statement) {
        StatementEntity entity = new StatementEntity();
        ConvertUtils.copyProperties(statement, entity);
        entity.setId(statementId);
        this.statementRepository.save(entity);
    }

    /**
     * @see StatementService#saveStatements(List)
     */
    @Override
    public List<String> saveStatements(List<Statement> statements) {
        List<String> ids = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(statements)) {
            List<StatementEntity> statementEntities = Lists.newArrayList();
            for (Statement statement : statements) {
                StatementEntity entity = new StatementEntity();
                ConvertUtils.copyProperties(statement, entity);
                if (StringUtils.isEmpty(entity.getId())) {
                    entity.setId(XApiUtils.randomUUID());
                }
                statementEntities.add(entity);
            }
            this.statementRepository.saveAll(statementEntities);
            for (StatementEntity entity : statementEntities) {
                ids.add(entity.getId());
            }
        }
        return ids;
    }

    /**
     * @see StatementService#getStatement(String, String)
     */
    @Override
    public Statement getStatement(String statementId, String voidedStatementId) {
        StatementEntity entity = this.statementRepository.findById(statementId).orElse(null);
        Statement statement = new Statement();
        ConvertUtils.copyProperties(entity, statement);
        return statement;
    }

    /**
     * @see StatementService#getStatements(String, String, String, String, Boolean, Boolean, String, String, String, Boolean, Boolean, String)
     */
    @Override
    public StatementsResult getStatements(String agentJson,
                                          String verb,
                                          String activity,
                                          String registration,
                                          Boolean relatedActivities,
                                          Boolean relatedAgents,
                                          String since,
                                          String until,
                                          String format,
                                          Boolean attachments,
                                          Boolean ascending,
                                          String limit) {
        return null;
    }

    /**
     * 私有方法用于构建查询条件
     */
    private Criteria createCriteria(String statementId,
                                    String voidedStatementId,
                                    String agentJson,
                                    String verb,
                                    String activity,
                                    String registration,
                                    Boolean relatedActivities,
                                    Boolean relatedAgents,
                                    String since,
                                    String until,
                                    String format,
                                    Boolean attachments,
                                    Boolean ascending,
                                    String limit) {
        Criteria criteria = new Criteria();
        //
        if (StringUtils.isNotEmpty(statementId)) {
            criteria.and("statementId").is(statementId);
        }
        //
        if (StringUtils.isNotEmpty(voidedStatementId)) {
            criteria.and("voidedStatementId").is(voidedStatementId);
        }
        //
        processAgentCriteria(criteria, agentJson);
        //
        if (StringUtils.isNotEmpty(verb)) {
            criteria.and("verb").is(verb);
        }
        //
        if (StringUtils.isNotEmpty(activity)) {
            criteria.and("activity").is(activity);
        }
        //
        if (StringUtils.isNotEmpty(registration)) {
            criteria.and("registration").is(registration);
        }
        //
        if (relatedActivities == Boolean.TRUE) {
        }
        //
        if (relatedAgents == Boolean.TRUE) {
        }
        //
        if (StringUtils.isNotEmpty(since)) {
            Date sinceDateObject = XApiUtils.parseTimestamp(since);
            if (sinceDateObject != null) {
                criteria.and("createdAt").gt(sinceDateObject);
            }
        }
        //
        if (StringUtils.isNotEmpty(until)) {
            Date utilDateObject = XApiUtils.parseTimestamp(until);
            if (utilDateObject != null) {
                criteria.and("createdAt").gt(utilDateObject);
            }
        }
        //
        if (StringUtils.isNotEmpty(format)) {
        }
        //
        if (attachments == Boolean.TRUE) {
        }
        //
        if (ascending == Boolean.TRUE) {
        }
        return criteria;
    }

}
