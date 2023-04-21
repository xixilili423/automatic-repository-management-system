package com.controller;

import com.service.EnterService;
import com.vo.R;
import com.vo.param.CheckParcelParam;
import com.vo.param.EnterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: EnterController
 * Date: 2023/04/18
 */

@RestController
@RequestMapping("/other")
@CrossOrigin
public class EnterController {

    @Autowired
    private EnterService enterService;

    // 入库请求--post保存
    @PostMapping("enter")
    public R enterStock(@RequestBody EnterParam enterParam) { return enterService.enterStock(enterParam); }

    // 获取入库记录表格 get
    @GetMapping("DBrecordIn")
    public R getInTable(@RequestParam(value="token") String token) { return enterService.getInTable(token); }

    // 根据id查询包裹请求
    @PostMapping("checkParcel")
    public R checkParcel(@RequestBody CheckParcelParam checkParcelParam) { return enterService.checkParcel(checkParcelParam); }
//

}
