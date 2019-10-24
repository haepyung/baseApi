package com.base.apiserver.error;

/**
 * <p> API 통신시 발생하는 error 코드
 * 
 * <li>맴버변수
 * <pre>
 *  {@link ApiErrorCode#INTERNAL_ERROR_CODE}
 *  {@link ApiErrorCode#INVALID_PARAMETER_CODE}
 *  {@link ApiErrorCode#NOT_EXIST_ENTITIY}
 *  {@link ApiErrorCode#REQUEST_BODY_NULL}
 *  {@link ApiErrorCode#ARGUMENT_TYPE_MISMATCH_ERROR_CODE}
 *  {@link ApiErrorCode#NOT_JSON_MEDIA_TYPE_ERROR_CODE}
 *  {@link ApiErrorCode#JSON_PROCESSING_ERROR_CODE}
 * </pre>
 * @author PARK
 *
 */
public class ApiErrorCode {
	
	/**
	 * 서버 내부에서 에러가 발생한 경우
	 */
	public static final int INTERNAL_ERROR_CODE = -1;
	
	/**
	 * 객체의 데이터의 유효성 에러
	 */
	public static final int INVALID_PARAMETER_ERROR_CODE = -2;
	
	/**
	 * 객체가 DB에 없는 경우
	 */
	public static final int NOT_EXIST_ENTITIY = -3;
	
	/**
	 * REQEST BODY에 값이 null인 경우
	 */
	public static final int REQUEST_BODY_NULL = -4;
	
	/**
	 * 객체의 ARGUMENT TYPE 오류
	 */
	public static final int ARGUMENT_TYPE_MISMATCH_ERROR_CODE = -5;
	
	/**
	 * application/json 미디어 타입 이 아닌 경우 발생
	 */
	public static final int NOT_JSON_MEDIA_TYPE_ERROR_CODE = -6;
	
	/**
	 * JSON 컨텐트를 처리 (파싱, 생성) 시 발생
	 */
	public static final int JSON_PROCESSING_ERROR_CODE = -7;
}
