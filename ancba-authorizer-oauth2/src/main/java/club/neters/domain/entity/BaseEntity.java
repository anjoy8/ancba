package club.neters.domain.entity;

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

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("CreateId")
    private Integer CreateId;

    @TableField("CreateBy")
    private String CreateBy;

    @TableField(value = "CreateTime", fill = FieldFill.INSERT)
    protected Date CreateTime;

    @TableField("ModifyId")
    private Integer ModifyId;

    @TableField("ModifyBy")
    private String ModifyBy;

    @TableField(value = "ModifyTime", fill = FieldFill.INSERT)
    protected Date ModifyTime;

    @TableField("tdIsDelete")
    private Boolean IsDeleted;

}
