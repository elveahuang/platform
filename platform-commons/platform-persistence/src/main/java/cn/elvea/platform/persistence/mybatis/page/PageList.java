package cn.elvea.platform.persistence.mybatis.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * PageList
 *
 * @param <T>
 * @author elvea
 * @since 0.0.1
 */
public class PageList<T> extends ArrayList<T> implements List<T> {

    private final long total;

    private final Pageable pageable;

    public PageList(List<T> content, Pageable pageable, long total) {
        this.addAll(content);
        this.pageable = pageable;
        this.total = pageable.toOptional().filter(it -> !content.isEmpty())//
                .filter(it -> it.getOffset() + it.getPageSize() > total)//
                .map(it -> it.getOffset() + content.size())//
                .orElse(total);
    }

    public Page<T> getPage() {
        return new PageImpl<>(this, this.pageable, total);
    }

}
