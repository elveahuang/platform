package cn.elvea.platform.persistence.jdbc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * JdbcRepository
 *
 * @param <T> 实体
 * @param <K> 主键
 * @author elvea
 * @since 0.0.1
 */
public interface JdbcRepository<T, K> extends PagingAndSortingRepository<T, K> {
}
