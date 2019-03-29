package mayfly.common.permission.registry;

import mayfly.common.permission.check.SysPermissionCheck;

/**
 * 系统所有权限码注册器
 * @author meilin.huang
 * @version 1.0
 * @date 2019-03-28 10:47 AM
 */
public interface SysPermissionCodeRegistry extends SysPermissionCheck {
    /**
     * 保存系统所有权限（用于实时禁用删除权限等）
     * @return
     */
    void save();

    /**
     * 重命名 (用于实时重命名系统权限中的状态，以便用于判断权限是否可用）
     * @param oldCode
     * @param newCode
     */
    void rename(String oldCode, String newCode);
}