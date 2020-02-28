package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.VO.PayInfoReturnParam;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.MszPayInfo;
import com.msz.service.MszPayInfoService;
import com.msz.util.ExportExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 支付信息（作为发起支付、支付回调的中转，以及支付记录的留存） 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/msz-pay-infos", description = "支付信息（作为发起支付、支付回调的中转，以及支付记录的留存） 接口; Responseble:cww")
@RestController
@RequestMapping("/msz-pay-infos")
public class MszPayInfoController {

    /**
     * 30位uuid  id  String
     * <p>
     * accountId  Integer
     * <p>
     * 场景：1支付押金和租金2.支付租金和水电费  type  String
     * <p>
     * 支付金额  amt  BigDecimal
     * <p>
     * 支付渠道：0钱包余额，1微信支付  channel  String
     * <p>
     * 订单ID  orderId  Integer
     * <p>
     * 0创建、1成功、2失败  status  String
     * <p>
     * updateTime  Date
     * <p>
     * createTime  Date
     */
    @Autowired
    private MszPayInfoService mszPayInfoService;


    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 14:21 2019/6/14
     **/
    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszPayInfo  @Description 包括订单对象", notes = "根据id获取单个MszPayInfo  @Description 包括订单对象")
    public RespEntity<MszPayInfo> get(@PathVariable String id) {
        return RespEntity.ok().setResponseContent(mszPayInfoService.mszPayInfo(id));
    }

//    @GetMapping("byYearMonthGroup/{id}")
//    @ApiOperation(value = "查询那些月份有交易记录-------小程序(房东)@Author=Maoyy", notes = "查询那些月份有交易记录-------小程序(房东)@Author=Maoyy")
//    public RespEntity<PageInfo<PayInfoVO>> byYearMonthGroup(@PathVariable Integer id) {
//        return RespEntity.ok().setResponseContent(mszPayInfoService.byYearMonthGroup(id));
//    }
//
//    @GetMapping("byMonthGroup")
//    @ApiOperation(value = "根据年月查询交易记录-------小程序(房东)@Author=Maoyy", notes = "根据年月查询交易记录-------小程序(房东)@Author=Maoyy")
//    public RespEntity<PageInfo<MszPayInfo>> byMonthGroup(String date, Integer id) {
//        return RespEntity.ok().setResponseContent(mszPayInfoService.byMonthGroup(id, date));
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @GetMapping("byAccountId")
    @ApiOperation(value = "分页查询交易记录-------小程序(租客)@Author=Maoyy", notes = "分页查询交易记录-------小程序(租客)@Author=Maoyy")
    public RespEntity<PageInfo<MszPayInfo>> byAccountId(PagingRequest pagingRequest, @RequestParam(value = "id", required = true) Integer id) {
        return RespEntity.ok().setResponseContent(mszPayInfoService.listPage(pagingRequest, new EntityWrapper<MszPayInfo>().eq("accountId", id)));
    }

    @GetMapping("/getListByYearMonth")
    @ApiOperation(value = "收入汇总@Author=cww", notes = "收入汇总@Author=cww")
    public RespEntity<PageInfo<PayInfoVO>> getListByYearMonth(Integer orgId, String createTimeMin, String createTimeMax) {
        return RespEntity.ok().setResponseContent(mszPayInfoService.getListByYearMonth(orgId, createTimeMin, createTimeMax));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "网点id"),
            @ApiImplicitParam(name = "createTimeMin", value = "起始时间"),
            @ApiImplicitParam(name = "createTimeMax", value = "结束时间"),
            @ApiImplicitParam(name = "date", value = "年月查询"),

    })
    @ApiOperation(value = "收入明细列表@Author=cww", notes = "后台收入明细列表@Author=cww")
    @GetMapping("/findList")
    public RespEntity<List<PayInfoReturnParam>> findList(PagingRequest pagingRequest, Integer orgId, String createTimeMin, String createTimeMax, String date) {

        return RespEntity.ok().setResponseContent(mszPayInfoService.findList(pagingRequest, orgId, createTimeMin, createTimeMax, date));
    }

    @ApiOperation(value = "收入详情@Author=cww", notes = "收入详情@Author=cww")
    @GetMapping("/getPayInfoDesc/{id}")
    public RespEntity<MszPayInfo> getPayInfoDesc(@PathVariable String id) {

        return RespEntity.ok().setResponseContent(mszPayInfoService.getPayInfoDesc(id));
    }

    @PostMapping
    @ApiOperation(value = "保存MszPayInfo@Author=cww", notes = "保存MszPayInfo@Author=cww")
    public RespEntity insert(@RequestBody MszPayInfo mszPayInfo) {
        if (!mszPayInfoService.insert(mszPayInfo)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改MszPayInfo", notes = "根据ID修改MszPayInfo")
    public RespEntity update(@RequestBody MszPayInfo mszPayInfo) {
        if (!mszPayInfoService.updateById(mszPayInfo)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszPayInfo", notes = "根据ID删除MszPayInfo")
    public RespEntity delete(@PathVariable String id) {
        if (!mszPayInfoService.deleteById(id)) {
            return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }


    @ApiOperation(value = "收入明细导出excel", notes = "收入明细导出excel")
    @GetMapping("/exportExcelData")
    public void exportExcelData(Integer orgId, String date, HttpServletResponse response) {
        //excel标题
        String title = "收入报表";
        //excel表名
        String[] headers = {"名称", "交易账号", "姓名", "金额", "交易时间", "流水号"};
        //excel文件名
        String fileName = title + System.currentTimeMillis() + ".xls";
        List<PayInfoReturnParam> list = mszPayInfoService.exportExcelData(orgId, date);
        //excel元素
        String content[][] = new String[list.size()][6];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[headers.length];
            String type = list.get(i).getType();
            if (type.equals("1")) {
                content[i][0] = "支付押金和租金";
            } else if (type.equals("2")) {
                content[i][0] = "支付租金和水电费";
            }
            content[i][1] = list.get(i).getAccountTel();
            content[i][2] = list.get(i).getAccountName();
            content[i][3] = list.get(i).getAmt().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(list.get(i).getCreateTime());
            content[i][4] = format;
            content[i][5] = list.get(i).getOrderNo();
        }

        //创建HSSFWorkbook
        XSSFWorkbook hssfWorkbook = ExportExcel.getHSSFWorkbook(title, headers, content);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            hssfWorkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置响应头
     */
    private void setResponseHeader(HttpServletResponse aResponse, String aFileName) {
        try {
            try {
                aFileName = new String(aFileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            aResponse.setContentType("application/octet-stream;charset=ISO8859-1");
            aResponse.setHeader("Content-Disposition", "attachment;filename=" + aFileName);
            aResponse.addHeader("Pargam", "no-cache");
            aResponse.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}