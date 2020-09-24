package json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wenbaoxie
 * @Date 2020/9/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entity {
    String comments;
    Long id;
    String uri;
    String method;
    String permissionItemId;
    String belongsService;
}
