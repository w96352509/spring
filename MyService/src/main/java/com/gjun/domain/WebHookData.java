package com.gjun.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "通用WebHook" ,description = "Line WebHook 通用格式")
public class WebHookData{
    public String destination;
    public MyEvent[] events;
}
