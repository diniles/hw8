package ru.gb.hw8.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.gb.hw8.utils.Logger;

import java.util.Arrays;
import java.util.HashSet;

@Aspect
@Component
public class TrackUserAspect {
    private final Logger logger;
    private final HashSet<String> methods = new HashSet<>();

    public TrackUserAspect(Logger logger) {
        this.logger = logger;
        methods.add("getAuthenticatedUser");
    }

    private String doSecureParams(String methodName, Object[] params) {
        if (methods.contains(methodName)) {
            return "***[SECURED]***";
        }
        return Arrays.toString(params);
    }

    @Before("@annotation(ru.gb.hw8.aop.TrackUserAction)")
    public void trackUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String params = doSecureParams(methodName, joinPoint.getArgs());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null) ? "User <" + authentication.getName() + ">. " : "";
        logger.log(username + "Calling method: <" + methodName + "> from class: <" + className + "> with parameters: <" + params + ">");

    }
}
