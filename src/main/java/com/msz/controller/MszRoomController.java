package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.Scheduler.LeaseScheduler;
import com.msz.VO.*;
import com.msz.annotation.CurrentUser;
import com.msz.annotation.LoginRequired;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.*;
import com.msz.service.*;
import com.msz.util.*;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.service.SysCityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 房源信息 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/msz-rooms", description = "房源信息 接口; Responseble:cww")
@RestController
@RequestMapping("/msz-rooms")
public class MszRoomController {
    private static Logger logger = LoggerFactory.getLogger(MszRoomController.class);

    /**
     * 编号  id  Integer
     * <p>
     * 房东 id   long
     * <p>
     * 房源编号  no  String
     * <p>
     * 房间名称  name  String
     * <p>
     * 省份id  provinceId  Integer
     * <p>
     * 城市id  cityId  Integer
     * <p>
     * 区县id  countyId  Integer
     * <p>
     * 街道  townId  Integer
     * <p>
     * 门牌号  houseNumber  String
     * <p>
     * 地址  address  String
     * <p>
     * 室  room  String
     * <p>
     * 厅  hall  String
     * <p>
     * 卫  toilet  String
     * <p>
     * 楼层  floor  String
     * <p>
     * 共几层  whichFloor  String
     * <p>
     * 房间面积  area  Double
     * <p>
     * 是否有电梯：1-是，2-否  elevator  boolean
     * <p>
     * 装修情况  decorate  Integer
     * <p>
     * 朝向  toward  Integer
     * <p>
     * 房间配置（1：全部，2：标配，3：电视，4：空调，5：热水器，6：沙发，7：床，8：暖气，9：衣柜，10：可做饭，11：独立卫生间，12：独立阳台，13：冰箱，14：洗衣机）  configuration  String
     * <p>
     * 付款方式  payWay  Integer
     * <p>
     * 付款类型  payType  Integer
     * <p>
     * 租金价格  rentPrice  BigDecimal
     * <p>
     * 押金  depositPrice  BigDecimal
     * <p>
     * 底价  FloorPrice  BigDecimal
     * <p>
     * 差价  diffPrice  BigDecimal
     * <p>
     * 标题  title  String
     * <p>
     * 房间描述  description  String
     * <p>
     * 所属网点ID  orgId  Integer
     * <p>
     * 采集人ID  userId  Integer
     * <p>
     * 房源状态 0闲置 1已出租 2下架  status  String
     * <p>
     * 是否删除  isDel  String
     * <p>
     * 更新时间  updateTime  Date
     * <p>
     * 生成时间  createTime  Date
     */
    @Autowired
    private MszRoomService mszRoomService;

    @Autowired
    private MszRoomImgService mszRoomImgService;
    @Autowired
    private MszAccountService mszAccountService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysCityService sysCityService;

    @Autowired
    private MszLeaseService mszLeaseService;
    @Autowired
    private SysLogUtil sysLogUtil;

    @GetMapping("{id}")
    @ApiOperation(value = "查询单个房源信息-------小程序(房东)@Author=Maoyy", notes = "查询单个房源信息-------小程序(房东)@Author=Maoyy")
    public RespEntity<MszRoom> get(@PathVariable Integer id) {
        RoomVO vo = null;
        try {
            MszRoom room = mszRoomService.selectById(id);
            vo = new RoomVO();
            BeanUtils.copyProperties(room, vo);
            if (vo.getStatus().equals("1")) {
                MszLease lease = mszLeaseService.selectOne(new EntityWrapper<MszLease>().eq("roomId", vo.getId()).eq("status", "1"));
                if (lease != null) {
                    LeaseReturnParamVO leaseReturnParamVO = mszLeaseService.getMszLeaseById(lease.getId());
                    leaseReturnParamVO.setCollectMoneyDay(lease.getCollectMoneyDay());
                    leaseReturnParamVO.setBillDay(lease.getBillDay());
                    //得到操作人
                    leaseReturnParamVO.setOperatorName(sysUserService.selectById(leaseReturnParamVO.getUserId()).getTrueName());
                    //得到房客姓名
                    leaseReturnParamVO.setTenantName(mszAccountService.selectById(leaseReturnParamVO.getTenantId()).getName());
                    vo.setMszLease(leaseReturnParamVO);
                }
            }
            vo.setOrgName(sysOrgService.selectById(vo.getOrgId()).getName());
            vo.setOrgCode(sysOrgService.selectById(vo.getOrgId()).getCode());//网点编号
            vo.setTelName(mszAccountService.selectById(vo.getTelId()).getName());// 房东姓名
            vo.setUserName(sysUserService.selectById(vo.getUserId()).getTrueName());
            //图片
            vo.setProvinceName(vo.getProvinceId() == null ? "" : sysCityService.selectById(vo.getProvinceId().toString()).getName()); //省份名称
            vo.setCityName(vo.getCityId() == null ? "" : sysCityService.selectById(vo.getCityId().toString()).getName()); //城市名称
            vo.setCountyName(vo.getCountyId() == null ? "" : sysCityService.selectById(vo.getCountyId().toString()).getName()); //区县名称
            vo.setTownName(vo.getTownId() == null ? "" : sysCityService.selectById(vo.getTownId().toString()).getName()); //街道名称
        } catch (Exception e) {
            System.out.println("异常信息：");
            e.printStackTrace();
            return RespEntity.badRequest("查询失败！");
        }
        return RespEntity.ok().setResponseContent(vo);
    }

    @GetMapping("/getRoomDesc/{id}")
    @ApiOperation(value = "查看房源信息", notes = "查看房源信息")
    public RespEntity<MszRoom> getRoomDesc(@PathVariable Integer id) {

        return RespEntity.ok().setResponseContent(mszRoomService.getRoomDesc(id));
    }

    @PutMapping("/shelf/{houseId}")
    @ApiOperation(value = "上架所有房源信息", notes = "上架所有房源信息")
    public RespEntity<MszRoom> shelfAll(@PathVariable Integer houseId) {
        MszRoom mszRoom = new MszRoom();
        mszRoom.setStatus("0");
        return RespEntity.ok().setResponseContent(mszRoomService.update(mszRoom, new EntityWrapper<MszRoom>().eq("houseId", houseId).eq("status", "2")));
    }


    @GetMapping("byFloor/{id}")
    @ApiOperation(value = "根据层id找到对应的房源-------PC@Author=Maoyy", notes = "根据层id找到对应的房源-------PC@Author=Maoyy")
    public RespEntity<MszRoom> byFloor(@PathVariable Integer id) {
        List<MszRoom> room = mszRoomService.selectList(new EntityWrapper<MszRoom>().eq("floorId", id));
        List<MszRoom> vo = new ArrayList<>();
        for (MszRoom item : room) {
            item.setTelName(mszAccountService.selectById(item.getTelId()).getName());
            item.setOrgName(sysOrgService.selectById(item.getOrgId()).getName());
            item.setUserName(sysUserService.selectById(item.getUserId()).getTrueName());
            vo.add(item);
        }
        return RespEntity.ok().setResponseContent(vo);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "网点ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "houseName", value = "楼号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "houseNumber", value = "房间号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "管家", required = true, paramType = "query"),
            @ApiImplicitParam(name = "status", value = "0未租 1已出租 2下架")
    })
    @ApiOperation(value = "分页查询MszRoom", notes = "分页查询MszRoom")
    @GetMapping("/getMszRoomList")
    @LoginRequired
    public RespEntity<PageInfo<MszRoom>> list(@CurrentUser SysUser user, PagingRequest pagingRequest,
                                              Integer orgId,
                                              String houseName,
                                              String houseNumber,
                                              String housekeeper,
                                              String status,
                                              String address,
                                              Integer telId) {
        MszRoom room = new MszRoom();
        room.setStatus(status);
        room.setAddress(address);
        room.setTelId(telId);
        room.setOrgId(orgId);
        room.setHouseName(houseName);
        room.setHouseNumber(houseNumber);
        room.setHousekeeper(housekeeper);
        return RespEntity.ok().setResponseContent(mszRoomService.listPage(pagingRequest, room, user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
    })
    @ApiOperation(value = "查询闲置的房屋", notes = "查询闲置的房屋")
    @GetMapping("/getLockRoomList")
    @LoginRequired
    public RespEntity<PageInfo<MszRoom>> getLockRoomList(PagingRequest pagingRequest, String address,
                                                         String no, String telName, String community, String orgCode) {
        try {
            MszRoom room = new MszRoom();
            room.setStatus("0");
            room.setAddress(address);
            room.setOrgCode(orgCode);
            room.setNo(no);
            room.setTelName(telName);
            room.setCommunity(community);
            return RespEntity.ok().setResponseContent(mszRoomService.listPage(pagingRequest, room, UserCommon.getCurrentUser()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RespEntity.badRequest("查询失败！");
        }

    }

    @ApiOperation(value = "查询闲置/已出租/下架/全部 数量-------PC(房源管理)@Author=Maoyy", notes = "查询闲置/已出租/下架/全部 数量-------PC(房源管理)@Author=Maoyy")
    @GetMapping("/getIdleNumAndRentedNumAndObtainedNum")
    @LoginRequired
    public RespEntity<PageInfo<IdleNumAndRentedNumAndObtainedNumVO>> getIdleNumAndRentedNumAndObtainedNum(@CurrentUser SysUser user) {
        return RespEntity.ok().setResponseContent(mszRoomService.getIdleNumAndRentedNumAndObtainedNum(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "查询房源信息-------小程序(业务员)@Author=Maoyy", notes = "查询房源信息-------小程序(业务员)@Author=Maoyy")
    @GetMapping("weProgramList")
    public RespEntity<PageInfo<MszRoom>> weProgramList(PagingRequest pagingRequest, RoomReceive roomReceive) {
        return RespEntity.ok().setResponseContent(mszRoomService.weProgramList(pagingRequest, roomReceive));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "查询房源信息-------小程序(房东)@Author=Maoyy", notes = "查询房源信息-------小程序(房东)@Author=Maoyy")
    @GetMapping("landlordRoom")
    public RespEntity<PageInfo<MszRoom>> landlordRoom(PagingRequest pagingRequest, RoomReceive roomReceive, @RequestParam(required = true) Integer landlordId) {
        return RespEntity.ok().setResponseContent(mszRoomService.landlordRoom(pagingRequest, roomReceive, landlordId));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "保存MszRoom", notes = "保存MszRoom")
    @LoginRequired
    public RespEntity insert(HttpServletRequest request, @RequestBody MszRoom mszRoom) {
        SysUser sysUser = UserCommon.getCurrentUser();
        //1.城市首字母
        SysCity sysCity = sysCityService.selectOne(new EntityWrapper<SysCity>().eq("id", mszRoom.getCityId()));
        String pyIndexStr = FirstCharUtil.getPYIndexStr(sysCity.getName(), true);
        //2.年月日scheduleRoom
        String dayStr = DateUtil.getDayStr(new Date(), "yyyyMMddHHmmss");
        //3.随机数
        String randomStr = RandomUtil.getRandomStr(6);
        String no = pyIndexStr + dayStr + randomStr;
        mszRoom.setNo(no);
        mszRoom.setCreateTime(new Date());
        if (!mszRoomService.insert(mszRoom)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->新增 ->失败");
            return RespEntity.badRequest("保存失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->新增->成功");
        return RespEntity.ok("保存成功");
    }

    @Autowired
    private MszRoomTempleService mszRoomTempleService;

    @Autowired
    private MszFloorService mszFloorService;

    @PostMapping("/byTemple")
    @ApiOperation(value = "根据楼栋的id找到对应的房源模板添加房源-------PC@Author=Maoyy", notes = "根据楼栋的id找到对应的房源模板添加房源-------PC@Author=Maoyy")
    public RespEntity byTemple(HttpServletRequest request, Integer houseId, String houseNumber, String houseDesc, BigDecimal rentPrice, Integer floorId) {
        MszRoom room = new MszRoom();
        try {
            MszRoomTemple temple = mszRoomTempleService.selectOne(new EntityWrapper<MszRoomTemple>().eq("houseId", houseId));
            MszRoom repeatRoom = mszRoomService.selectOne(new EntityWrapper<MszRoom>().eq("houseId", houseId).eq("houseNumber", houseNumber).eq("floorId", floorId));
            if (temple == null) {
                return RespEntity.badRequest("保存失败，未找到该楼栋对应的模板");
            }
            if (repeatRoom != null) {
                return RespEntity.badRequest("保存失败，这一层已经有" + houseNumber + "了");
            }
            BeanUtils.copyProperties(temple, room);
            room.setHouseNumber(houseNumber);
            room.setName(houseNumber);
            room.setHouseDesc(houseDesc);
            if (room.getHouseDesc().equals("") || room.getHouseDesc() == null) {
                room.setHouseDesc(temple.getHouseDesc());
            }
            room.setRentPrice(rentPrice);
            if (rentPrice == null) {
                room.setRentPrice(temple.getRentPrice());
            }
            room.setFloorId(floorId);
            MszFloor floor = mszFloorService.selectById(floorId);
            room.setFloorName(floor.getName().toString());
            room.setCreateTime(new Date());
            room.setDiffPrice(room.getRentPrice().subtract(room.getFloorPrice()));
            //1.城市首字母
            SysCity sysCity = sysCityService.selectOne(new EntityWrapper<SysCity>().eq("id", room.getCityId()));
            String pyIndexStr = FirstCharUtil.getPYIndexStr(sysCity.getName(), true);
            //2.年月日
            String dayStr = DateUtil.getDayStr(new Date(), "yyyyMMddHHmmss");
            //3.随机数
            String randomStr = RandomUtil.getRandomStr(6);
            String no = pyIndexStr + dayStr + randomStr;
            room.setNo(no);
            if (!mszRoomService.insert(room)) {
                return RespEntity.badRequest("保存失败");
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功").setResponseContent(room);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "telId", value = "房东Id", required = true, paramType = "query")
    })
    @GetMapping("getRooms")
    @ApiOperation(value = "小程序首页查看房源-------小程序@Author=Maoyy", notes = "小程序首页查看房源-------小程序@Author=Maoyy")
    public RespEntity getRooms(PagingRequest pagingRequest, Integer telId, String status) {
        return RespEntity.ok().setResponseContent(mszRoomService.getRooms(pagingRequest, telId, status));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房东Id", required = true, paramType = "query")
    })
    @GetMapping("getSpotRooms/{id}")
    @ApiOperation(value = "小程序首页查看即期房源", notes = "小程序首页查看即期房源")
    public RespEntity getSpotRooms(@PathVariable Integer id) {
        return RespEntity.ok().setResponseContent(mszRoomService.getSpotRooms(id));
    }

    @PutMapping("/update")
    @ApiOperation(value = "根据ID修改MszRoom", notes = "根据ID修改MszRoom")
    public RespEntity update(HttpServletRequest request, @RequestBody MszRoom mszRoom) {
        List<MszRoom> rooms = mszRoomService.selectList(new EntityWrapper<MszRoom>().eq("floorId", mszRoom.getFloorId()).eq("houseNumber", mszRoom.getHouseNumber()).ne("id", mszRoom.getId()));
        if (rooms.size() == 1) {
            return RespEntity.error("更新失败,该房间号重复");
        }
        if (!mszRoomService.updateById(mszRoom)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }

    @PutMapping("/updateStatus")
    @ApiOperation(value = "下架", notes = "下架")
    @LoginRequired
    public RespEntity updateStatus(HttpServletRequest request, @RequestParam Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        MszRoom mszRoom = new MszRoom();
        mszRoom.setStatus("2");
        if (!mszRoomService.update(mszRoom, new EntityWrapper<MszRoom>().eq("id", id))) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->下架->失败");
            return RespEntity.badRequest("下架失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->下架->成功");
        return RespEntity.ok("下架成功");
    }

    @PutMapping("/updateShelf")
    @ApiOperation(value = "上架", notes = "上架")
    @LoginRequired
    public RespEntity updateShelf(HttpServletRequest request, @RequestParam Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        MszRoom mszRoom = new MszRoom();
        mszRoom.setStatus("0");
        if (!mszRoomService.update(mszRoom, new EntityWrapper<MszRoom>().eq("id", id))) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->上架->失败");
            return RespEntity.badRequest("上架失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->上架->成功");
        return RespEntity.ok("上架成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszRoom", notes = "根据ID删除MszRoom")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!mszRoomService.deleteById(id)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "房源信息 ->删除->成功");
        return RespEntity.ok("删除成功");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "网点Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "telName", value = "房东", required = false, paramType = "query"),
            @ApiImplicitParam(name = "houseName", value = "楼号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "houseNumber", value = "房号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "管家", required = false, paramType = "query")
    })
    @ApiOperation(value = "根据网点查房源", notes = "根据网点查房源")
    @GetMapping("/getRoomListByOrg")
    public RespEntity<PageInfo<MszRoom>> getRoomListByOrg(PagingRequest pagingRequest, Integer orgId, String telName,
                                                          String houseName, String houseNumber, String userName) {
        //分页查询
        List<MszRoom> mszRoomList = mszRoomService.getRoomListByOrg(pagingRequest, orgId, telName, houseName, houseNumber, userName);
        //总条数
        int total = mszRoomService.getRoomListByOrgSums(orgId, telName, houseName, houseNumber, userName);
        //总页数
        int pages = total % pagingRequest.getLimit() == 0 ? (total / pagingRequest.getLimit()) : (total / pagingRequest.getLimit() + 1);
        //返回
        PageInfo<MszRoom> pageInfo = new PageInfo<MszRoom>();
        pageInfo = PageInfoUtil.setPageInfo(pageInfo, mszRoomList, pagingRequest, total, pages);
        return RespEntity.ok().setResponseContent(pageInfo);
    }


}