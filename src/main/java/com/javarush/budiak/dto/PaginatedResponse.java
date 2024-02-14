package com.javarush.budiak.dto;

import java.util.List;

public class PaginatedResponse<T> {

    private List<T> content;
    private int currentPage;
    private int totalPages;

    public PaginatedResponse() {
    }

    public PaginatedResponse(List<T> content, int currentPage, int totalPages) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
