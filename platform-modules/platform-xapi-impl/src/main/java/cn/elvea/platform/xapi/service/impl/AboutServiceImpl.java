package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.xapi.model.About;
import cn.elvea.platform.xapi.service.AboutService;
import org.springframework.stereotype.Service;

/**
 * AboutServiceImpl
 *
 * @author elvea
 */
@Service
public class AboutServiceImpl implements AboutService {

    /**
     * @see AboutService#about()
     */
    @Override
    public About about() {
        return new About();
    }

}
