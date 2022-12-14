package pro.niunai.openblive.pojo.start;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目开启需要的参数
 */
@Data
@AllArgsConstructor
public class StartP {
	/**
	 * 主播身份码
	 */
	String code;
	/**
	 * 项目ID
	 */
	@JSONField(name="app_id")
	long appId;
}
