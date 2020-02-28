package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszOrderHistoryVO;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.MszAccount;
import com.msz.model.MszOrderHistory;
import com.msz.service.MszAccountService;
import com.msz.service.MszOrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 历史缴费信息 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/msz-order-historys", description = "历史缴费信息 接口; Responseble:cww")
@RestController
@RequestMapping("/msz-order-historys")
public class MszOrderHistoryController {

    /**
     * id  Integer
     * <p>
     * 缴费编号  no  String
     * <p>
     * 租约ID  leaseId  Integer
     * <p>
     * 房间ID  roomId  Integer
     * <p>
     * 房东ID  landlordId  Integer
     * <p>
     * 房客ID  tenantId  Integer
     * <p>
     * 租金  rentPrice  BigDecimal
     * <p>
     * 押金  depositPrice  BigDecimal
     * <p>
     * breachPrice  BigDecimal
     * <p>
     * 结束时间  endTime  Date
     * <p>
     * 缴费状态 0未缴费 1已缴费  status  String
     * <p>
     * 是否删除  isDel  String
     * <p>
     * 更新时间  updateTime  Date
     * <p>
     * 生成时间  createTime  Date
     */
    @Autowired
    private MszOrderHistoryService mszOrderHistoryService;

    @Autowired
    private MszAccountService mszAccountService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszOrderHistory", notes = "根据id获取单个MszOrderHistory")
    public RespEntity<MszOrderHistory> get(@PathVariable Integer id) {
        MszOrderHistory orderHistory = new MszOrderHistory();
        orderHistory = mszOrderHistoryService.selectById(id);
        if (orderHistory != null) {
            orderHistory.setTenantName(mszAccountService.selectOne(new EntityWrapper<MszAccount>().eq("id", orderHistory.getTenantId())).getName());
        }
        return RespEntity.ok().setResponseContent(orderHistory);
    }

    @GetMapping("byYearMonthGroup/{id}")
    @ApiOperation(value = "查询那些月份有交易记录-------小程序(房东)@Author=Maoyy", notes = "查询那些月份有交易记录-------小程序(房东)@Author=Maoyy")
    public RespEntity<PageInfo<PayInfoVO>> byYearMonthGroup(@PathVariable Integer id) {
        return RespEntity.ok().setResponseContent(mszOrderHistoryService.byYearMonthGroup(id));
    }

    @GetMapping("byMonthGroup")
    @ApiOperation(value = "根据年月查询交易记录-------小程序(房东)@Author=Maoyy", notes = "根据年月查询交易记录-------小程序(房东)@Author=Maoyy")
    public RespEntity<PageInfo<MszOrderHistoryVO>> byMonthGroup(String date, Integer id) {
        return RespEntity.ok().setResponseContent(mszOrderHistoryService.byMonthGroup(id, date));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszOrderHistory", notes = "分页查询MszOrderHistory")
    @GetMapping
    public RespEntity<PageInfo<MszOrderHistory>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(mszOrderHistoryService.listPage(pagingRequest, null));
    }


    @PostMapping
    @ApiOperation(value = "保存MszOrderHistory", notes = "保存MszOrderHistory")
    public RespEntity insert(@RequestBody MszOrderHistory mszOrderHistory) {
        if (!mszOrderHistoryService.insert(mszOrderHistory)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改MszOrderHistory", notes = "根据ID修改MszOrderHistory")
    public RespEntity update(@RequestBody MszOrderHistory mszOrderHistory) {
        if (!mszOrderHistoryService.updateById(mszOrderHistory)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszOrderHistory", notes = "根据ID删除MszOrderHistory")
    public RespEntity delete(@PathVariable Integer id) {
        if (!mszOrderHistoryService.deleteById(id)) {
            return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }


}