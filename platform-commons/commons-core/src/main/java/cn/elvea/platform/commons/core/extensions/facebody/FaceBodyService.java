package cn.elvea.platform.commons.core.extensions.facebody;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface FaceBodyService {

    FaceBodyResult detectFace(String target);

    FaceBodyResult compareFace(String target, String source);

}
