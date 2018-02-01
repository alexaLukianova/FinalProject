package ua.nure.lukianova.SummaryTask4.tag;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.apache.commons.collections4.MapUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ValidateTag extends BodyTagSupport {
    private static final long serialVersionUID = 4755854507975485172L;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ValidateTag.class);
    private static final String TYPE_PASSWORD = "password";
    private static final String TYPE_TEXT = "text";


    private Map<String, String> errors;
    private String placeholder;
    private String label;
    private String value;
    private String name;
    private String type;
    private Boolean passwordType;


    @Override
    public int doAfterBody() {
        try {
            setType(passwordType);
            BodyContent bodyContent = super.getBodyContent();
            String bodyString = bodyContent.getString();
            JspWriter out = bodyContent.getEnclosingWriter();

            out.write(String.join(EMPTY,
                    "<label class=\"form-control-label\">", defaultString(label, EMPTY), "</label>"));

            if (isEmpty(errors)) {
                out.write(isNotEmpty(value) ? setSuccess() : setInit());
            } else {
                out.write(errors.containsKey(name) ? setError(bodyString) : setSuccess());
            }

            bodyContent.clear(); // empty buffer for next evaluation
        } catch (IOException e) {
            System.out.println("Error in BodyContentTag.doAfterBody()" + e.getMessage());
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    private void setType(Boolean password) {
        this.type = Objects.nonNull(password) ? TYPE_PASSWORD : TYPE_TEXT;
    }

    private String setInit() {
        StringBuilder result = new StringBuilder("<div class=\"form-group\">");
        result.append("<input type=\"").append(type).append("\"class=\"form-control form-control-sm\"name=\"")
                .append(name).append("\"placeholder=\"").append(defaultString(placeholder, EMPTY)).append("\">")
                .append("<div class=\"form-text\"></div></div>");
        return result.toString();
    }

    private String setError(String bodyContent) {
        StringBuilder result = new StringBuilder("<div class=\"form-group has-danger\">");
        result.append("<input type=\"").append(type).append("\"class=\"form-control form-control-sm is-invalid\"name=\"")
                .append(name).append("\" value=\"").append(value).append("\">");
        result.append("<div class=\"invalid-feedback\">").append(bodyContent).append("</div></div>");
        return result.toString();
    }

    private String setSuccess() {
        StringBuilder result = new StringBuilder("<div class=\"form-group has-success\">");
        result.append("<input type=\"").append(type).append("\"class=\"form-control form-control-sm is-valid\"name=\"")
                .append(name).append("\" value=\"").append(value).append("\">");
        result.append("<div class=\"valid-feedback\"></div></div>");

        return result.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setLabel(String label) {
        this.label = label;

    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPasswordType(Boolean passwordType) {
        this.passwordType = passwordType;
    }
}

