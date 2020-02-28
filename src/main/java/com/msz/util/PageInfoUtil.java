package com.msz.util;

import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;

import java.util.List;

public class PageInfoUtil {

    public static PageInfo setPageInfo(PageInfo pageInfo, List list, PagingRequest pagingRequest, int total, int pages){
        pageInfo.setList(list);
        pageInfo.setPageNum(pagingRequest.getOffset());
        pageInfo.setPageSize(pagingRequest.getLimit());
        pageInfo.setSize(list==null?0:list.size());
        if(pageInfo.getSize()==0) {
            pageInfo.setStartRow(0);
            pageInfo.setEndRow(0);
        }else{
            pageInfo.setStartRow((pagingRequest.getOffset()-1)*pagingRequest.getLimit()+1);
            pageInfo.setEndRow(pageInfo.getStartRow() - 1 + pageInfo.getSize());
        }
        pageInfo.setTotal(total);
        pageInfo.setPages(pages);

        return pageInfo;
    }


}
