package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.model.MszWalletBill;

/**
 * <p>
 * 钱包流水 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszWalletBillService extends IService<MszWalletBill> {
    
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
    PageInfo<MszWalletBill> listPage(PagingRequest pagingRequest, Wrapper wrapper);
    
        
}
