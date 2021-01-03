package com.fisher.mybatis.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author shefenfei
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Student对象", description="")
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    @NotEmpty(message = "用户名不能为空")
    private String name;

    @TableField("gender")
    private String gender;

    @TableField("grade")
    private Integer grade;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("birthday")
    private Date birthday;

    @Size.List(@Size(max = 2, message = "最大支持2个"))
    private List<String> objs;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
