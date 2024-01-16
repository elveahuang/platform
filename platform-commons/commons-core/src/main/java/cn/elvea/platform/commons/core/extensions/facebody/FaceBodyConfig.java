package cn.elvea.platform.commons.core.extensions.facebody;

import cn.elvea.platform.commons.core.extensions.facebody.aliyun.AliyunFaceBodyService;
import cn.elvea.platform.commons.core.extensions.facebody.tencent.TencentFaceBodyService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaceBodyConfig {
    @Builder.Default
    private Boolean enabled = Boolean.FALSE;
    @Builder.Default
    private FaceBodyType type = FaceBodyType.Aliyun;
    @Builder.Default
    private AliyunFaceBodyService.Config aliyun = new AliyunFaceBodyService.Config();
    @Builder.Default
    private TencentFaceBodyService.Config tencent = new TencentFaceBodyService.Config();
}
