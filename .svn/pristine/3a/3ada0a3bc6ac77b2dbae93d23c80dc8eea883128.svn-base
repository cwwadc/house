package com.msz.service;

import com.msz.common.PagingRequest;
import com.msz.model.MszLeaseCharge;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 租约收费项目（水电费等） 服务类
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08
 */
public interface MszLeaseChargeService extends IService<MszLeaseCharge> {
    
    /**
     * 默认分页(PageHelper分页)
     * @param pagingRequest
     *   <pre>
     *      <table border="1">
     *          <caption>参数说明({@link PagingRequest})</caption>
     *          <tr>
     *              <td>参数名称</td>
     *              <td>参数类型</td>
     *              <td>参数说明</td>
     *          </tr>
     *          <tr>
     *              <td>PagingRequest#getOffset()</td>
     *              <td>int</td>
     *              <td>页码</td>
     *          </tr>
     *          <tr>
     *              <td>PagingRequest#getLimit()</td>
     *              <td>int</td>
     *              <td>每页显示数量</td>
     *          </tr>
     *      </table>
     *      <br>
     *     示例 :
     *          <ul>
     *              <li> 1. PageInfo page = listPage(new PagingRequest(1,10)); </li>
     *              <li> 
     *                  2. 控制器中直接使用 PagingRequest 作为参数接收即可,就算客户端不传值也会有默认值. <br/>
     *                     默认分页起始值 : {@link com.baicang.common.GlobalConstant#DEFAULT_PAGE_NUM} <br/>
     *                     默认分页大小值 : {@link com.baicang.common.GlobalConstant#DEFAULT_PAGE_SIZE} 
     *              </li>
     *          </ul>
     *
     *
     *   </pre>                    
     * @return PageInfo
     */
    PageInfo<MszLeaseCharge> listPage(PagingRequest pagingRequest, Wrapper wrapper);
    
        
}
