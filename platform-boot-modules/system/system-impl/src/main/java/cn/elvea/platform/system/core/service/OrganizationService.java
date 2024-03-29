package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.core.model.dto.OrganizationDeleteDto;
import cn.elvea.platform.system.core.model.dto.OrganizationDto;
import cn.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface OrganizationService extends CachingEntityService<OrganizationEntity, Long> {

    /**
     * 保存部门
     */
    OrganizationDto saveOrganization(OrganizationSaveDto saveDto);

    /**
     * 删除部门
     */
    void deleteOrganization(OrganizationDeleteDto deleteDto);

    /**
     * 获取顶层部门
     */
    OrganizationEntity getRootOrganization();

    /**
     * 获取默认部门
     */
    OrganizationEntity getDefaultOrganization();

}
