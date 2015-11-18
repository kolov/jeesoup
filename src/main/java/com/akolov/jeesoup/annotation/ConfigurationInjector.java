package com.akolov.jeesoup.annotation;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * This Injector will provide CDI values from a configuration service. The configuration service requires
 * an application key, parameter key and default value. While the latter two have to be provided for each parameter,
 * it would be annoying to supply the same, often very long, application key on each line
 * where a parameter is injected.
 */
public class ConfigurationInjector {
    static final String APP_KEY_MISSING = "No application key defined; use @ApplicationConfiguration directly or through your own annotation ";
    static final String APP_MISSING_METHOD = "Annotation misses method {0}";
    static final String APP_ERROR_METHOD = "Error executing method {0}";

    @Produces
    @ConfigurationValue
    public String injectConfiguration(InjectionPoint ip) throws IllegalStateException {
        return inject(ip, ConfigurationValue.class);
    }

    public String inject(InjectionPoint ip, Class<? extends Annotation> aClass) {
        Annotation param = ip.getAnnotated().getAnnotation(aClass);
        Set<Annotation> ans = ip.getAnnotated().getAnnotations();
        String appKey = null;
        for (Annotation ann : ans) {
            ApplicationConfiguration result = (ApplicationConfiguration) annotatedWith(ann, ApplicationConfiguration.class);
            if (result != null) {
                appKey = result.applicationKey();
            }
        }

        if (appKey == null) {
            throw new IllegalStateException(APP_KEY_MISSING);
        }

        String key = invokeMethod(param, "key");
        String defaultValue = invokeMethod(param, "defaultValue");
        // TODO: Call service here with app key,  property key
        return appKey + ":" + key + "[" + defaultValue + "]";
    }

    private String invokeMethod(Annotation param, String methodName) {
        String value;
        try {
            Method method = param.annotationType().getMethod(methodName, new Class[]{});
            value = (String) method.invoke(param, new Object[]{});
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(String.format(APP_MISSING_METHOD, methodName));
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(String.format(APP_ERROR_METHOD, methodName));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format(APP_ERROR_METHOD, methodName));
        }
        return value;
    }

    private Set<Class<?>> checkedClasses = new HashSet<>();

    private Object annotatedWith(Annotation ann, Class<?> aClass) {
        System.out.println("Checking " + ann);
        if (checkedClasses.contains(ann.annotationType())) {
            return null;
        }
        if (ann.annotationType().isAssignableFrom(aClass)) {
            return ann;
        }
        checkedClasses.add(ann.annotationType());

        Class annClass = ann.annotationType();
        for (Annotation sub : annClass.getAnnotations()) {
            Object result = annotatedWith(sub, aClass);
            if (result != null) {
                return result;
            }
        }

        return null;
    }


}
