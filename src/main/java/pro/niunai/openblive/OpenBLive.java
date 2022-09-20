package pro.niunai.openblive;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import pro.niunai.openblive.pojo.RequestHeader;
import pro.niunai.openblive.pojo.end.EndP;
import pro.niunai.openblive.pojo.end.EndR;
import pro.niunai.openblive.pojo.heartbeat.HeartbeatP;
import pro.niunai.openblive.pojo.heartbeat.HeartbeatR;
import pro.niunai.openblive.pojo.start.StartP;
import pro.niunai.openblive.pojo.start.StartR;

import java.util.Map;



public class OpenBLive {
	public static final String DOMAIN = "https://live-open.biliapi.com";
	/**
	 * AccessKey Secret
	 */
	private final String accessKeySecret;
	/**
	 * AccessKeyId
	 */
	private final String accesskeyid;

	/**
	 * 初始化传入必要开发者信息
	 * @param accessKeySecret accessKeySecret
	 * @param accesskeyid accesskeyid
	 */
	public OpenBLive(String accessKeySecret, String accesskeyid) {
		this.accessKeySecret = accessKeySecret;
		this.accesskeyid = accesskeyid;
	}

	/**
	 * 项目开启
	 * @param startP 传入参数
	 * @return 返回信息
	 */
	public StartR start(StartP startP) {
		String jsonString = JSON.toJSONString(startP);
		RequestHeader requestHeader = new RequestHeader(accesskeyid, DigestUtil.md5Hex(jsonString));
		Map<String, String> headerMap = requestHeader.getHeaders(accessKeySecret);
		HttpRequest request = HttpRequest.post(DOMAIN + "/v2/app/start")
				.headerMap(headerMap, true)
				.body(jsonString);
//		System.out.println(request);
		HttpResponse response =request.execute();
		JSONObject parseObject = JSON.parseObject(response.body());
		return parseObject.to(StartR.class);
	}

	/**
	 * 项目关闭
	 * @param endP 传入参数
	 * @return 返回信息
	 */
	public EndR end(EndP endP) {
		String jsonString = JSON.toJSONString(endP);
		RequestHeader requestHeader = new RequestHeader(accesskeyid, DigestUtil.md5Hex(jsonString));
		Map<String, String> headerMap = requestHeader.getHeaders(accessKeySecret);
		HttpRequest request = HttpRequest.post(DOMAIN + "/v2/app/end")
				.headerMap(headerMap, true)
				.body(jsonString);
		System.out.println(request);
		HttpResponse response =request.execute();
		JSONObject parseObject = JSON.parseObject(response.body());
		return parseObject.to(EndR.class);
	}

	/**
	 * 项目心跳
	 * @param heartbeatP 传入参数
	 * @return 返回信息
	 */
	public HeartbeatR heartbeat(HeartbeatP heartbeatP) {
		String jsonString = JSON.toJSONString(heartbeatP);
		RequestHeader requestHeader = new RequestHeader(accesskeyid, DigestUtil.md5Hex(jsonString));
		Map<String, String> headerMap = requestHeader.getHeaders(accessKeySecret);
		HttpRequest request = HttpRequest.post(DOMAIN + "/v2/app/heartbeat")
				.headerMap(headerMap, true)
				.body(jsonString);
		System.out.println(request);
		HttpResponse response =request.execute();
		JSONObject parseObject = JSON.parseObject(response.body());
		return parseObject.to(HeartbeatR.class);
	}
}
