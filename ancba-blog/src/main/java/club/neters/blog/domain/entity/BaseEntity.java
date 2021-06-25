package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author laozhang
 * @date 2021/06/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "创建人Id")
    @TableField("CreateId")
    private Integer CreateId;

    @EntityDoc(note = "创建人名称")
    @TableField("CreateBy")
    private String CreateBy;

    @EntityDoc(note = "创建时间")
    @TableField(value = "CreateTime", fill = FieldFill.INSERT)
    protected Date CreateTime;

    @EntityDoc(note = "修改人Id")
    @TableField("ModifyId")
    private Integer ModifyId;

    @EntityDoc(note = "修改人名称")
    @TableField("ModifyBy")
    private String ModifyBy;

    @EntityDoc(note = "修改时间")
    @TableField(value = "ModifyTime", fill = FieldFill.INSERT)
    protected Date ModifyTime;

    @EntityDoc(note = "是否删除")
    @TableField("tdIsDelete")
    private Boolean IsDeleted;

}
