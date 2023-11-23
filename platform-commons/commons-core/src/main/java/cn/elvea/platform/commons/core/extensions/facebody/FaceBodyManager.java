package cn.elvea.platform.commons.core.extensions.facebody;

import cn.elvea.platform.commons.core.extensions.facebody.aliyun.AliyunFaceBodyService;
import cn.elvea.platform.commons.core.extensions.facebody.tencent.TencentFaceBodyService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class FaceBodyManager {

    private final FaceBodyConfig config;

    public FaceBodyManager(FaceBodyConfig config) {
        this.config = config;
    }

    public FaceBodyService getFaceBodyService() {
        if (FaceBodyType.Aliyun.equals(config.getType())) {
            return getAliyunFaceBodyService();
        } else if (FaceBodyType.Tencent.equals(config.getType())) {
            return getTencentFaceBodyService();
        }
        return getAliyunFaceBodyService();
    }

    public FaceBodyService getAliyunFaceBodyService() {
        return getAliyunFaceBodyService(this.config.getAliyun());
    }

    public FaceBodyService getAliyunFaceBodyService(AliyunFaceBodyService.Config config) {
        return new AliyunFaceBodyService(config);
    }

    public FaceBodyService getTencentFaceBodyService() {
        return getTencentFaceBodyService(this.config.getTencent());
    }

    public FaceBodyService getTencentFaceBodyService(TencentFaceBodyService.Config config) {
        return new TencentFaceBodyService(config);
    }

}
