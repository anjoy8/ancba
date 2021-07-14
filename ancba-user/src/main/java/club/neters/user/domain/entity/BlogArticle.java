package club.neters.user.domain.entity;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogArticle {

    private Integer bID;

    private String bsubmitter;

    private String btitle;

    private String bcategory;

    private String bcontent;

    private Integer btraffic;

    private Integer bcommentNum;

    private Date bUpdateTime;

    private Date bCreateTime;

    private String bRemark;

    private Boolean IsDeleted;

}