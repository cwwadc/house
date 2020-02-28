package com.msz.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.dao.SysCityMapper;
import com.msz.model.SysCity;
import com.msz.util.ExportExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * <p>
 * 测试导出excel 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/poi", description = "导出excel")
@RestController
@RequestMapping("/poi")
public class ExportExcelController {

    @Autowired
    SysCityMapper sysCityMapper;

    @ApiOperation(value = "导出excel", notes = "导出excel")
    @GetMapping("/exportExcelData")
    public void exportExcelData(HttpServletRequest request, HttpServletResponse response) {
        //excel标题
        String title = "全国省市区表";
        //excel表名
        String[] headers = {"id", "名称", "省份", "城市", "区域", "街道", "父级id", "等级", "经度", "纬度"};
        //excel文件名
        String fileName = title + System.currentTimeMillis() + ".xls";
        Wrapper<SysCity> wrapper = new EntityWrapper<>();
        wrapper.eq("parent", "0");
        List<SysCity> list = sysCityMapper.selectList(wrapper);
        //excel元素
        String content[][] = new String[list.size()][10];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[headers.length];
            content[i][0] = list.get(i).getId();
            content[i][1] = list.get(i).getName();
            content[i][2] = list.get(i).getProvince();
            content[i][3] = list.get(i).getCity();
            content[i][4] = list.get(i).getDistrict();
            content[i][5] = list.get(i).getTown();
            content[i][6] = list.get(i).getParent();
            content[i][7] = list.get(i).getLevel().toString();
            content[i][8] = list.get(i).getLng();
            content[i][9] = list.get(i).getLat();
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
