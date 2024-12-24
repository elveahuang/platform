package cc.elvea.platform.commons.oapis.facebody;

import cc.elvea.platform.commons.oapis.facebody.model.FaceBodyResult;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface FaceBodyService {

    FaceBodyResult detectFace(String target);

    FaceBodyResult compareFace(String target, String source);

}
