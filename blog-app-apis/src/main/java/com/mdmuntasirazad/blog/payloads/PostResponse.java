package com.mdmuntasirazad.blog.payloads;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	/**
	 * The list of PostDto objects for the current page.
	 */
	private List<PostDto> content;
	
	/**
	 * The current page number (starts from 0).
	 */
	private int pageNumber;
	
	/**
	 * The number of records requested per page.
	 */
	private int pageSize;
	
	/**
	 * The total number of records available across all pages.
	 */
	private long totalElements;
	
	/**
	 * The total number of pages available.
	 */
	private int totalPages;
	
	/**
	 * A boolean flag indicating if this is the last page.
	 */
	private boolean lastPage;

}