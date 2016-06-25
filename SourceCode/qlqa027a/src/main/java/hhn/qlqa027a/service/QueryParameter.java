/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Query Parameter.
 *
 * <pre>
 * // import static com.devsniper.desktop.customers.service.QueryParameter.*;
 * count = categoryService.findWithNamedQuery(Category.FIND_ALL,
 *      with(&quot;name&quot;, "filter").parameters()).size();
 * </pre>
 *
 * @author Cem Ikta
 */
public class QueryParameter {

    private Map<String, Object> parameters = null;

    /**
     * Es wird nur von <code>with</code> neue Instance erstellt.
     *
     * @param name parameter name
     * @param value parameter value
     */
    private QueryParameter(String name, Object value) {
        this.parameters = new HashMap();
        this.parameters.put(name, value);
    }

    /**
     * Erstellt neue QueryParameter instance.
     *
     * @param name parameter name
     * @param value parameter value
     * @return neue Instance von Query Parameter.
     */
    public static QueryParameter with(String name, Object value) {
        return new QueryParameter(name, value);
    }

    /**
     * Fügt neue Parameter hinzu. Das ist nicht <code>and</code> parameter in
     * NamedQuery.
     *
     * @param name parameter name
     * @param value parameter value
     * @return Query Parameter instance
     */
    public QueryParameter and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    /**
     * Liefert parameters Map zurück.
     *
     * @return parameters map
     */
    public Map<String, Object> parameters() {
        return this.parameters;
    }

}
