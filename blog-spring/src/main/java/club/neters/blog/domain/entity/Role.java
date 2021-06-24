package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
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
@EntityDoc(note = "角色表", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Role")
public class Role extends BaseEntity {
    @EntityDoc(note = "角色名")
    @TableField("Name")
    private String Name;

    @EntityDoc(note = "描述")
    @TableField("Description")
    private String Description;

    @EntityDoc(note = "排序")
    @TableField("OrderSort")
    private Integer OrderSort;

    @EntityDoc(note = "是否启用")
    @TableField("Enabled")
    private Boolean Enabled;

}
