package club.neters.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("TestMuchTableResult")
public class TestMuchTableResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("moduleName")
    private String moduleName;

    @TableField("permName")
    private String permName;

    @TableField("rid")
    private Integer rid;

    @TableField("mid")
    private Integer mid;

    @TableField("pid")
    private Integer pid;


}
