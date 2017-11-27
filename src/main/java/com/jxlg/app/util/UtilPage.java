package com.jxlg.app.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UtilPage<T> {
	private Integer totalPages;//得到总页数
	private Integer pageNumber;//当前第几页
	private List<T> all;//得到的集合
	private Integer totalElements;//得到总条数
}