package pro.niunai.openblive.pojo.start;

import pro.niunai.openblive.pojo.Result;

/**
 * 项目开启的返回值
 */
@lombok.Data
public class StartR extends Result {
	/**
	 * 业务信息，具体请参考各业务接口返回值
	 */
	private Data data;
}

/**
 * 业务信息，具体请参考各业务接口返回值
 */
@lombok.Data
class Data {
	/**
	 * 场次信息
	 */
	private GameInfo gameInfo;
	/**
	 * 长连信息
	 */
	private WebsocketInfo websocket_info;
	/**
	 * 主播信息
	 */
	private AnchorInfo anchor_info;
}

/**
 * 场次信息
 */
@lombok.Data
class GameInfo {
	/**
	 * 场次id,心跳key(心跳保持20s-60s)调用一次,超过60s无心跳自动关闭,长连停止推送消息
	 */
	private String gameId;
}

/**
 * 长连信息
 */
@lombok.Data
class WebsocketInfo {
	/**
	 * 长连使用的请求json体 第三方无需关注内容,建立长连时使用即可
	 */
	String authBody;
	/**
	 * wss 长连地址
	 */
	String[] wssLink;
}
/**
 * 主播信息
 */
@lombok.Data
class AnchorInfo{
	/**
	 *主播房间号
	 */
	Integer roomId;
	/**
	 *主播昵称
	 */
	String uname;
	/**
	 * 主播头像
	 */
	String uface;
	/**
	 * 主播uid
	 */
	Integer uid;
}

