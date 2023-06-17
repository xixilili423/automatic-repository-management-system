package com;

import com.entity.Inbound;
import com.entity.Outbound;
import com.entity.Shelf;
import com.entity.Warehouseperson;
import com.mapper.InboundMapper;
import com.mapper.ShelfMapper;
import com.mapper.WarehousepersonMapper;
import com.service.OutAndInService;
import com.service.impl.OutAndInServiceImpl;
import com.vo.R;
import com.vo.param.ExamineInParam;
import com.vo.param.ParcelList;
import com.vo.param.SearchInParam;
import com.vo.param.SearchOutParam;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class test {
    @Mock
    private InboundMapper inboundMapper;

    @Mock
    private WarehousepersonMapper warehousepersonMapper;
    @Mock
    private ShelfMapper shelfMapper;
    @InjectMocks
    private OutAndInServiceImpl examineInService;

    @Test
    public void testExamineInInboundNotExist() {
        // 模拟入库单不存在的情况
        when(inboundMapper.selectById(anyLong())).thenReturn(null);
        ExamineInParam param = new ExamineInParam();
        param.setInID("5");
        // 调用被测试的方法
        R result;
        result = examineInService.ExamineIn("123", param);

        // 验证返回结果是否符合预期
        assertEquals("入库单不存在", result.getMessage());
    }

    @Test
    public void testExamineInWarehousepersonNotExist() {
        // 模拟入库单存在但入库交接人不存在的情况
        Inbound inbound = new Inbound();
        inbound.setInboundid(1L);
        when(inboundMapper.selectById(anyLong())).thenReturn(inbound);
        when(warehousepersonMapper.selectOne(any())).thenReturn(null);
        // 调用被测试的方法
        ExamineInParam param = new ExamineInParam();
        param.setInID("1");
        param.setInPeopleName("张三");
        R result = examineInService.ExamineIn("123", param);

        // 验证返回结果是否符合预期
        assertEquals("入库交接人不存在", result.getMessage());
    }

    @Test
    public void testExamineInSuccess() {
        // 模拟入库单存在且入库交接人存在的情况
        Inbound inbound = new Inbound();
        inbound.setInboundid(1L);
        when(inboundMapper.selectById(anyLong())).thenReturn(inbound);
        when(warehousepersonMapper.selectOne(any())).thenReturn(new Warehouseperson());
        when(examineInService.selectAvailableShelves()).thenReturn(new ArrayList<Shelf>());

        // 调用被测试的方法
        ExamineInParam param = new ExamineInParam();
        param.setInID("1");
        param.setInPeopleName("张三");
        param.setInStatus("已入库");
        ParcelList[] parcelLists = new ParcelList[1];
        ParcelList p = new ParcelList();
        p.setParcelID(9);
        parcelLists[0] = p;
        param.setParcelList(parcelLists);
        R result = examineInService.ExamineIn("123", param);

        // 验证返回结果是否符合预期
        assertEquals("No available shelf", result.getMessage());
    }

    @Test
    public void testExamineInNoAvailableShelf() {
        // 模拟入库单存在但没有可用的货架的情况
        Inbound inbound = new Inbound();
        inbound.setInboundid(1L);
        when(inboundMapper.selectById(anyLong())).thenReturn(inbound);
        when(warehousepersonMapper.selectOne(any())).thenReturn(new Warehouseperson());
        when(examineInService.selectAvailableShelves()).thenReturn(new ArrayList<Shelf>());

        // 调用被测试的方法
        ExamineInParam param = new ExamineInParam();
        param.setInID("1");
        param.setInPeopleName("张三");
        param.setInStatus("已入库");
        param.setParcelList(new ParcelList[]{new ParcelList()});
        R result = examineInService.ExamineIn("123", param);
        // 验证返回结果是否符合预期
        assertEquals("No available shelf", result.getMessage());
    }
}