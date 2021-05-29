package cn.elvea.platform.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * 中华人民共和国县以上行政区划代码。
 * 数据来源于民政部官网，http://www.mca.gov.cn/article/sj/xzqh/。
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class RegionUtils {

    /**
     * 获取民政部官方数据
     *
     * @return {@link McaData}
     */
    public static McaData fetchMcaData() throws Exception {
        McaData data = new McaData();
        String url = "http://www.mca.gov.cn/article/sj/xzqh/2020/2020/202101041104.html";
        Document doc = JsoupUtils.getDocument(url);
        Elements trs = doc.select("tr");
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            if (tds.size() > 3) {
                String code = tds.get(1).text();
                String title = tds.get(2).text();
                if (StringUtils.isNotEmpty(code)) {
                    if (code.trim().endsWith("0000")) {
                        data.getProvinceList().add(new Region(code, title));
                    } else if (code.trim().endsWith("00")) {
                        data.getCityList().add(new Region(code, title));
                    } else {
                        data.getCountyList().add(new Region(code, title));
                    }
                }
            }
        }
        return data;
    }

    @Data
    @NoArgsConstructor
    static class McaData {
        private List<Region> provinceList = Lists.newArrayList();
        private List<Region> cityList = Lists.newArrayList();
        private List<Region> countyList = Lists.newArrayList();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Region {
        private String code;
        private String title;
    }

}
