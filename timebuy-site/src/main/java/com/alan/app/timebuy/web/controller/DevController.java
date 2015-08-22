package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.entity.ClientType;
import com.alan.app.timebuy.entity.ClientVersion;
import com.alan.app.timebuy.entity.DeviceInfo;
import com.alan.app.timebuy.service.SidService;
import com.alan.app.timebuy.web.vo.Request;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 设备控制接口
 * Created by zhangbinalan on 15/8/22.
 */
@Controller
@RequestMapping(value = "/dev")
public class DevController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DevController.class);

    @Resource(name="sidServiceImpl")
    private SidService sidService;

    /**
     * APP启动后就请求该接口。
     * 产生设备对应的SID，如果SID已经有登录绑定信息，则延长有效期
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sid")
    @ResponseBody
    public String refreshSid(HttpServletRequest httpRequest) throws Exception {
        Request request = new Request();
        request.setHttpRequest(httpRequest);
        String did = request.getString("did");
        String client_version = request.getString("client_version");
        int client_type = request.getInt("client_type");

        DeviceInfo deviceInfo = new DeviceInfo();
        ClientType clientType = ClientType.getInstance(client_type);
        deviceInfo.setClientType(clientType);
        ClientVersion version = ClientVersion.getInstance(client_version);
        deviceInfo.setClientVersion(version);
        deviceInfo.setDeviceId(did);

        //产生设备对应的SID
        String sid = sidService.generateSid(deviceInfo);

        //刷新SID的用户登录信息的绑定有效期
        sidService.refreshSid(deviceInfo, sid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", sid);

        return createSuccessResponse(jsonObject);
    }
}