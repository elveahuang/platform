package cn.elvea.platform.core.socket.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * SimpleTextMessage
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class SimpleTextMessage implements Serializable {
    private String content;
}
