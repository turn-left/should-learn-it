package com.ethen.elastic.controller;

import com.alibaba.fastjson.JSONObject;
import com.ethen.elastic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 创建索引
     *
     * @param params
     * @return
     * @see -> https://zhuanlan.zhihu.com/p/159138736
     */
    @PostMapping("/v1/user/index")
    public Object createUserIndex(@RequestBody JSONObject params) {
        return userService.createUserIndex(params.getString("indexName"));
    }
}
