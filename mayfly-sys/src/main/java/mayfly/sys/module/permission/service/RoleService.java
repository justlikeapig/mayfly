package mayfly.sys.module.permission.service;

import mayfly.sys.module.permission.entity.Role;
import mayfly.sys.module.base.service.BaseService;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2018-12-07 4:13 PM
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void deleteRole(Integer id);
}