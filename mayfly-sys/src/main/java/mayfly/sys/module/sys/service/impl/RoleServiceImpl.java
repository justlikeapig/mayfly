package mayfly.sys.module.sys.service.impl;

import mayfly.core.base.service.impl.BaseServiceImpl;
import mayfly.core.exception.BusinessAssert;
import mayfly.sys.module.sys.entity.AccountRoleDO;
import mayfly.sys.module.sys.entity.RoleDO;
import mayfly.sys.module.sys.entity.RoleResourceDO;
import mayfly.sys.module.sys.mapper.RoleMapper;
import mayfly.sys.module.sys.service.AccountRoleService;
import mayfly.sys.module.sys.service.RoleResourceService;
import mayfly.sys.module.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2018-12-07 4:13 PM
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    @Override
    protected void setBaseMapper() {
        super.baseMapper = roleMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(Integer id) {
        RoleDO role = getById(id);
        BusinessAssert.notNull(role, "角色不存在");
        // 删除角色关联的用户角色信息
        accountRoleService.deleteByCondition(AccountRoleDO.builder().roleId(id).build());
        // 删除角色关联的资源信息
        roleResourceService.deleteByCondition(RoleResourceDO.builder().roleId(id).build());
        deleteById(id);
    }
}
