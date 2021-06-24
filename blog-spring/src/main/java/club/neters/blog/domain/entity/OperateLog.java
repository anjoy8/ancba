package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * OperateLog表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "OperateLog", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("OperateLog")


public class OperateLog {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "IsDeleted")
    @TableField("IsDeleted")
    private Boolean IsDeleted;

    @EntityDoc(note = "Area")
    @TableField("Area")
    private String Area;

    @EntityDoc(note = "Controller")
    @TableField("Controller")
    private String Controller;

    @EntityDoc(note = "Action")
    @TableField("Action")
    private String Action;

    @EntityDoc(note = "IPAddress")
    @TableField("IPAddress")
    private String IPAddress;

    @EntityDoc(note = "Description")
    @TableField("Description")
    private String Description;

    @EntityDoc(note = "LogTime")
    @TableField("LogTime")
    private Date LogTime;

    @EntityDoc(note = "LoginName")
    @TableField("LoginName")
    private String LoginName;

    @EntityDoc(note = "UserId")
    @TableField("UserId")
    private Integer UserId;

}