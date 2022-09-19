package pro.niunai.openblive.pojo;

import lombok.Data;

/**
 * 返回结果
 */
@Data
public class Result {
	/**
	 * 返回码
	 */
	private int code;
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 请求id
	 */
	private String requestId;
}
