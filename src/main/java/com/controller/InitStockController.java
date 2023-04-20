package com.controller;

import com.service.InitStockService;
import com.vo.R;
import com.vo.param.InitStockParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:  InitStockController
 * Date: 2023/04/18
 * Describution: 该类用于充当 初始化仓库 的Controller层
 */

@RestController
@RequestMapping("/user")
@CrossOrigin
public class InitStockController {
    @Autowired
    private InitStockService initStockService;

    // 仓库初始化
    @PostMapping("/initStock")
    public R initStock(@RequestBody InitStockParam initStockParam) {
        System.out.println(initStockParam.toString());
        return initStockService.initStock(initStockParam);
    }

    // 获取旧用户仓库数据
    @GetMapping("getOldInitStock")
    public R getOldInitStock(@RequestParam(value="token") String token) { return initStockService.getOldInitStock(token); }

}
