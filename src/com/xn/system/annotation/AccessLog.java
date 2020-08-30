package com.xn.system.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统访问日志，在Controller层拦截
 * @author yoko
 * @createTime  2016-6-28 上午09:46:02
 */

@Target( { ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLog {

	String desc() default "";
}
