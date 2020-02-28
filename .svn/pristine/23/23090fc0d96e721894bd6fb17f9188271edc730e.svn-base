package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysLog对象", description = "操作日志")
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 操作用户
     */
	@ApiModelProperty(value = "操作用户", name = "userId")
	private Integer userId;
    /**
     * 日志类型
     */
	@ApiModelProperty(value = "日志类型", name = "type")
	private Integer type;
    /**
     * 操作IP
     */
	@ApiModelProperty(value = "操作IP", name = "ip")
	private String ip;
    /**
     * 操作详情
     */
	@ApiModelProperty(value = "操作详情", name = "info")
	private String info;
    /**
     * 操作时间
     */
	@ApiModelProperty(value = "操作时间", name = "createTime")
	private Date createTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
