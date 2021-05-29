package cn.elvea.platform.commons.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * JsoupUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class JsoupUtils {

    private static final int CONNECT_TIMEOUT = 3 * 60 * 1000;

    /**
     * 抓取网页内容
     */
    public static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).timeout(CONNECT_TIMEOUT).get();
    }

}
