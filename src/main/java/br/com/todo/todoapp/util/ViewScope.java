package br.com.todo.todoapp.util;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

import javax.faces.context.FacesContext;
import java.util.Map;

public class ViewScope implements Scope {

    private static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

    @Override
    public synchronized Object get(String name, ObjectFactory<?> objectFactory) {
        Object instance = this.getViewMap().get(name);
        if (instance == null) {
            instance = objectFactory.getObject();
            this.getViewMap().put(name, instance);
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object remove(String name) {
        Object instance = this.getViewMap().remove(name);
        if (instance == null) {
            Map<String, Runnable> callbacks = (Map<String, Runnable>) this.getViewMap().get(VIEW_SCOPE_CALLBACKS);
            if (callbacks != null)
                callbacks.remove(name);
        }
        return instance;
    }

    /**
     * Responsável por registrar uma chamada de destruição ao bean
     * que será armazenadano [b]viewMap[/b] da [b]ViewRoot[/b](nossa página que será mostrada)
     *
     * @param name     - nome do bean
     * @param runnable
     * @see #getViewMap()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        Map<String, Runnable> callbacks = (Map<String, Runnable>) this.getViewMap().get(VIEW_SCOPE_CALLBACKS);
        if (callbacks != null)
            callbacks.put(name, runnable);
    }

    @Override
    public Object resolveContextualObject(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesResquestAttributes = new FacesRequestAttributes(facesContext);
        return facesResquestAttributes.resolveReference(key);
    }

    @Override
    public String getConversationId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesResquestAttributes = new FacesRequestAttributes(facesContext);
        return facesResquestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
    }

    private Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
    }

}