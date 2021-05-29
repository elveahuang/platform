package cn.elvea.platform.persistence.jdbc.auditor;

import cn.elvea.platform.commons.Context;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * UserAuditorAware
 *
 * @author elvea
 * @since 0.0.1
 */
public class UserAuditorAware implements AuditorAware<Long> {

    private final Context context;

    public UserAuditorAware(Context context) {
        this.context = context;
    }

    /**
     * @see AuditorAware#getCurrentAuditor()
     */
    public @NotNull
    Optional<Long> getCurrentAuditor() {
        return Optional.ofNullable(this.context.getCurrentUser().getId());
    }

}
