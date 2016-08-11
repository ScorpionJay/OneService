package com.one.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

/**
 * @author jay time: 2016年8月12日
 */
public class JpushUtil {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final String appKey = "your appKey";
	private final String masterSecret = "your ms";

	private void push() {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = buildPushObject_all_all_alert();

		try {
			PushResult result = jpushClient.sendPush(payload);
			log.info("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			log.error("Connection error, should retry later", e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			log.error("Should review the error, and fix the request", e);
			log.info("HTTP Status: " + e.getStatus());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Error Message: " + e.getErrorMessage());
		}

	}

	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll("test111");
	}
	
	public static void main(String[] args) {
		JpushUtil jpushUtil = new JpushUtil();
		jpushUtil.push();
	}
	
}
