package pro.niunai.openblive;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import pro.niunai.openblive.pojo.RequestHeader;
import pro.niunai.openblive.pojo.start.StartP;
import pro.niunai.openblive.pojo.start.StartR;

import java.util.Map;

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
import pro.niunai.openblive.key;

import java.util.Map;

public class Api {
	public static final String DOMAIN = "https://live-open.biliapi.com";
	/**
	 * AccessKey Secret
	 */
	private final String accessKeySecret;
	/**
	 * AccessKeyId
	 */
	private final String accesskeyid;


	Api(String accessKeySecret,String accesskeyid) {
		this.accessKeySecret = accessKeySecret;
		this.accesskeyid = accesskeyid;
	}
	public static void main(String[] args) {
		Api api = new Api(key.ACCESSKEYSECRET,key.ACCESSKEYID);
		StartP startP = new StartP(key.CODE,key.APPID);
		System.out.println(api.start(startP));
	}
	public StartR start(StartP startP) {
		String jsonString = JSON.toJSONString(startP);
		RequestHeader requestHeader = new RequestHeader(accesskeyid, DigestUtil.md5Hex(jsonString));
		Map<String, String> headerMap = requestHeader.getHeaders(accessKeySecret);
		HttpResponse response = HttpRequest.post(DOMAIN + "/v2/app/start")
				.headerMap(headerMap, false)
				.body(jsonString)
				.execute();
		JSONObject parseObject = JSON.parseObject(response.body());
		return parseObject.to(StartR.class);
	}
}
