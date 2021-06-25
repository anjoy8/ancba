package club.neters.user.domain.entity;

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
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sysUserInfo")
public class SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uID", type = IdType.AUTO)
    private Integer uID;

    @TableField("uLoginName")
    private String uLoginName;

    @TableField("uLoginPWD")
    private String uLoginPWD;

    @TableField("uRealName")
    private String uRealName;

    @TableField("uStatus")
    private Integer uStatus;

    @TableField("uRemark")
    private String uRemark;

    @TableField("uCreateTime")
    private LocalDateTime uCreateTime;

    @TableField("uUpdateTime")
    private LocalDateTime uUpdateTime;

    @TableField("uLastErrTime")
    private LocalDateTime uLastErrTime;

    @TableField("uErrorCount")
    private Integer uErrorCount;

    @TableField("name")
    private String name;

    @TableField("sex")
    private Integer sex;

    @TableField("age")
    private Integer age;

    @TableField("birth")
    private LocalDateTime birth;

    @TableField("addr")
    private String addr;

    @TableField("tdIsDelete")
    private Boolean tdIsDelete;


}
