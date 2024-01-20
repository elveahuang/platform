package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.dto.AccountDto;
import cn.elvea.platform.system.core.model.entity.AccountEntity;
import cn.elvea.platform.system.core.model.form.AccountForm;
import cn.elvea.platform.system.core.model.request.AccountCheckRequest;
import cn.elvea.platform.system.core.model.request.AccountSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 * @see EntityService
 * @since 24.1.0
 */
public interface AccountService extends CachingEntityService<AccountEntity, Long> {

    /**
     * 检查用户名是否可用
     */
    boolean check(AccountCheckRequest request);

    /**
     * 搜索用户
     */
    Page<AccountDto> search(AccountSearchRequest request);

    /**
     * 根据用户名查找用户
     */
    AccountEntity findByUsername(String username);

    /**
     * 根据ID查找用户
     */
    AccountEntity findById(Long id);

    /**
     * 根据邮箱查找用户
     */
    AccountEntity findByEmail(String email);

    /**
     * 根据手机查找用户
     */
    AccountEntity findByMobile(String mobileCountryCode, String mobileNumber);

    /**
     * 保存用户
     */
    void saveAccount(AccountForm form);

}
