package mayfly.sys.module.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mayfly.core.base.mapper.annotation.NoColumn;
import mayfly.core.base.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 资源类
 *
 * @author hml
 * @date 2018/6/27 下午2:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6346288647519202567L;

    private Integer pid;

    private Integer type;

    private Integer weight;

    private String name;

    private String icon;

//    private String path;

    private String code;

    private Integer status;

    @NoColumn
    private List<Resource> children;

}
