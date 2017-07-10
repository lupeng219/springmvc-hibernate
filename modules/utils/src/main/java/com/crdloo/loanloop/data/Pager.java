package com.crdloo.loanloop.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pager<T> implements Serializable {
    private static final long serialVersionUID = -5721800111931095465L;

    /**
     * 状态值
     */
    private int code = 0;

    /**
     * 错误信息
     */
    private String info;

    /**
     * 页码
     */
    private int page = 0;

    /**
     * 每页数量
     */
    private int size = 10;

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 总共页数
     */
    private int totalPages;

    /**
     * 总共数据的数量
     */
    private int totalElements;

    /**
     * 是否为首页
     */
    private Boolean firstPage;

    /**
     * 是否为末页
     */
    private Boolean lastPage;

    /**
     * 排序方向，应为ASC或DESC。本字段可选
     */
    private String direction;

    /**
     * 排序字段
     */
    private String sort;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        this.firstPage = firstPage;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public static <T> Pager<T> valueOf(Pageable page) {
        Pager<T> pager = new Pager<T>();

        pager.setPage(page.getPageNumber());
        pager.setSize(page.getPageSize());

        Sort sort = page.getSort();
        if (sort != null) {
            for (Sort.Order order : sort) {
                pager.setSort(order.getProperty());
                pager.setDirection(order.getDirection().toString());
            }
        }

        return pager;
    }

    public static <T> Pager<T> valueOf(Page<T> page) {
        Pager<T> pager = new Pager<T>();

        pager.setPage(page.getNumber() + 1);
        pager.setSize(page.getSize());

        pager.setTotalElements((int) page.getTotalElements());
        pager.setTotalPages(page.getTotalPages());

        pager.setFirstPage(page.isFirst());
        pager.setLastPage(page.isLast());

        Sort sort = page.getSort();
        if (sort != null) {
            for (Sort.Order order : sort) {
                pager.setSort(order.getProperty());
                pager.setDirection(order.getDirection().toString());
            }
        }

        pager.setData(page.getContent());

        return pager;
    }

    public Pageable toPageable() {
        if (this.sort != null) {
            return new PageRequest(this.page - 1, this.size, Sort.Direction.valueOf(this.direction),
                    this.sort);
        }

        return new PageRequest(this.page - 1, this.size);
    }

    /**
     * 增加默认排序的Pageable转换
     * 
     * @param direction
     *            默认的排序方向
     * @param name
     *            黰认的排序字段
     * 
     * @return Pageable请求
     */
    public Pageable toPageable(Sort.Direction direction, String name) {
        if (this.sort != null) {
            return new PageRequest(this.page - 1, this.size, Sort.Direction.valueOf(this.direction),
                    this.sort);
        }

        return new PageRequest(this.page - 1, this.size, direction, name);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
