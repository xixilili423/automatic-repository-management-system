package com.controller;

import com.service.CheckParcelService;
import com.vo.R;
import com.vo.param.CheckParcelParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:  CheckParcelController
 * Date: 2023/04/18
 */

@RestController
@RequestMapping("/other")
@CrossOrigin

public class CheckParcelController {

    @Autowired
    private CheckParcelService checkParcelService;

    // 3.根据id查询包裹请求
    @PostMapping("checkParcel")
    public R checkParcel(@RequestBody CheckParcelParam checkParcelParam) { return checkParcelService.checkParcel(checkParcelParam); }

}
