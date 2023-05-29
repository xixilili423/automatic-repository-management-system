package com.controller;

import com.service.OutService;
import com.vo.R;
import com.vo.param.OutParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:  OutController
 * Date: 2023/04/18
 */
/**
@RestController
@RequestMapping("/other")
@CrossOrigin
/
public class OutController {

    @Autowired
    private OutService outService;

    // 出库请求--post保存
    @PostMapping("out")
    public R outStock(@RequestBody OutParam outParam) { return outService.outStock(outParam); }

    // 5.获取出库记录表格 get
    @GetMapping("DBrecordOut")
    public R  getOutTable(@RequestParam(value="token") String token) { return outService.getOutTable(token); }

}
*/