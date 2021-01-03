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
@ApiModel(value="LiveGoods对象", description="")
public class LiveGoods extends Model<LiveGoods> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "实物商品id")
    @TableField("spu_code")
    private String spuCode;

    @ApiModelProperty(value = "实物商品数据")
    @TableField("product_info")
    private String productInfo;

    @ApiModelProperty(value = "商品图片")
    @TableField("media_id")
    private String mediaId;

    @ApiModelProperty(value = "多媒体素材失效时间")
    @TableField("media_expire_time")
    private Date mediaExpireTime;

    @ApiModelProperty(value = "标题名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "价格类型")
    @TableField("price_type")
    private Boolean priceType;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private Integer price;

    @ApiModelProperty(value = "价格2")
    @TableField("price2")
    private Integer price2;

    @ApiModelProperty(value = "小程序跳转地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "直播商品id")
    @TableField("live_goods_id")
    private String liveGoodsId;

    @ApiModelProperty(value = "直播间")
    @TableField("live_room_ids")
    private String liveRoomIds;

    @ApiModelProperty(value = "审核单id")
    @TableField("live_audit_id")
    private String liveAuditId;

    @ApiModelProperty(value = "直播商品审核状态 (0:未审核) (1:审核中) (2:审核通过) (3:审核失败)")
    @TableField("live_audit_status")
    private Boolean liveAuditStatus;

    @ApiModelProperty(value = "是否下架")
    @TableField("live_is_offline")
    private Integer liveIsOffline;

    @ApiModelProperty(value = "直播间主图")
    @TableField("live_cover_img_url")
    private String liveCoverImgUrl;

    @ApiModelProperty(value = "最后错误code")
    @TableField("live_last_code")
    private String liveLastCode;

    @ApiModelProperty(value = "最后返回错误")
    @TableField("live_last_message")
    private String liveLastMessage;

    @ApiModelProperty(value = "商品同步状态 (0: 待提交) (5: 检查失败) (10: 同步完成) (12:同步失败) (13:已撤销)")
    @TableField("goods_sync_status")
    private Integer goodsSyncStatus;

    @ApiModelProperty(value = "租户id")
    @TableField("tenant_id")
    private String tenantId;

    @ApiModelProperty(value = "创建者完整组织")
    @TableField("org_code")
    private String orgCode;

    @ApiModelProperty(value = "所属广场")
    @TableField("plaza_code")
    private String plazaCode;

    @ApiModelProperty(value = "所属门店")
    @TableField("store_id")
    private String storeId;

    @ApiModelProperty(value = "扩展备注信息")
    @TableField("remark")
    private String remark;

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

    @ApiModelProperty(value = "状态  0:  正常  1 已删除 ")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "乐观锁版本号")
    @TableField("version")
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
