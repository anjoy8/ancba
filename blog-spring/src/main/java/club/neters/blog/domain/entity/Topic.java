package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Topic表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "Topic", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Topic")
public class Topic {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "tLogo")
    @TableField("tLogo")
    private String tLogo;

    @EntityDoc(note = "tName")
    @TableField("tName")
    private String tName;

    @EntityDoc(note = "tDetail")
    @TableField("tDetail")
    private String tDetail;

    @EntityDoc(note = "tAuthor")
    @TableField("tAuthor")
    private String tAuthor;

    @EntityDoc(note = "tSectendDetail")
    @TableField("tSectendDetail")
    private String tSectendDetail;

    @EntityDoc(note = "tIsDelete")
    @TableField("tIsDelete")
    private Boolean tIsDelete;

    @EntityDoc(note = "tRead")
    @TableField("tRead")
    private Integer tRead;

    @EntityDoc(note = "tCommend")
    @TableField("tCommend")
    private Integer tCommend;

    @EntityDoc(note = "tGood")
    @TableField("tGood")
    private Integer tGood;

    @EntityDoc(note = "tCreatetime")
    @TableField("tCreatetime")
    private Date tCreatetime;

    @EntityDoc(note = "tUpdatetime")
    @TableField("tUpdatetime")
    private Date tUpdatetime;

}