package com.piano.aspect;

import com.piano.annotation.AutoFill;
import com.piano.constant.AutoFillConstant;
import com.piano.context.BaseContext;
import com.piano.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * customize aspect, auto fill common fields
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.piano.mapper.*.*(..)) && @annotation(com.piano.annotation.AutoFill)")
    public void autoFillPointCut(){}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("start auto fill common fields");

        // get current operation method
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();

        // get the parameters of the method
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];

        // prepare data
        LocalDateTime time = LocalDateTime.now();
        Long operationUser = BaseContext.getCurrentId();

        // assign data based on reflected property
        if (operationType == OperationType.INSERT) {
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);


                //assign value through reflect
                setCreateTime.invoke(entity, time);
                setCreateUser.invoke(entity, operationUser);
                setUpdateTime.invoke(entity, time);
                setUpdateUser.invoke(entity, operationUser);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                setUpdateTime.invoke(entity, time);
                setUpdateUser.invoke(entity, operationUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
