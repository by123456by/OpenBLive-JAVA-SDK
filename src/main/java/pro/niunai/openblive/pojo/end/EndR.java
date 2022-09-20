package pro.niunai.openblive.pojo.end;

import lombok.Data;

/**
 * 项目关闭的返回值
 */
@Data
public class EndR  {
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
	/**
	 * 业务信息，具体请参考各业务接口返回值
	 */
	private Object data;
}

