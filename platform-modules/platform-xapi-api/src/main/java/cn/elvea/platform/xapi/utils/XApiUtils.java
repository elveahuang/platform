package cn.elvea.platform.xapi.utils;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.utils.DateUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.xapi.exception.InvalidRequestException;
import cn.elvea.platform.xapi.exception.XAPIException;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.model.Agent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * XApiUtils
 */
@Slf4j
public class XApiUtils {
    /**
     * 当前支持的时间格式
     */
    private final static String[] SUPPORT_PATTERNS = {
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SSS",
    };

    /**
     * 生成唯一ID
     *
     * @return String
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取Agent的唯一标识标记
     *
     * @param agent {@link Agent}
     * @return 唯一标识标记
     */
    public static String extractAgentUniqueIdentifier(Agent agent) throws XAPIException {
        String uniqueIdentifier = null;
        if (agent != null && StringUtils.isNotEmpty(agent.getMbox())) {
            uniqueIdentifier = "mbox";
        } else if (agent != null && StringUtils.isNotEmpty(agent.getMboxSHA1Sum())) {
            uniqueIdentifier = "mboxSHA1Sum";
        } else if (agent != null && StringUtils.isNotEmpty(agent.getOpenID())) {
            uniqueIdentifier = "openID";
        } else if (agent != null && agent.getAccount() != null &&
                (StringUtils.isNotEmpty(agent.getAccount().getName()) && StringUtils.isNotEmpty(agent.getAccount().getHomePage()))) {
            uniqueIdentifier = "account";
        }
        if (StringUtils.isNotEmpty(uniqueIdentifier)) {
            return uniqueIdentifier;
        } else {
            throw new XAPIException("Invalid agent param.");
        }
    }

    /**
     * 从字符串获取Agent对象
     *
     * @param json String
     * @return {@link Agent}
     */
    public static Agent extractAgentObject(String json) throws XAPIException {
        try {
            return new Agent(JsonMapper.toJsonNode(json));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("failed to extract agent object from json [{}]", json, e);
            throw new XAPIException("Invalid agent param.");
        }
    }

    /**
     * 解析日期
     */
    public static Date parseTimestamp(String timestamp) throws InvalidRequestException {
        if (StringUtils.isNotEmpty(timestamp)) {
            return DateUtils.parse(timestamp, DateTimeFormat.Pattern.ISO_DATE_TIME);
        }
        return null;
    }

    /**
     * 解析日期
     */
    public static String formatTimestamp(Date timestamp) throws InvalidRequestException {
        if (timestamp != null) {
            try {
                DateUtils.format(timestamp, DateTimeFormat.Pattern.ISO_DATE_TIME);
            } catch (Exception e) {
                log.error("failed to format timestamp [{}].", timestamp, e);
                throw new InvalidRequestException();
            }
        }
        return "";
    }

    public static Map<String, String> jsonNodeToExtensions(JsonNode jsonNode) {
        Map<String, String> extensions = Maps.newHashMap();
        Iterator<Map.Entry<String, JsonNode>> items = jsonNode.fields();
        while (items.hasNext()) {
            Map.Entry<String, JsonNode> item = items.next();
            extensions.put(item.getKey(), item.getValue().textValue());
        }
        return extensions;
    }

    public static ObjectNode extensionsToJsonNode(Map<String, String> extensions) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        for (Map.Entry<String, String> entry : extensions.entrySet()) {
            node.put(entry.getKey(), entry.getValue());
        }
        return node;
    }

}
