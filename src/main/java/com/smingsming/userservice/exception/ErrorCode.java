package com.smingsming.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum ErrorCode {     // 핵심. 모든 예외 케이스를 이곳에서 관리

    /* 400 BAD_REQUEST : 잘못된 요청, 클라이언트 오류 */
    /* User */
    USER_JOIN_FAILED (BAD_REQUEST, "회원가입에 실패하였습니다."),
    EXIST_EMAIL (BAD_REQUEST, "이미 존재하는 이메일입니다."),
    EXIST_NICKNAME (BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    EXIST_PASSWORD (BAD_REQUEST, "이미 사용 중인 비밀번호입니다."),
    PRIVACY_READ_FAILED (BAD_REQUEST, "개인정보 조회에 실패하였습니다."),
    PASSWORD_UPDATE_FAILED (BAD_REQUEST, "비밀번호 변경을 실패하였습니다."),
    NICKNAME_UPDATE_FAILED (BAD_REQUEST, "닉네임 변경을 실패하였습니다."),
    THUMBNAIL_UPDATE_FAILED (BAD_REQUEST, "프로필 사진 변경을 실패하였습니다."),

    /* UserService */
    NOT_FOLLOW_MYSELF (BAD_REQUEST, "본인은 팔로우 할 수 없습니다."),
    USER_NOT_EXISTED (BAD_REQUEST, "존재하지 않는 사용자입니다."),
    FOLLOWING_READ_FAILED (BAD_REQUEST, "팔로잉 목록 조회에 실패하였습니다."),
    FOLLOWER_READ_FAILED (BAD_REQUEST, "팔로워 목록 조회에 실패하였습니다."),
    TICKET_BUY_FAILED (BAD_REQUEST, "이용권 구매에 실패하였습니다."),
    TICKET_PAYHISTORY_FAILED (BAD_REQUEST, "이용권 및 결제 내역 조회에 실패하였습니다."),
    TICKET_NOT_EXISTED (BAD_REQUEST, "존재하지 않는 이용권입니다."),
    READ_TICKET_FAILED (BAD_REQUEST, "이용권 목록 조회에 실패하였습니다."),


    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    TOKEN_WRONG_TYPE(UNAUTHORIZED, "잘못된 형식의 토큰입니다."),
    TOKEN_EXPIRED(UNAUTHORIZED, "만료된 토큰입니다."),
    TOKEN_UNSUPPORTED(UNAUTHORIZED, "지원하지 않는 형식의 토큰입니다."),
    ACCESS_DENIED(UNAUTHORIZED, "접근할 수 없습니다."),
    TOKEN_SIGNATURE_ERROR(UNAUTHORIZED, "잘못된 JWT 서명입니다."),
    TOKEN_NULL(UNAUTHORIZED, "토큰이 null이 아닙니다."),
    //    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "이미 존재하는 데이터입니다."),


    /* 500 INTERNAL_SERVER_ERROR : 서버 에러 */
    USER_SERVER_ERROR(INTERNAL_SERVER_ERROR, "USER 서버에서 요청을 이행할 수 없습니다."),
    USERSERVICE_SERVER_ERROR(INTERNAL_SERVER_ERROR, "USER SERVICE 서버에서 요청을 이행할 수 없습니다."),
    CHAT_SERVER_ERROR(INTERNAL_SERVER_ERROR, "CHAT 서버에서 요청을 이행할 수 없습니다."),
    SONG_SERVER_ERROR(INTERNAL_SERVER_ERROR, "SONG 서버에서 요청을 이행할 수 없습니다."),


    /* 503 SERVICE_UNAVAILABLE : 서버 지연 */
    USER_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "USER 서버가 지연되는 중입니다."),
    USERSERVICE_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "USER SERVICE 서버가 지연되는 중입니다."),
    CHAT_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "CHAT 서버가 지연되는 중입니다."),
    SONG_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "SONG 서버가 지연되는 중입니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
