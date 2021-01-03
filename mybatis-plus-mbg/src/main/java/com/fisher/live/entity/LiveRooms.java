package com.fisher.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author shefenfei
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LiveRooms对象", description="")
public class LiveRooms extends Model<LiveRooms> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "直播间号")
    @TableField("live_room_id")
    private String liveRoomId;

    @ApiModelProperty(value = "直播间名称")
    @TableField("live_room_name")
    private String liveRoomName;

    @ApiModelProperty(value = "店铺直播主图")
    @TableField("cover_img")
    private String coverImg;

    @ApiModelProperty(value = "门店id")
    @TableField("store_id")
    private String storeId;

    @ApiModelProperty(value = "门店名称")
    @TableField("store_name")
    private String storeName;

    @ApiModelProperty(value = "广场id")
    @TableField("plaza_code")
    private String plazaCode;

    @ApiModelProperty(value = "广场名称")
    @TableField("plaza_name")
    private String plazaName;

    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "是否下架")
    @TableField("is_offline")
    private Boolean isOffline;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "创建者id")
    @TableField("create_id")
    private String createId;

    @ApiModelProperty(value = "创建者用户名")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty(value = "最后更新者id")
    @TableField("last_update_id")
    private String lastUpdateId;

    @ApiModelProperty(value = "最后更新者用户名")
    @TableField("last_update_name")
    private String lastUpdateName;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "乐观锁版本号")
    @TableField("version")
    private Integer version;

    @ApiModelProperty(value = "当前登录人组织")
    @TableField("orgcode")
    private String orgcode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
