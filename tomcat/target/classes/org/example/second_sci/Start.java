package org.example.second_sci;

import mvc.dispatcher.AbstractAnnotationConfigDispatcherServletInitializer;

public class Start extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{Config.class};
    }

    @Override
    protected Class<?>[] getWebConfigClasses() {
        return new Class[]{Config.class};
    }
}
