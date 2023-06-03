package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.entity.Customer;
import com.entity.Outboundperson;
import com.entity.Transactionrecord;
import com.entity.Warehouseperson;
import com.mapper.CustomerMapper;
import com.mapper.OutboundpersonMapper;
import com.mapper.TransactionRecordMapper;
import com.mapper.WarehousepersonMapper;
import com.service.PeopleService;
import com.vo.R;
import com.vo.param.checkCustomInformationParam;
import com.vo.param.checkCustomTransactionParam;
import com.vo.param.checkFetchOutPeopleInformationParam;
import com.vo.param.checkInBoundPeopleInformationParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public abstract class PeopleServiceImpl implements PeopleService {
    @Autowired
    private WarehousepersonMapper warehousepersonMapper;
    private OutboundpersonMapper outboundpersonMapper;
    private CustomerMapper customerMapper;
    private TransactionRecordMapper transactionRecordMapper;


    //入库人信息查询
    public R checkInBoundPeorsonInformation(String id, checkInBoundPeopleInformationParam params){
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Warehouseperson> queryWrapper = new QueryWrapper<>();

        // 判断属性是否不为空，并根据不同属性设置查询条件
        if (StringUtils.isNotBlank(params.getAddress())) {
            queryWrapper.like("address", params.getAddress());
        }
        if (StringUtils.isNotBlank(params.getEmail())) {
            queryWrapper.like("email", params.getEmail());
        }
        if (StringUtils.isNotBlank(params.getInBoundPersonId())) {
            queryWrapper.eq("warehousepersonid ", params.getInBoundPersonId());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            queryWrapper.like("name", params.getName());
        }
        if (StringUtils.isNotBlank(params.getPhone())) {
            queryWrapper.like("contactnumber ", params.getPhone());
        }
        if (StringUtils.isNotBlank(params.getUserName())) {
            queryWrapper.like("username", params.getUserName());
        }

        List<Warehouseperson> warehousepersons = warehousepersonMapper.selectList(queryWrapper);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (Warehouseperson warehouseperson : warehousepersons) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("inBoundPersonId", warehouseperson.getCustomerid());
            personMap.put("userName", warehouseperson.getUsername());
            personMap.put("name", warehouseperson.getName());
            personMap.put("phone", warehouseperson.getContactnumber());
            personMap.put("address", warehouseperson.getAddress());
            personMap.put("email", warehouseperson.getEmail());
            personMap.put("remark", warehouseperson.getRemark());

            // 添加其他属性到 personMap

            PeopleList.add(personMap);
        }

        r.data("status_code",true);

        r.data("inBoundPeopleList", PeopleList);

        return r;
    }

    public R checkFetchOutPeopleInformation(String id, checkFetchOutPeopleInformationParam params){
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Outboundperson> queryWrapper = new QueryWrapper<>();

        // 判断属性是否不为空，并根据不同属性设置查询条件
        if (StringUtils.isNotBlank(params.getAddress())) {
            queryWrapper.like("address", params.getAddress());
        }
        if (StringUtils.isNotBlank(params.getEmail())) {
            queryWrapper.like("email", params.getEmail());
        }
        if (StringUtils.isNotBlank(params.getOutBoundPresonId())) {
            queryWrapper.eq("outboundpersonid", params.getOutBoundPresonId());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            queryWrapper.like("name", params.getName());
        }
        if (StringUtils.isNotBlank(params.getPhone())) {
            queryWrapper.like("contactnumber", params.getPhone());
        }
        if (StringUtils.isNotBlank(params.getUserName())) {
            queryWrapper.like("username", params.getUserName());
        }

        List<Outboundperson> outboundpersons = outboundpersonMapper.selectList(queryWrapper);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (Outboundperson outboundperson : outboundpersons) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("outBoundPersonId", outboundperson.getCustomerid());
            personMap.put("userName", outboundperson.getUsername());
            personMap.put("name", outboundperson.getName());
            personMap.put("phone", outboundperson.getContactnumber());
            personMap.put("address", outboundperson.getAddress());
            personMap.put("email", outboundperson.getEmail());
            personMap.put("remark", outboundperson.getRemark());

            // 添加其他属性到 personMap

            PeopleList.add(personMap);
        }

        r.data("status_code",true);

        r.data("outBoundPeopleList", PeopleList);

        return r;
    }

    public R checkCustomInformation(String id, checkCustomInformationParam params) {
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();

        // 判断属性是否不为空，并根据不同属性设置查询条件
        if (StringUtils.isNotBlank(params.getAddress())) {
            queryWrapper.like("address", params.getAddress());
        }
        if (StringUtils.isNotBlank(params.getEmail())) {
            queryWrapper.like("email", params.getEmail());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            queryWrapper.like("name", params.getName());
        }
        if (StringUtils.isNotBlank(params.getPhone())) {
            queryWrapper.like("contactnumber", params.getPhone());
        }
        if (StringUtils.isNotBlank(params.getCompanyName())) {
            queryWrapper.like("companyname", params.getCompanyName());
        }
        if (StringUtils.isNotBlank(params.getCustomerId())) {
            queryWrapper.like("customerid", params.getCustomerId());
        }
        if (StringUtils.isNotBlank(params.getUsername())) {
            queryWrapper.like("username", params.getUsername());
        }


        List<Customer> customers = customerMapper.selectList(queryWrapper);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (Customer customer : customers) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("customerId", customer.getCustomerid());
            personMap.put("companyName", customer.getCompanyname());
            personMap.put("payableAmount", customer.getPayableamount());
            personMap.put("contactPersonName", customer.getContactpersonname());
            personMap.put("phone", customer.getContactnumber());
            personMap.put("address", customer.getAddress());
            personMap.put("email", customer.getEmail());
            personMap.put("remark", customer.getRemark());

            // 添加其他属性到 personMap

            PeopleList.add(personMap);
        }

        r.data("status_code",true);

        r.data("customList", PeopleList);

        return r;
    }

    public R checkCustomTransaction(String id, checkCustomTransactionParam params) {
        R r = new R();
        r.data("status_code",false);

        // 查询transactionrecord表中的信息
        List<Transactionrecord> transactions = transactionRecordMapper.selectList(null);

        List<Map<String, Object>> transactionList = new ArrayList<>();
        for (Transactionrecord transaction : transactions) {
            if (transaction.getTransactionid () == Long.parseLong(params.getCustomerId()) && transaction.getUsername().equals(params.getUserName())) {
                Map<String, Object> transactionMap = new HashMap<>();
                transactionMap.put("transactionId", transaction.getTransactionid());
                transactionMap.put("transactionAmount", transaction.getTransactionamount());
                transactionMap.put("time", transaction.getTransactiontime());

                // 查询customer表中的信息
                Customer customer = customerMapper.selectById(params.getCustomerId());
                if (customer != null) {
                    transactionMap.put("contactName", customer.getContactpersonname());
                    transactionMap.put("phone", customer.getContactnumber());
                    transactionMap.put("bankName", customer.getBankname());
                    transactionMap.put("bankCardNum", customer.getBankcardnumber());
                }

                transactionList.add(transactionMap);
            }
        }

        r.data("transactionList", transactionList);
        r.data("status_code", true);

        return r;
    }


    public R delCustomInformation(int customerId, String userName) {
        R r = new R();
        r.data("status_code",false);

        // 查询customer表中的信息
        Customer customer = customerMapper.selectById(customerId);
        if (customer != null && customer.getUsername().equals(userName)) {
            // 执行删除操作
            int rowsAffected = customerMapper.deleteById(customerId);
            if (rowsAffected > 0) {
                r.data("status_code", true);
            }
        }

        return r;
    }

    public R delFetchInPeopleInformation(int inBoundPersonId) {
        R r = new R();
        r.data("status_code",false);

        // 查询warehouseperson表中的信息
        Warehouseperson warehouseperson = warehousepersonMapper.selectById(inBoundPersonId);

        if (warehouseperson != null) {
            // 执行删除操作
            int rowsAffected = warehousepersonMapper.deleteById(inBoundPersonId);
            if (rowsAffected > 0) {
                r.data("status_code", true);
            }
        }

        return r;
    }

    public R delFetchOutPeopleInformation(int outBoundPresonId) {
        R r = new R();
        r.data("status_code", false);

        // 查询 warehouseperson 表中的信息
        Warehouseperson warehouseperson = warehousepersonMapper.selectById(outBoundPresonId);

        if (warehouseperson != null) {
            // 执行删除操作
            int rowsAffected = warehousepersonMapper.deleteById(outBoundPresonId);
            if (rowsAffected > 0) {
                r.data("status_code", true);
            }
        }

        return r;
    }

    public R incAccountsPayment(String customerId, String incAccounts, String notes) {
        R r = new R();
        r.data("status_code", false);

        // 查询customer表中的信息
        Customer customer = customerMapper.selectById(customerId);
        if (customer != null) {
            // 更新payableamount
            double payableAmount = customer.getPayableamount() + Double.parseDouble(incAccounts);
            customer.setPayableamount(payableAmount);
            customerMapper.updateById(customer);

            // 向transactionrecord表中插入记录
            Transactionrecord transaction = new Transactionrecord();
            transaction.setCustomerid(customer.getCustomerid());
            transaction.setUsername(customer.getUsername());
            transaction.setTransactionamount(Double.parseDouble(incAccounts));
            transaction.setTransactionremark(notes);

            // 设置当前系统时间为transactiontime
            Date currentDate = new Date();
            transaction.setTransactiontime(new Timestamp(currentDate.getTime()));
            transactionRecordMapper.insert(transaction);

            r.data("status_code", true);
            r.data("payableAmount", Double.toString(payableAmount));

        }

        return r;
    }

    public R balanceAccountsPayment(String customerId, String balAccounts, String notes) {
        R r = new R();
        r.data("status_code", false);

        // 查询customer表中的信息
        Customer customer = customerMapper.selectById(customerId);
        if (customer != null) {
            // 更新payableamount
            double payableAmount = customer.getPayableamount() - Double.parseDouble(balAccounts);
            customer.setPayableamount(payableAmount);
            customerMapper.updateById(customer);

            double recordAmount = 0 - Double.parseDouble(balAccounts);

            // 向transactionrecord表中插入记录
            Transactionrecord transaction = new Transactionrecord();
            transaction.setCustomerid(customer.getCustomerid());
            transaction.setUsername(customer.getUsername());
            transaction.setTransactionamount(Double.parseDouble(Double.toString(recordAmount)));//表中记录负数值
            transaction.setTransactionremark(notes);

            // 设置当前系统时间为transactiontime
            Date currentDate = new Date();
            transaction.setTransactiontime(new Timestamp(currentDate.getTime()));
            transactionRecordMapper.insert(transaction);

            r.data("status_code", true);
            r.data("payableAmount", Double.toString(payableAmount));

        }

        return r;
    }






}
