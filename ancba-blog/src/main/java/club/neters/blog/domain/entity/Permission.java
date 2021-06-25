package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Permission表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode(callSuper = false)
@EntityDoc(note = "Permission", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Permission")
public class Permission extends BaseEntity {

    @EntityDoc(note = "Code")
    @TableField("Code")
    private String Code;

    @EntityDoc(note = "Name")
    @TableField("Name")
    private String Name;

    @EntityDoc(note = "IsButton")
    @TableField("IsButton")
    private Boolean IsButton;

    @EntityDoc(note = "IsHide")
    @TableField("IsHide")
    private Boolean IsHide;

    @EntityDoc(note = "IskeepAlive")
    @TableField("IskeepAlive")
    private Boolean IskeepAlive;

    @EntityDoc(note = "Func")
    @TableField("Func")
    private String Func;

    @EntityDoc(note = "OrderSort")
    @TableField("OrderSort")
    private Integer OrderSort;

    @EntityDoc(note = "Icon")
    @TableField("Icon")
    private String Icon;

    @EntityDoc(note = "Description")
    @TableField("Description")
    private String Description;

    @EntityDoc(note = "Enabled")
    @TableField("Enabled")
    private Boolean Enabled;


    @EntityDoc(note = "Pid")
    @TableField("Pid")
    private int Pid;

    @EntityDoc(note = "Mid")
    @TableField("Mid")
    private int Mid;

}