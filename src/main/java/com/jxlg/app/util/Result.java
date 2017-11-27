package com.jxlg.app.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class Result<T> {
	private String msg;
	private String state;
	private List<T> response;
	private T responseOne;

}
