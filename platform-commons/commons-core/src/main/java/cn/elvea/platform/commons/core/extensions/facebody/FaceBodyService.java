package cn.elvea.platform.commons.core.extensions.facebody;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface FaceBodyService {

    FaceBodyResult detectFace(String target);

    FaceBodyResult compareFace(String target, String source);

}
