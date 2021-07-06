package cn.elvea.platform.mybatis.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * AbstractEntityService
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractEntityService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
