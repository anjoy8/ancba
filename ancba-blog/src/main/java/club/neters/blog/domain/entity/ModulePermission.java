
package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ModulePermission表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode(callSuper = false)
@EntityDoc(note = "ModulePermission", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ModulePermission")


public class ModulePermission extends BaseEntity {

    @EntityDoc(note = "ModuleId")
    @TableField("ModuleId")
    private Integer ModuleId;

    @EntityDoc(note = "PermissionId")
    @TableField("PermissionId")
    private Integer PermissionId;

}