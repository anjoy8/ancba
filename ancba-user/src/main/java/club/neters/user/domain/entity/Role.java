package club.neters.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色信息表
 *
 * @author laozhang
 * @date 2021/06/16
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Role")
public class Role extends BaseEntity {
    @TableField("Name")
    private String Name;

    @TableField("Description")
    private String Description;

    @TableField("OrderSort")
    private Integer OrderSort;

    @TableField("Enabled")
    private Boolean Enabled;

}
