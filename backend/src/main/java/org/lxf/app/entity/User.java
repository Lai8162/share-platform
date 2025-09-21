package org.lxf.app.entity;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.lxf.app.transform.demo.base.BaseDTOCastDemo;
import org.lxf.app.transform.demo.UserDemo;
import org.lxf.app.entity.base.BaseTime;

/**
 * 用户实体
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Data
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
@Table(value = "user")
public class User extends BaseTime implements BaseDTOCastDemo<User, UserDemo> {
    private String name;

    private String phone;

    private String email;

    private String address;

    private String employeeNumber;

    private String account;

    @Override
    public UserDemo buildDemo(User user) {
        UserDemo userDemo = new UserDemo();
        BeanUtil.copyProperties(user, userDemo);
        return userDemo;
    }
}
