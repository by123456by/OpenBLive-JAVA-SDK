package pro.niunai.openblive.demo;
/*
package pro.niunai.openblive;

//敏感key信息，排除git范围。
public class key {
	public static final String ACCESSKEYSECRET = "";
	public static final String ACCESSKEYID = "";
	public static final String CODE = "";
	public static final long APPID = 0L;
}
 */
//import pro.niunai.openblive.test.key;

import pro.niunai.openblive.OpenBLive;
import pro.niunai.openblive.pojo.end.EndP;
import pro.niunai.openblive.pojo.end.EndR;
import pro.niunai.openblive.pojo.heartbeat.HeartbeatP;
import pro.niunai.openblive.pojo.heartbeat.HeartbeatR;
import pro.niunai.openblive.pojo.start.StartP;
import pro.niunai.openblive.pojo.start.StartR;

public class demo {
	public static void main(String[] args) throws InterruptedException {
		OpenBLive openBlive = new OpenBLive(key.ACCESSKEYSECRET,key.ACCESSKEYID);//初始化传入开发密钥信息

		StartP startP = new StartP(key.CODE,key.APPID);//项目开启传入主播身份码和项目id
		StartR startR = openBlive.start(startP);//项目开启
		String gameId = startR.getData().getGameInfo().getGameId();
		System.out.println(startR);

		Thread.sleep(20000);

		HeartbeatP heartbeatP = new HeartbeatP(gameId);//项目心跳传入场次id
		HeartbeatR heartbeatR = openBlive.heartbeat(heartbeatP);//项目心跳
		System.out.println(heartbeatR);

		Thread.sleep(20000);

		EndP endP = new EndP(key.APPID, gameId);//项目关闭传入项目id和场次id
		EndR endR = openBlive.end(endP);//项目关闭
		System.out.println(endR);
	}
}
