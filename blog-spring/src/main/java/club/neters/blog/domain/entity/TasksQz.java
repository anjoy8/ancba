package club.neters.blog.domain.entity;

import club.neters.blog.core.annotation.EntityDoc;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TasksQz表
 *
 * @author laozhang
 * @date 2021/06/12
 */
@EqualsAndHashCode()
@EntityDoc(note = "TasksQz", isClass = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("TasksQz")
public class TasksQz {

    @EntityDoc(note = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @EntityDoc(note = "Name")
    @TableField("Name")
    private String Name;

    @EntityDoc(note = "JobGroup")
    @TableField("JobGroup")
    private String JobGroup;

    @EntityDoc(note = "Cron")
    @TableField("Cron")
    private String Cron;

    @EntityDoc(note = "AssemblyName")
    @TableField("AssemblyName")
    private String AssemblyName;

    @EntityDoc(note = "ClassName")
    @TableField("ClassName")
    private String ClassName;

    @EntityDoc(note = "Remark")
    @TableField("Remark")
    private String Remark;

    @EntityDoc(note = "RunTimes")
    @TableField("RunTimes")
    private Integer RunTimes;

    @EntityDoc(note = "BeginTime")
    @TableField("BeginTime")
    private Date BeginTime;

    @EntityDoc(note = "EndTime")
    @TableField("EndTime")
    private Date EndTime;

    @EntityDoc(note = "TriggerType")
    @TableField("TriggerType")
    private Integer TriggerType;

    @EntityDoc(note = "IntervalSecond")
    @TableField("IntervalSecond")
    private Integer IntervalSecond;

    @EntityDoc(note = "IsStart")
    @TableField("IsStart")
    private Boolean IsStart;

    @EntityDoc(note = "JobParams")
    @TableField("JobParams")
    private String JobParams;

    @EntityDoc(note = "IsDeleted")
    @TableField("IsDeleted")
    private Boolean IsDeleted;

    @EntityDoc(note = "CreateTime")
    @TableField("CreateTime")
    private Date CreateTime;

}