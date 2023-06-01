package com.bdqn.ux_share.util;

import java.util.List;

public class Page<T> {
    private int page;//当前页面
    private int size;// 每页显示数量
    private int total;// 数据库总条数  100
    private int pageCount;// 总页码
    private List<T> data;// 数据

    public Page() {
    }

    public Page(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public Page(String page, String size) {
        if(page != null && !"".equals(page)){
            this.page = Integer.parseInt(page);
        }
        if(size != null && !"".equals(size)){
            this.size = Integer.parseInt(size);
        }
    }

    public void setTotal(int total) {
        this.total = total;
        // 计算总页码
        this.pageCount = (int)(Math.ceil(1.0 * this.total / this.size));
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }



    public int getPageCount() {
        return pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", pageCount=" + pageCount +
                ", data=" + data +
                '}';
    }
}
