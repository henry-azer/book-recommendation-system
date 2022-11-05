package com.henry.bookrecommendationsystem.dto.base.response;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.dto.base.pagination.Page;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationResponse<Dto extends BaseDto> extends Page {
    private int totalNumberOfPages;
    private long totalNumberOfElements;
    private List<Dto> result;
    private boolean isFirst;
    private boolean isLast;

    @Builder
    public PaginationResponse(int pageNumber, int pageSize, int totalNumberOfPages, long totalNumberOfElements, List<Dto> result, boolean isFirst, boolean isLast) {
        super(pageNumber, pageSize);
        this.totalNumberOfPages = totalNumberOfPages;
        this.totalNumberOfElements = totalNumberOfElements;
        this.result = result;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }
}