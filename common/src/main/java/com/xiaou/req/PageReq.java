package com.xiaou.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageReq {
    @NotNull(message = "【页码】不能为空")
    private Integer page;

    @NotNull(message = "【每页条数】不能为空")
    @Max(value=100,message = "【每页条数】不能超过100")
    private Integer size;
}
