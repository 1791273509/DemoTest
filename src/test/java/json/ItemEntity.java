package json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author wenbaoxie
 * @Date 2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    String comments;
    Long id;
    String resourceId;
    Map<String, Object> operation;
}
