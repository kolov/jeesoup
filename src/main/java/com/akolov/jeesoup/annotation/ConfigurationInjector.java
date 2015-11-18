package com.akolov.jeesoup.annotation;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class ConfigurationInjector {
    static final String APP_KEY_MISSING = "No applicatiion key definid; use @ApplicationConfiguration ";

    @Produces
    @ConfigurationValue
    public String injectConfiguration(InjectionPoint ip) throws IllegalStateException {
        return inject(ip, ConfigurationValue.class);

    }

    public String inject(InjectionPoint ip, Class<? extends Annotation> aClass) {
        Object param = ip.getAnnotated().getAnnotation(aClass);
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

        // TODO: Call service here with app key,  property key
        return appKey + ":" ;//+ param.key() + "[" + param.defaultValue() + "]";
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
