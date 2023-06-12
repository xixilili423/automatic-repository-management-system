package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.entity.*;
import com.mapper.*;
import com.service.PeopleService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private WarehousepersonMapper warehousepersonMapper;
    private OutboundpersonMapper outboundpersonMapper;
    private CustomerMapper customerMapper;
    private TransactionRecordMapper transactionRecordMapper;
    private UserMapper userMapper;


    //入库人信息查询
    public R checkInBoundPeopleInformation(String id, checkInBoundPeopleInformationParam params){
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Warehouseperson> queryWrapper = new QueryWrapper<>();

        System.out.println(params.getAddress());
        // 判断属性不为空则设置相应查询条件
        if (StringUtils.isNotBlank(params.getInBoundPersonId())) {
            queryWrapper.eq("warehousepersonid", params.getInBoundPersonId().replaceAll("[^0-9]", ""));
        }

        if (StringUtils.isNotBlank(params.getAddress())) {
            System.out.println("HHHHHH");
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
        if (StringUtils.isNotBlank(params.getAddress())) {
            queryWrapper.like("username", params.getUserName());
        }

        List<Warehouseperson> warehousepersons = warehousepersonMapper.selectList(queryWrapper);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (Warehouseperson warehouseperson : warehousepersons) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("inBoundPersonId", warehouseperson.getWarehousepersonid());
            personMap.put("userName", warehouseperson.getUsername());
            personMap.put("name", warehouseperson.getName());
            personMap.put("phone", warehouseperson.getContactnumber());
            personMap.put("address", warehouseperson.getAddress());
            personMap.put("email", warehouseperson.getEmail());
            personMap.put("remark", warehouseperson.getRemark());

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
            queryWrapper.like("outboundpersonid", params.getOutBoundPresonId().replaceAll("[^0-9]", ""));
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
            personMap.put("outBoundPersonId", outboundperson.getOutboundpersonid());
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
        if (StringUtils.isNotBlank(params.getCustomId())) {
            queryWrapper.like("customerid", params.getCustomId().replaceAll("[^0-9]", ""));
        }
        if (StringUtils.isNotBlank(params.getUserName())) {
            queryWrapper.like("username", params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            queryWrapper.like("name", params.getName());
        }


        List<Customer> customers = customerMapper.selectList(queryWrapper);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (Customer customer : customers) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("customId", customer.getCustomerid());
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
            if (transaction.getCustomerid () == Long.parseLong(params.getCustomId().replaceAll("[^0-9]", ""))) {
                Map<String, Object> transactionMap = new HashMap<>();
                transactionMap.put("transactionId", transaction.getTransactionid());
                transactionMap.put("transactionAmount", transaction.getTransactionamount());
                transactionMap.put("time", transaction.getTransactiontime());
                transactionMap.put("remark",transaction.getTransactionremark());

                // 查询customer表中的信息
                LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.like(Customer::getCustomerid, params.getCustomId().replaceAll("[^0-9]", ""));
                List<Customer> customers = customerMapper.selectList(queryWrapper);
                System.out.println(customers.size());

                if (customers.size() > 0) {
                    transactionMap.put("contactName", customers.get(0).getContactpersonname());
                    transactionMap.put("phone", customers.get(0).getContactnumber());
                    transactionMap.put("bankName", customers.get(0).getBankname());
                    transactionMap.put("bankCardNum", customers.get(0).getBankcardnumber());
                }

                transactionList.add(transactionMap);
            }
        }

        r.data("transactionList", transactionList);
        r.data("status_code", true);

        System.out.println("获取用户流水信息获取完毕");
        System.out.println("transactionList: "+ transactionList);
        return r;
    }

    public R delCustomInformation(String id, String customId) {
        R r = new R();
        r.data("status_code", false);

        System.out.println(customId);

        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Customer::getCustomerid, customId.replaceAll("[^0-9]", ""));
        int rowsAffected = customerMapper.delete(queryWrapper);

        if (rowsAffected > 0) {
            r.data("status_code", true);
        }
        return r;
    }

    public R delFetchInPeopleInformation(String id, String inBoundPersonId) {
        R r = new R();
        r.data("status_code",false);

        // 查询warehouseperson表中的信息
        LambdaQueryWrapper<Warehouseperson> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Warehouseperson::getWarehousepersonid, inBoundPersonId.replaceAll("[^0-9]", ""));
        int rowsAffected = warehousepersonMapper.delete(queryWrapper);

        if (rowsAffected > 0) {
            r.data("status_code", true);
        }
        return r;
    }

    public R delFetchOutPeopleInformation(String id, String outBoundPresonId) {
        R r = new R();
        r.data("status_code", false);

        // 查询outboundperson表中的信息

        LambdaQueryWrapper<Outboundperson> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Outboundperson::getOutboundpersonid, outBoundPresonId.replaceAll("[^0-9]", ""));
        int rowsAffected = outboundpersonMapper.delete(queryWrapper);

        if (rowsAffected > 0) {
            r.data("status_code", true);
        }

        return r;
    }

    public R incAccountsPayment(String id, incAccountsPaymentparam params) {
        R r = new R();
        r.data("status_code", false);

        // 查询customer表中的信息
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("customerid", params.getCustomId());
        List<Customer> customers = customerMapper.selectList(queryWrapper);

        if (customers.size() > 0) {
            Customer customer = customers.get(0);

            // 更新payableamount
            double payableAmount = customer.getPayableamount() + Double.parseDouble(params.getIncAccounts());
            LambdaUpdateWrapper<Customer> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Customer::getCustomerid, customer.getCustomerid())
                    .set(Customer::getPayableamount, payableAmount);

            customerMapper.update(null, updateWrapper);
            // 向transactionrecord表中插入记录
            Transactionrecord transaction = new Transactionrecord();
            transaction.setCustomerid(customer.getCustomerid());
            transaction.setUsername(customer.getUsername());
            transaction.setTransactionamount(Double.parseDouble(params.getIncAccounts()));
            transaction.setTransactionremark(params.getNotes());

            // 设置当前系统时间为transactiontime
            Date currentDate = new Date();
            transaction.setTransactiontime(new Timestamp(currentDate.getTime()));
            transactionRecordMapper.insert(transaction);

            r.data("status_code", true);
            r.data("payableAmount", Double.toString(payableAmount));

        }

        return r;
    }

    public R balanceAccountsPayment(String id, balanceAccountsPaymentparam params) {
        R r = new R();
        r.data("status_code", false);

        // 查询customer表中的信息

        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("customerid", params.getCustomId());
        List<Customer> customers = customerMapper.selectList(queryWrapper);

        if (customers.size() > 0) {
            Customer customer = customers.get(0);
            // 更新payableamount
            double payableAmount = customer.getPayableamount() - Double.parseDouble(params.getbalAccounts());
            LambdaUpdateWrapper<Customer> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Customer::getCustomerid, customer.getCustomerid())
                    .set(Customer::getPayableamount, payableAmount);

            double recordAmount = 0 - Double.parseDouble(params.getbalAccounts());

            // 向transactionrecord表中插入记录
            Transactionrecord transaction = new Transactionrecord();
            transaction.setCustomerid(customer.getCustomerid());
            transaction.setUsername(customer.getUsername());
            transaction.setTransactionamount(Double.parseDouble(Double.toString(recordAmount)));//表中记录负数值
            transaction.setTransactionremark(params.getNotes());

            // 设置当前系统时间为transactiontime
            Date currentDate = new Date();
            transaction.setTransactiontime(new Timestamp(currentDate.getTime()));
            transactionRecordMapper.insert(transaction);

            r.data("status_code", true);
            r.data("payableAmount", Double.toString(payableAmount));

        }

        return r;
    }

    public R checkStaffInformation(String id, checkStaffInformationparam params){
        R r = new R();
        r.data("status_code", false);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 根据参数设置查询条件
        if (StringUtils.isNotBlank(params.getEmail())) {
            queryWrapper.eq("email", params.getEmail());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            queryWrapper.eq("name", params.getName());
        }
        if (StringUtils.isNotBlank(params.getPhone())) {
            queryWrapper.eq("contactnumber", params.getPhone());
        }
        if (StringUtils.isNotBlank(params.getTransferStation())) {
            queryWrapper.eq("transitstation", params.getTransferStation());
        }
        if (StringUtils.isNotBlank(params.getUserName())) {
            queryWrapper.eq("id", params.getUserName());
        }
        if (StringUtils.isNotBlank(params.getWarehouseId())) {
            queryWrapper.eq("warehouseid", params.getWarehouseId());
        }

        // 执行查询并返回结果
        List<User> userList = userMapper.selectList(queryWrapper);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (User user : userList) {
            if(user.getPermission().equals("user")) {
                Map<String, Object> personMap = new HashMap<>();
                personMap.put("name", user.getName());
                personMap.put("email", user.getEmail());
                personMap.put("phone", user.getContactnumber());
                personMap.put("transferStation", user.getTransitstation());
                personMap.put("userName", user.getId());
                personMap.put("warehouseId", user.getWarehouseid());


                // 添加其他属性到 personMap

                PeopleList.add(personMap);
            }
        }

        r.data("status_code", true);
        r.data("staffList", PeopleList);

        return r;
    }

    public R addInBoundPeople(String id, addInBoundPeopleparam params) {
        R r = new R();
        r.data("status_code", false);

        Warehouseperson warehouseperson = new Warehouseperson();

        // 设置参数值
        warehouseperson.setAddress(params.getAddress());
        warehouseperson.setEmail(params.getEmail());
        warehouseperson.setWarehousepersonid(Long.parseLong(params.getInBoundPersonId()));
        warehouseperson.setName(params.getName());
        warehouseperson.setContactnumber(params.getPhone());
        warehouseperson.setRemark(params.getRemark());
        warehouseperson.setUsername(params.getUserName());

        // 执行插入操作
        int rowsAffected = warehousepersonMapper.insert(warehouseperson);

        if(rowsAffected > 0){
            r.data("status_code", true);

        }

        return r;
    }

    public R addFetchOutPeople(String id, addFetchOutPeopleparam params) {
        R r = new R();
        r.data("status_code", false);

        Outboundperson outboundperson = new Outboundperson();

        // 设置参数值
        outboundperson.setAddress(params.getAddress());
        outboundperson.setEmail(params.getEmail());
        outboundperson.setOutboundpersonid(Long.parseLong(params.getOutBoundPresonId()));
        outboundperson.setName(params.getName());
        outboundperson.setContactnumber(params.getPhone());
        outboundperson.setRemark(params.getRemark());
        outboundperson.setUsername(params.getUserName());

        // 执行插入操作
        int rowsAffected = outboundpersonMapper.insert(outboundperson);

        if(rowsAffected > 0){
            r.data("status_code", true);

        }

        return r;

    }

    public R delStaffInformation(String id, String userName) {
        R r = new R();
        r.data("status_code", false);

        // 查询customer表中的信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getName, userName.replaceAll("[^\\u4E00-\\u9FA5]", ""));
        int rowsAffected = userMapper.delete(queryWrapper);

        if (rowsAffected > 0) {
            r.data("status_code", true);
        }

        return r;
    }

    public R getInBoundPeopleInformationAll(String id){
        R r = new R();

        List<Warehouseperson> list = warehousepersonMapper.selectList(null);

        List<Map<String, Object>> PeopleList = new ArrayList<>();
        for (Warehouseperson warehouseperson : list) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("inBoundPersonId", warehouseperson.getWarehousepersonid());
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

    public R getFetchOutPeopleInformationAll(String id) {
        R r = new R();

        List<Outboundperson> outboundpersons = outboundpersonMapper.selectList(null);
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

    public R getCustomInformationAll(String id) {
        R r = new R();

        List<Customer> customers = customerMapper.selectList(null);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (Customer customer : customers) {
            Map<String, Object> personMap = new HashMap<>();
            personMap.put("customId", customer.getCustomerid());
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

    public R getStaffInformationAll(String id) {
        R r = new R();

        // 执行查询并返回结果
        List<User> userList = userMapper.selectList(null);
        List<Map<String, Object>> PeopleList = new ArrayList<>();

        for (User user : userList) {
            if(user.getPermission().equals("user")) {
                Map<String, Object> personMap = new HashMap<>();
                personMap.put("name", user.getName());
                personMap.put("email", user.getEmail());
                personMap.put("phone", user.getContactnumber());
                personMap.put("transferStation", user.getTransitstation());
                personMap.put("userName", user.getId());
                personMap.put("warehouseId", user.getWarehouseid());


                // 添加其他属性到 personMap

                PeopleList.add(personMap);
            }
        }

        r.data("status_code", true);
        r.data("staffList", PeopleList);


        return r;
    }

    public R getStaffNameList(String id) {
        R r = new R();

        // 执行查询并返回结果
        List<User> userList = userMapper.selectList(null);
        List<String> PeopleList = new ArrayList<>();

        for (User user : userList) {
            if(user.getPermission().equals("user"))
                PeopleList.add(user.getName());
        }

        r.data("status_code", true);
        r.data("staffNameList", PeopleList);

        return r;
    }

    public R addCustom(String id, addCustomparam params) {
        R r = new R();
        r.data("status_code", false);

        Customer customer = new Customer();

        // 设置参数值
        customer.setAddress(params.getAddress());
        customer.setEmail(params.getEmail());
        customer.setContactnumber(params.getPhone());
        customer.setUsername(params.getUserName());
        customer.setCompanyname(params.getCompanyName());
        customer.setName(params.getName());


        // 执行插入操作
        int rowsAffected = customerMapper.insert(customer);

        if(rowsAffected > 0){
            r.data("status_code", true);

        }

        return r;
    }

    public R getCustomNameList(String id) {
        R r = new R();

        // 执行查询并返回结果
        List<Customer> userList = customerMapper.selectList(null);
        List<String> PeopleList = new ArrayList<>();

        for (Customer cus : userList) {
            PeopleList.add(cus.getName());
        }

        r.data("status_code", true);
        r.data("customNameList", PeopleList);

        return r;
    }




}
