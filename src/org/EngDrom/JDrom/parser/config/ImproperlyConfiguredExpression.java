
package org.EngDrom.JDrom.parser.config;



public class ImproperlyConfiguredExpression extends RuntimeException {
    private String message;

    public ImproperlyConfiguredExpression(String message) {
        super();

        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}


