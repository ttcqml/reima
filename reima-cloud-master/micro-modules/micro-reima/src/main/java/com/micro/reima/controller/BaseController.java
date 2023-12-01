package com.micro.reima.controller;

import com.micro.common.core.domain.PosResult;
import com.micro.common.core.exception.ServiceException;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class BaseController {


  @ResponseBody
  @ExceptionHandler(value = ServiceException.class)
  public PosResult error(ServiceException e){
    log.error(">>>error:{}",e);
    return PosResult.fail(e.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public PosResult error(Exception e){
    log.error(">>>error:{}",e);
    return PosResult.fail(Pattern.compile(e.getMessage()));
  }

}
