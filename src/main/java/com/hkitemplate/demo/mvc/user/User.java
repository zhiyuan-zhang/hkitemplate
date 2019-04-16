package com.hkitemplate.demo.mvc.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanghao
 * @since 2019-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "乐观锁版本")
    private Integer version;

    @ApiModelProperty(value = "逻辑删除字段")
    private Integer deleted;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "会员等级，1，2，3 默认为0")
    private Integer grade;

    @ApiModelProperty(value = "可转债字段，默认为0")
    private Integer debt;

    @ApiModelProperty(value = "会员生效时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "会员是否到期  1：到期  2：未到期")
    private String expire;


}
