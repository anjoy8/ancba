package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Modules表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode(callSuper = false)
@EntityDoc(note = "Modules", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Modules")
public class Modules extends BaseEntity {


    @EntityDoc(note = "Name")
    @TableField("Name")
    private String Name;

    @EntityDoc(note = "LinkUrl")
    @TableField("LinkUrl")
    private String LinkUrl;

    @EntityDoc(note = "Area")
    @TableField("Area")
    private String Area;

    @EntityDoc(note = "Controller")
    @TableField("Controller")
    private String Controller;

    @EntityDoc(note = "Action")
    @TableField("Action")
    private String Action;

    @EntityDoc(note = "Icon")
    @TableField("Icon")
    private String Icon;

    @EntityDoc(note = "Code")
    @TableField("Code")
    private String Code;

    @EntityDoc(note = "OrderSort")
    @TableField("OrderSort")
    private Integer OrderSort;

    @EntityDoc(note = "Description")
    @TableField("Description")
    private String Description;

    @EntityDoc(note = "IsMenu")
    @TableField("IsMenu")
    private Boolean IsMenu;

    @EntityDoc(note = "Enabled")
    @TableField("Enabled")
    private Boolean Enabled;


    @EntityDoc(note = "ParentId")
    @TableField("ParentId")
    private Integer ParentId;
}