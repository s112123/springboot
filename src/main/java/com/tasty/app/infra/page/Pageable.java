package com.tasty.app.infra.page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pageable {

    /* 페이지 처리
     * 보여줄 데이터 개수: (MySQL) limit offset, size = limit (page - 1) * 10, size
     *
     * 페이지 번호 영역
     * last = (int) (Math.ceil(total / (double) size));
     * end = (int) (Math.ceil(page / (double) size)) * size;
     * (마지막 페이지 번호 영역에서) end = end > last ? last : end
     * start = (end - ((end - 1) % size)) > 0 ? (end - ((end - 1) % size)) : 1;
     * prev = start > 1
     * next = total > (end * size);
     */

    // 총 데이터 수
    private int total;
    // 보여 줄 데이터 개수
    private int size = 10;
    private int offset;
    // 현재 페이지
    private int page = 1;

    // 페이지 번호 영역
    // 마지막 페이지
    private int last;
    private int end = (int) (Math.ceil(page / (double) size)) * size;
    private int start;
    private boolean prev;
    private boolean next;

    public void setPage(int page) {
        this.page = page;
        this.offset = (page - 1) * size;
        this.end = (int) (Math.ceil(page / (double) size)) * size;
    }

    public void setTotal(int total) {
        this.total = total;
        this.last = (int) (Math.ceil(total / (double) 10));
        this.end = (last == 0) ? 1 : (end > last) ? last : end;
        this.start = (end - ((end - 1) % size)) > 0 ? (end - ((end - 1) % size)) : 1;
        this.prev = start > 1;
        this.next = total > (end * size);
    }
}
