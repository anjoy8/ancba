package club.neters.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Module")
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("IsDeleted")
    private Boolean IsDeleted;

    @TableField("ParentId")
    private Integer ParentId;

    @TableField("Name")
    private String Name;

    @TableField("LinkUrl")
    private String LinkUrl;

    @TableField("Area")
    private String Area;

    @TableField("Controller")
    private String Controller;

    @TableField("Action")
    private String Action;

    @TableField("Icon")
    private String Icon;

    @TableField("Code")
    private String Code;

    @TableField("OrderSort")
    private Integer OrderSort;

    @TableField("Description")
    private String Description;

    @TableField("IsMenu")
    private Boolean IsMenu;

    @TableField("Enabled")
    private Boolean Enabled;

    @TableField("CreateId")
    private Integer CreateId;

    @TableField("CreateBy")
    private String CreateBy;

    @TableField("CreateTime")
    private LocalDateTime CreateTime;

    @TableField("ModifyId")
    private Integer ModifyId;

    @TableField("ModifyBy")
    private String ModifyBy;

    @TableField("ModifyTime")
    private LocalDateTime ModifyTime;


}
