package com.akolov.jeesoup.annotation;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * This Producer will provide CDI values from a configuration service.
 *
 * The configuration service requires an application key, each parameter requires key and default value. While the
 * latter two have to be provided for each parameter, we do not want to define the application key
 * more than once, hence the need of a custom annotation for each application key ,
 * see @MyAppConfiguration.
 */
public class ConfigurationValueProducer {
    static final String APP_KEY_MISSING = "No application key defined; use @ApplicationConfiguration directly or through your own annotation ";
    private Set<Class<?>> checkedClasses = new HashSet<>();

    @Produces
    @ConfigurationValue
    public String injectConfiguration(final InjectionPoint ip) {
        final ConfigurationValue param = ip.getAnnotated().getAnnotation(ConfigurationValue.class);
        final Set<Annotation> ans = ip.getAnnotated().getAnnotations();
        String appKey = null;
        for (final Annotation ann : ans) {
            final ApplicationConfiguration configAnnotation = annotatedWith(ann, ApplicationConfiguration.class);
            if (configAnnotation != null) {
                appKey = configAnnotation.applicationKey();
                break;
            }
        }

        if (appKey == null) {
            throw new IllegalStateException(APP_KEY_MISSING);
        }

        final String key = param.key();
        final String defaultValue = param.defaultValue();
        // TODO: Call service here with app key,  property key
        // TODO check if mandatory and null, throw exception
        return appKey + ":" + key + "[" + defaultValue + "]";
    }


    /**
     * Searches for annotation with a given class - either the annotation itself or anontaion used to annotated this
     * one.
     *
     * @param ann    annotation to search
     * @param aClass class to search
     * @return annotation of the class, if found, or null.
     */
    private <T extends Annotation> T annotatedWith(final Annotation ann, final Class<T> aClass) {
        if (checkedClasses.contains(ann.annotationType())) {
            return null;
        }
        if (ann.annotationType().isAssignableFrom(aClass)) {
            return (T) ann;
        }
        checkedClasses.add(ann.annotationType());

        final Class annClass = ann.annotationType();
        for (final Annotation sub : annClass.getAnnotations()) {
            final T result = annotatedWith(sub, aClass);
            if (result != null) {
                return result;
            }
        }
        return null;
    }


}
