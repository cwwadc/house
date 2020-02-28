package com.msz.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.common.PagingRequest;
import com.msz.model.MszMsm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-16
 */
public interface MszMsmService extends IService<MszMsm> {
    
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
     *                     默认分页起始值 :  <br/>
     *                     默认分页大小值 :
     *              </li>
     *          </ul>
     *
     *
     *   </pre>                    
     * @return PageInfo
     */
    PageInfo<MszMsm> listPage(PagingRequest pagingRequest, Wrapper wrapper);
    
        
}
