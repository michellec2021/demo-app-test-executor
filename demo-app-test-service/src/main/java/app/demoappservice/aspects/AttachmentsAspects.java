/*
 *  Copyright 2019 Qameta Software OÜ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package app.demoappservice.aspects;

import com.wonder.allure.Allure;
import com.wonder.allure.AllureLifecycle;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.qameta.allure.Attachment;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static io.qameta.allure.util.AspectUtils.getParametersMap;
import static io.qameta.allure.util.NamingUtils.processNameTemplate;

/**
 * Aspects (AspectJ) for handling {@link Attachment}.
 *
 * @author Dmitry Baev charlie@yandex-team.ru
 * Date: 24.10.13
 */
@Aspect
@SuppressFBWarnings("MS_SHOULD_BE_FINAL")
public final class AttachmentsAspects {
    public static AllureLifecycle getLifecycle() {
        return LIFE_CYCLE.get();
    }

    /**
     * For tests only.
     *
     * @param allure allure lifecycle to set.
     */
    public static void setLifecycle(final AllureLifecycle allure) {
        LIFE_CYCLE.set(allure);
    }

    private static final InheritableThreadLocal<AllureLifecycle> LIFE_CYCLE = new InheritableThreadLocal<>() {
        @Override
        protected AllureLifecycle initialValue() {
            return Allure.getLifecycle();
        }
    };

    /**
     * Pointcut for things annotated with {@link Attachment}.
     */
    @Pointcut("@annotation(io.qameta.allure.Attachment)")
    public void withAttachmentAnnotation() {
        //pointcut body, should be empty
    }

    /**
     * Pointcut for any methods.
     */
    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    /**
     * Process data returned from method annotated with {@link Attachment}.
     * If returned data is not a byte array, then use toString() method, and get bytes from it.
     *
     * @param joinPoint the join point to process.
     * @param result    the returned value.
     */
    @AfterReturning(pointcut = "anyMethod() && withAttachmentAnnotation()", returning = "result")
    public void attachment(final JoinPoint joinPoint, final Object result) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Attachment attachment = methodSignature.getMethod()
            .getAnnotation(Attachment.class);
        final byte[] bytes;
        if (result instanceof byte[]) {
            bytes = (byte[]) result;
        } else {
            bytes = Objects.toString(result).getBytes(StandardCharsets.UTF_8);
        }
        final String name = attachment.value().isEmpty()
            ? methodSignature.getName()
            : processNameTemplate(attachment.value(), getParametersMap(joinPoint));
        getLifecycle().addAttachment(name, attachment.type(), attachment.fileExtension(), bytes);
    }
}
