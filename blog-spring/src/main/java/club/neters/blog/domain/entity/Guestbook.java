package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Guestbook表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "Guestbook", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Guestbook")
public class Guestbook {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "blogId")
    @TableField("blogId")
    private Integer blogId;

    @EntityDoc(note = "createdate")
    @TableField("createdate")
    private Date createdate;

    @EntityDoc(note = "username")
    @TableField("username")
    private String username;

    @EntityDoc(note = "phone")
    @TableField("phone")
    private String phone;

    @EntityDoc(note = "QQ")
    @TableField("QQ")
    private String QQ;

    @EntityDoc(note = "body")
    @TableField("body")
    private String body;

    @EntityDoc(note = "ip")
    @TableField("ip")
    private String ip;

    @EntityDoc(note = "isshow")
    @TableField("isshow")
    private Boolean isshow;
}
