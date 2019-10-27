package com.mzj.networkswitch.library.annotation;

import com.mzj.networkswitch.library.type.NetworkType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Network {
    NetworkType networkType() default NetworkType.AUTO;
}
