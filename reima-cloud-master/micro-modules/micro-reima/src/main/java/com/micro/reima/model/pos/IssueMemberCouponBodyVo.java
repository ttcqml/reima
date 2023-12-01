package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("IssueMemberCouponBodyVo")
@JsonInclude(value = Include.ALWAYS)
public class IssueMemberCouponBodyVo implements Serializable {
    private static final long serialVersionUID = 8486538129451102198L;
    @ApiModelProperty("核销结果：true 或 false")
    private boolean success;

    public IssueMemberCouponBodyVo() {

    }

    public IssueMemberCouponBodyVo(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
