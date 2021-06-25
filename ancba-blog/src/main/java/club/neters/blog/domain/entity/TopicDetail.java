package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TopicDetail表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "TopicDetail", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("TopicDetail")


public class TopicDetail {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "tdLogo")
    @TableField("tdLogo")
    private String tdLogo;

    @EntityDoc(note = "tdName")
    @TableField("tdName")
    private String tdName;

    @EntityDoc(note = "tdContent")
    @TableField("tdContent")
    private String tdContent;

    @EntityDoc(note = "tdDetail")
    @TableField("tdDetail")
    private String tdDetail;

    @EntityDoc(note = "tdSectendDetail")
    @TableField("tdSectendDetail")
    private String tdSectendDetail;

    @EntityDoc(note = "tdIsDelete")
    @TableField("tdIsDelete")
    private Boolean tdIsDelete;

    @EntityDoc(note = "tdRead")
    @TableField("tdRead")
    private Integer tdRead;

    @EntityDoc(note = "tdCommend")
    @TableField("tdCommend")
    private Integer tdCommend;

    @EntityDoc(note = "tdGood")
    @TableField("tdGood")
    private Integer tdGood;

    @EntityDoc(note = "tdCreatetime")
    @TableField("tdCreatetime")
    private Date tdCreatetime;

    @EntityDoc(note = "tdUpdatetime")
    @TableField("tdUpdatetime")
    private Date tdUpdatetime;

    @EntityDoc(note = "tdTop")
    @TableField("tdTop")
    private Integer tdTop;

    @EntityDoc(note = "tdAuthor")
    @TableField("tdAuthor")
    private String tdAuthor;

    @EntityDoc(note = "TopicId")
    @TableField("TopicId")
    private Integer TopicId;

}

