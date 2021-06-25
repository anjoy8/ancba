package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Advertisement表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "Advertisement", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Advertisement")
public class Advertisement {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "ImgUrl")
    @TableField("ImgUrl")
    private String ImgUrl;

    @EntityDoc(note = "Title")
    @TableField("Title")
    private String Title;

    @EntityDoc(note = "Url")
    @TableField("Url")
    private String Url;

    @EntityDoc(note = "Remark")
    @TableField("Remark")
    private String Remark;

    @EntityDoc(note = "Createdate")
    @TableField("Createdate")
    private Date Createdate;

}

