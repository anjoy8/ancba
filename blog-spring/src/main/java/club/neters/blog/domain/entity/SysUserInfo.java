package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "系统用户", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sysUserInfo")
public class SysUserInfo {

    @EntityDoc(note = "id")
    @TableId(value = "uID", type = IdType.AUTO)
    private Integer uID;

    @EntityDoc(note = "登录名")
    @TableField("uLoginName")
    private String uLoginName;

    @EntityDoc(note = "密码")
    @TableField("uLoginPWD")
    private String uLoginPWD;

    @EntityDoc(note = "真实名")
    @TableField("uRealName")
    private String uRealName;

    @EntityDoc(note = "状态")
    @TableField("uStatus")
    private Integer uStatus;

    @EntityDoc(note = "备注")
    @TableField("uRemark")
    private String uRemark;

    @EntityDoc(note = "创建时间")
    @TableField(value = "uCreateTime",fill = FieldFill.INSERT)
    protected Date uCreateTime;

    @EntityDoc(note = "更新时间")
    @TableField(value = "uUpdateTime",fill = FieldFill.INSERT)
    protected Date uUpdateTime;

    @EntityDoc(note = "报错时间")
    @TableField(value = "uLastErrTime",fill = FieldFill.INSERT)
    protected Date uLastErrTime;

    @EntityDoc(note = "错误次数")
    @TableField("uErrorCount")
    private Integer uErrorCount;

    @EntityDoc(note = "名字")
    @TableField("name")
    private String name;


    @EntityDoc(note = "性别")
    @TableField("sex")
    private Integer sex;

    @EntityDoc(note = "年龄")
    @TableField("age")
    private Integer age;

    @EntityDoc(note = "生日")
    @TableField("birth")
    protected Date birth;

    @EntityDoc(note = "地址")
    @TableField("addr")
    private String addr;

    @EntityDoc(note = "是否删除")
    @TableField("tdIsDelete")
    private Boolean tdIsDelete;

}
