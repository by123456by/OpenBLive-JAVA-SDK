package pro.niunai.openblive.pojo.heartbeat;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目犀鸟需要的参数
 */
@Data
@AllArgsConstructor
public class HeartbeatP {
	/**
	 * 场次id
	 */
	@JSONField(name="game_id")
	String gameId;
}
