package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * PasswordLib表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "PasswordLib", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("PasswordLib")
public class PasswordLib{

    @EntityDoc(note = "PLID")
    @TableId(value = "PLID", type = IdType.AUTO)
    private Integer PLID ;

    @EntityDoc(note = "IsDeleted")
    @TableField("IsDeleted")
    private Boolean IsDeleted ;

    @EntityDoc(note = "plURL")
    @TableField("plURL")
    private String plURL ;

    @EntityDoc(note = "plPWD")
    @TableField("plPWD")
    private String plPWD ;

    @EntityDoc(note = "plAccountName")
    @TableField("plAccountName")
    private String plAccountName ;

    @EntityDoc(note = "plStatus")
    @TableField("plStatus")
    private Integer plStatus ;

    @EntityDoc(note = "plErrorCount")
    @TableField("plErrorCount")
    private Integer plErrorCount ;

    @EntityDoc(note = "plHintPwd")
    @TableField("plHintPwd")
    private String plHintPwd ;

    @EntityDoc(note = "plHintquestion")
    @TableField("plHintquestion")
    private String plHintquestion ;

    @EntityDoc(note = "plCreateTime")
    @TableField("plCreateTime")
    private Date plCreateTime ;

    @EntityDoc(note = "plUpdateTime")
    @TableField("plUpdateTime")
    private Date plUpdateTime ;

    @EntityDoc(note = "plLastErrTime")
    @TableField("plLastErrTime")
    private Date plLastErrTime ;

    @EntityDoc(note = "test")
    @TableField("test")
    private String test ;

}

