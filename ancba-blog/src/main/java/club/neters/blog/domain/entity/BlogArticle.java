package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * BlogArticle表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "BlogArticle", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("BlogArticle")
public class BlogArticle {

    @EntityDoc(note = "bID")
    @TableId(value = "bID", type = IdType.AUTO)
    private Integer bID;

    @EntityDoc(note = "bsubmitter")
    @TableField("bsubmitter")
    private String bsubmitter;

    @EntityDoc(note = "btitle")
    @TableField("btitle")
    private String btitle;

    @EntityDoc(note = "bcategory")
    @TableField("bcategory")
    private String bcategory;

    @EntityDoc(note = "bcontent")
    @TableField("bcontent")
    private String bcontent;

    @EntityDoc(note = "btraffic")
    @TableField("btraffic")
    private Integer btraffic;

    @EntityDoc(note = "bcommentNum")
    @TableField("bcommentNum")
    private Integer bcommentNum;

    @EntityDoc(note = "bUpdateTime")
    @TableField("bUpdateTime")
    private Date bUpdateTime;

    @EntityDoc(note = "bCreateTime")
    @TableField("bCreateTime")
    private Date bCreateTime;

    @EntityDoc(note = "bRemark")
    @TableField("bRemark")
    private String bRemark;

    @EntityDoc(note = "IsDeleted")
    @TableField("IsDeleted")
    private Boolean IsDeleted;

}