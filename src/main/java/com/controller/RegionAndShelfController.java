package com.controller;

import com.annotation.UserLoginToken;
import com.service.RegionAndShelfService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regionAndShelf")
@AllArgsConstructor
public class RegionAndShelfController {
    @Autowired
    private final RegionAndShelfService regionAndShelfService;

    @GetMapping("searchRegion")
    @UserLoginToken
    public R searchRegion(@RequestAttribute(value="id") String id,@RequestBody searchRegionparam params) { return regionAndShelfService.searchRegion(id, params); }

    @GetMapping("searchShelf")
    @UserLoginToken
    public R searchShelf(@RequestAttribute(value="id") String id,@RequestBody searchShelfparam params) { return regionAndShelfService.searchShelf(id, params); }

    @PostMapping("addShelf")
    @UserLoginToken
    public R addShelf(@RequestAttribute(value="id") String id,@RequestBody addShelfparam params) { return regionAndShelfService.addShelf(id, params); }

    @GetMapping("allShelf")
    @UserLoginToken
    public R allShelf(@RequestAttribute(value="id") String id,@RequestBody allShelfparam params) { return regionAndShelfService.allShelf(id, params); }

    @DeleteMapping("deleteShelf")
    @UserLoginToken
    public R deleteShelf(@RequestAttribute(value="id") String id,@RequestBody deleteShelfparam params) { return regionAndShelfService.deleteShelf(id, params); }



}
