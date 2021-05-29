package cn.elvea.platform.persistence.jdbc.callback;

import cn.elvea.platform.base.domain.IdEntity;
import cn.elvea.platform.commons.id.IdWorker;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.util.Assert;

/**
 * IdEntityCallback
 *
 * @author elvea
 * @since 0.0.1
 */
public class IdEntityCallback implements BeforeSaveCallback<IdEntity> {

    private final IdWorker idWorker;

    public IdEntityCallback(IdWorker idWorker) {
        Assert.notNull(idWorker, "IdEntityCallback.idWorker must not be null!");
        this.idWorker = idWorker;
    }

    /**
     * @see BeforeSaveCallback#onBeforeSave(Object, MutableAggregateChange)
     */
    @NotNull
    @Override
    public IdEntity onBeforeSave(IdEntity entity, @NotNull MutableAggregateChange<IdEntity> aggregateChange) {
        if (entity.getId() == null) {
            entity.setId(this.idWorker.nextId());
        }
        return entity;
    }

}
