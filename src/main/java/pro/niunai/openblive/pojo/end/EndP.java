package pro.niunai.openblive.pojo.end;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目关闭需要的参数
 */
@Data
@AllArgsConstructor
public class EndP {
	/**
	 * 项目ID
	 */
	@JSONField(name="app_id")
	long appId;
	/**
	 * 场次id
	 */
	@JSONField(name="game_id")
	String gameId;
}
