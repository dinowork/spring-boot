package br.com.dino.spring_boot.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) { }
