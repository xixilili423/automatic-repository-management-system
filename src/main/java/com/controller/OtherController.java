package com.controller;

import com.service.OtherService;
import com.vo.R;
import com.vo.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:  OtherController
 * Date: 2023/04/11
 */

@RestController
@RequestMapping("/other")
@CrossOrigin

public class OtherController {
    @Autowired
    private OtherService otherService;

    // 1.入库请求--post保存
    @PostMapping("enter")
    public R enterStock(@RequestBody EnterParam enterParam) { return otherService.enterStock(enterParam); }

    // 2.出库请求--post保存
    @PostMapping("out")
    public R outStock(@RequestBody OutParam outParam) { return otherService.outStock(outParam); }

    // 3.根据id查询包裹请求
    @PostMapping("checkParcel")
    public R checkParcel(@RequestBody CheckParcelParam checkParcelParam) { return otherService.checkParcel(checkParcelParam); }

    // 4.获取入库记录表格 get
    @GetMapping("DBrecordIn/{token}")
    public R getInTable(@PathVariable("token") String token) { return otherService.getInTable(token); }

    // 5.获取出库记录表格 get
    @GetMapping("DBrecordOut/{token}")
    public R  getOutTable(@PathVariable("token") String token) { return otherService.getOutTable(token); }

}
