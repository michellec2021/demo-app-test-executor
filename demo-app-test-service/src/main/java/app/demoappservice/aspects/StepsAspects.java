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
import com.wonder.log.Logger;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.UUID;

import static io.qameta.allure.util.AspectUtils.getName;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 * Date: 24.10.13
 * @author sskorol (Sergey Korol)
 */
@Aspect
@SuppressFBWarnings("MS_SHOULD_BE_FINAL")
public final class StepsAspects {
    public static AllureLifecycle getLifecycle() {
        return LIFECYCLE.get();
    }

    /**
     * For tests only.
     *
     * @param allure allure lifecycle to set.
     */
    public static void setLifecycle(final AllureLifecycle allure) {
        LIFECYCLE.set(allure);
    }

    private static final InheritableThreadLocal<AllureLifecycle> LIFECYCLE = new InheritableThreadLocal<>() {
        @Override
        protected AllureLifecycle initialValue() {
            return Allure.getLifecycle();
        }
    };

    private final Logger logger = Logger.getLogger(StepsAspects.class);

    @Pointcut("@annotation(io.qameta.allure.Step)")
    public void withStepAnnotation() {
        //pointcut body, should be empty
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    @Before("anyMethod() && withStepAnnotation()")
    public void stepStart(final JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Step step = methodSignature.getMethod().getAnnotation(Step.class);
        final String uuid = UUID.randomUUID().toString();
        String name = getName(step.value(), joinPoint);
        final StepResult result = new StepResult()
            .setName(name)
            .setParameters(null);

        getLifecycle().startStep(uuid, result);
    }

    @AfterThrowing(pointcut = "anyMethod() && withStepAnnotation()", throwing = "e")
    public void stepFailed(final Throwable e) {
        getLifecycle().updateStep(s -> {
            s.setStatus(Status.FAILED).setStatusDetails(getStatusDetails(e).orElse(null));
            logger.info("STEP FAILED: " + s.getName());
        });
        getLifecycle().stopStep();
    }

    @AfterReturning(pointcut = "anyMethod() && withStepAnnotation()")
    public void stepStop() {
        getLifecycle().updateStep(s -> {
            s.setStatus(Status.PASSED);
            logger.info("STEP PASSED: " + s.getName());
        });
        getLifecycle().stopStep();
    }
}
