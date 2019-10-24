package com.base.apiserver.error;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> API ERROR에 대한 결과값을 저장하고 있는 객체
 * 
 * <li>맴버변수
 * <pre>
 * 	{@link ErrorResult#code}
 * 	{@link ErrorResult#msg}
 * 	{@link ErrorResult#httpStatusCode}
 * 	{@link ErrorResult#url}
 * </pre> 
 * @author PARK
 *
 */
@Data
@NoArgsConstructor
public class ErrorResult {
	
	/**
	 * API ERROR 고유 값
	 */
	private int code;
	
	/**
	 * ERROR 상세 정보
	 */
    private String msg;
    
    /**
     * httpStatusCode 응답 코드
     */
    private int httpStatusCode;
    
    /**
     * 호출 URL
     */
    private String url;
    
    public ErrorResult(int code, String msg, HttpStatus status, String url) {
    	this.code = code;
        this.msg = msg;
        this.httpStatusCode = status.value();
        this.url = url;
    }
}
