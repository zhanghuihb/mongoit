package com.mongoit.common.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Tainy
 * @date 2018/6/11 14:27
 */
public class BaseController {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	protected ResponseEntity<String> responseData(BaseResponse<?> baseResponse) {
		HttpHeaders respHeader = new HttpHeaders();
		respHeader.set("Content-Type", "application/json;charset=UTF-8");
		String repJson = JSON.toJSONString(baseResponse);
		String msg = baseResponse.getData() == null ? "" : baseResponse.getData().getClass().getName();
		LOGGER.info("返回结果: " + msg + " " + StringUtils.abbreviate(repJson, 3000));
		LOGGER.info("\n\n");

		return new ResponseEntity<String>(repJson, respHeader, HttpStatus.OK);
	}

	/**
	 * 需要使用注解@JsonProperty 转换字段名称的，用此返回方法
	 *
	 * @author Tainy
	 * @date   2018/4/26 13:46
	 */
	protected ResponseEntity<String> responseDataTran(BaseResponse<?> baseResponse) throws JsonProcessingException {
		HttpHeaders respHeader = new HttpHeaders();
		respHeader.set("Content-Type", "application/json;charset=UTF-8");
		String repJson = new ObjectMapper().writeValueAsString(baseResponse);
		String msg = baseResponse.getData() == null ? "" : baseResponse.getData().getClass().getName();
		LOGGER.info("返回结果: " + msg + " " + StringUtils.abbreviate(repJson, 3000));
		LOGGER.info("\n\n");

		return new ResponseEntity<String>(repJson, respHeader, HttpStatus.OK);
	}
	protected <T> Page<T> setPage(PageRequest.Page page) {

		Page<T> pagePlugin = new Page<T>();

		if (page == null) {
			pagePlugin.setCurrentPage(1);
			pagePlugin.setPageSize(20);
		} else {
			BeanUtils.copyProperties(page, pagePlugin);
			// 判断传递参数
			if (page.getCurrentPage() == 0) {
				pagePlugin.setCurrentPage(1);
			}

			if (page.getPageSize() == 0) {
				pagePlugin.setPageSize(20);
			}
		}

		pagePlugin.setShowCount(pagePlugin.getPageSize());

		return pagePlugin;
	}
}
