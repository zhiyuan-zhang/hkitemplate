package com.hkitemplate.demo.utils;

/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-01-29 10:26
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//import com.durotan.common.interceptor.RestTemplateParamInterceptor;

/**
 * Created by huang on 18-6-22.
 */
public class RestTemplateUtil {

    private final static int CONNECT_TIMEOUT = 1000;

    private final static int READ_TIMEOUT = 1000;

    private RestTemplate restTemplate;

    private RestTemplateUtil() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        clientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);

        this.restTemplate = new RestTemplate(clientHttpRequestFactory);
//        this.restTemplate.setInterceptors(Collections.singletonList(new RestTemplateParamInterceptor()));
    }

    private static class HolderClass {
        private final static RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
    }

    private static RestTemplateUtil getInstance() {
        return HolderClass.restTemplateUtil;
    }

    /**
     * get请求
     * @param url url
     * @return string
     */
    public static String getForString(String url) {
        return getInstance().restTemplate.getForObject(url, String.class);
    }

    public static <T> T getForObject(String url, Class<T> responseType) {
        return getInstance().restTemplate.getForObject(url, responseType);
    }


    /**
     * post form请求
     * @param url url
     * @param params 请求参数
     * @return string
     */
    public static String postOfFormForString(String url, Map<String, String> params) {
        return postOfFormForObject(url, params, String.class);
    }

    public static <T> T postOfFormForObject(String url, Map<String, String> params, Class<T> responseType) {
        MultiValueMap<String, String> multiValueMap= new LinkedMultiValueMap<>();
        params.keySet().forEach(key -> multiValueMap.add(key, params.get(key)));

        HttpHeaders headers = new HttpHeaders();
        // post请求方式
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multiValueMap, headers);

        ResponseEntity<T> responseEntity = getInstance().restTemplate.postForEntity(url, requestEntity, responseType);
        return responseEntity.getBody();
    }

    /**
     * post json请求
     * @param url url
     * @param params 请求参数
     * @return string
     */
    public static String postOfJsonForString(String url, Map<String, String> params) {
        return postOfJsonForObject(url, params, String.class);
    }

    public static <T> T postOfJsonForObject(String url, Map<String, String> params, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        // post请求方式
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(params), headers);

        ResponseEntity<T> responseEntity = getInstance().restTemplate.postForEntity(url, requestEntity, responseType);
        return responseEntity.getBody();
    }

    public static void main(String[] args) {


//        Map<String, String> params = new HashMap<>();
//        params.put("deviceId", "string");
//        params.put("password", "string");
//        params.put("token", "string");
//        params.put("type", "string");
//        params.put("username", "string");
//
//        String result2 = RestTemplateUtil.postOfJsonForString("http://localhost:8080/v1/sso/login", params);
//        System.out.println(result2);
    }
}