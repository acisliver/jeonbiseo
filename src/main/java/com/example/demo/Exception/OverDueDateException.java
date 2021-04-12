package com.example.demo.Exception;

import org.springframework.dao.DataAccessException;

public class OverDueDateException extends DataAccessException {
        //생성자의 인자로 예외메세지를 전달받아서
	public OverDueDateException(String msg) {
        //부모생성자에 전달하면
        super(msg);
        //message 필드에 저장된다.
    }
}
