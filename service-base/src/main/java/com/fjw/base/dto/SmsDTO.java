package com.fjw.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fjw
 * @date 2021-08-08 17:50
 */
@Data
@ApiModel(description = "短信")
public class SmsDTO {
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "消息内容")
    private String message;
}
