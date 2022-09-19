package pro.niunai.openblive.pojo;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * 公共请求头
 */
@Data
@AllArgsConstructor
public class RequestHeader {
	/**
	 * 接受的返回结果的类型。目前只支持JSON类型，取值：application/json。
	 */
	private String accept;
	/**
	 * 当前请求体（Request Body）的数据类型。目前只支持JSON类型，取值：application/json。
	 */
	private String contentType;
	/**
	 * 请求体的编码值，根据请求体计算所得。算法说明：将请求体内容当作字符串进行MD5编码。
	 */
	private String xBiliContentMd5;
	/**
	 * unix时间戳，单位是秒。请求时间戳不能超过当前时间10分钟，否则请求会被丢弃。
	 */
	private String xBiliTimestamp;
	/**
	 * 签名方式。取值：HMAC-SHA256
	 */
	private String xBiliSignatureMethod;
	/**
	 * 签名唯一随机数。用于防止网络重放攻击，建议您每一次请求都使用不同的随机数。
	 */
	private String xBiliSignatureNonce;
	/**
	 * AccessKeyId
	 */
	private String xBiliAccesskeyid;
	/**
	 * 1.0
	 */
	private String xBiliSignatureVersion;
	/**
	 * 请求签名（注意生成的签名是小写的）。关于请求签名的计算方法，请参见签名机制
	 */
	private String authorization;

	public RequestHeader(String xBiliAccesskeyid, String xBiliContentMd5) {
		this.xBiliAccesskeyid = xBiliAccesskeyid;
		this.xBiliContentMd5 = xBiliContentMd5;
	}

	public Map<String, String> getHeaders(String accessKeySecret) {
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Accept", accept == null ? accept = "application/json" : accept);
		headers.put("Content-Type", contentType == null ? contentType = "application/json" : contentType);
		headers.put("x-bili-content-md5", xBiliContentMd5);
		headers.put("x-bili-timestamp", xBiliTimestamp == null ? xBiliTimestamp = String.valueOf(System.currentTimeMillis() / 1000L) : xBiliTimestamp);
		headers.put("x-bili-signature-method", xBiliSignatureMethod == null ? xBiliSignatureMethod = "HMAC-SHA256" : xBiliSignatureMethod);
		headers.put("x-bili-signature-nonce", xBiliSignatureNonce == null ? xBiliSignatureNonce = UUID.randomUUID().toString() : xBiliSignatureNonce);
		headers.put("x-bili-accesskeyid", xBiliAccesskeyid);
		headers.put("x-bili-signature-version", xBiliSignatureVersion == null ? xBiliSignatureVersion = "1.0" : xBiliSignatureVersion);
		headers.put("Authorization", authorization == null ? authorization = generateSignature(accessKeySecret) : authorization);
		return headers;
	}

	private String generateSignature(String accessKeySecret) {
		String template = "x-bili-accesskeyid:{}" + "\n" +
				"x-bili-content-md5:{}" + "\n" +
				"x-bili-signature-method:{}" + "\n" +
				"x-bili-signature-nonce:{}" + "\n" +
				"x-bili-signature-version:{}" + "\n" +
				"x-bili-timestamp:{}";
		String secretKey = StrUtil.format(template, xBiliAccesskeyid, xBiliContentMd5, xBiliSignatureMethod,
				xBiliSignatureNonce, xBiliSignatureVersion, xBiliTimestamp);
		return HexUtil.encodeHexStr(
				calcHmacSha256(
						accessKeySecret.getBytes(CharsetUtil.CHARSET_UTF_8),
						secretKey.getBytes(CharsetUtil.CHARSET_UTF_8)
				));
	}

	static public byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
		byte[] hmacSha256 = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
			mac.init(secretKeySpec);
			hmacSha256 = mac.doFinal(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to calculate hmac-sha256", e);
		}
		return hmacSha256;
	}
}
