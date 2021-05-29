package cn.elvea.platform.commons.storage.domain.dto;

import io.minio.GenericResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文件对象
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MinFileObject extends FileObject<GenericResponse> implements Serializable {
}
