package ua.nure.lukianova.SummaryTask4.tag;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ValidateTag extends TagSupport {
    private static final long serialVersionUID = 4755854507975485172L;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ValidateTag.class);


    private Map<String, String> errors;
    private String placeholder;
    private String labelName;
    private String value;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public void setErrors(Map<String, String> errors) {
        if (Objects.isNull(errors)) {
            errors = new HashMap<>();
        }
        this.errors = errors;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<label class=\"form-control-label\">" + labelName + "*</label>");

            if (!errors.containsKey(name) && Objects.nonNull(value)) {
                out.write("<div class=\"form-group has-success\">");
                out.write("<input type=\"text\"class=\"form-control form-control-sm is-valid\"name=\"" + name
                        + "\" value=\"" + value + "\">");
                out.write("<div class=\"valid-feedback\"></div></div>");
            }
            if (Objects.isNull(errors) && Objects.isNull(value)) {
                out.write("<div class=\"form-group\"");
                out.write("<input type=\"text\"class=\"form-control form-control-sm\"name=\"" +
                        name + "\"placeholder=\"" + placeholder + "\"></div>");
            }
            if (errors.containsKey(name)) {
                out.write("<div class=\"form-group has-danger\">");
                out.write("<input type=\"text\"class=\"form-control form-control-sm is-invalid\"name=\"" +
                        name + "\" value=\"" + value + "\">");
                out.write("<div class=\"invalid-feedback\"><fmt:message key=\""+ errors.get(name)+"\"/></div></div>");

            }
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }

}

// out.write("<label class=\"form-control-label\">" + labelName + "*</label>");
//         out.write("<c:choose>");
//         out.write("<c:when test=\"${not" + errors.containsKey(name) + "&& not empty " + value + "}\">");
//         out.write("<div class=\"form-group has-success\">");
//         out.write("<input type=\"text\"class=\"form-control form-control-sm is-valid\"name=\"" + name
//         + "\" value=\"" + value + "\">");
//         out.write("<div class=\"valid-feedback\"></div></div></c:when>");
//         out.write("<c:when test=\"${ empty " + errors + " &&  empty " + value + "}\">");
//         out.write("<div class=\"form-group\"");
//         out.write("<input type=\"text\"class=\"form-control form-control-sm\"name=\"" +
//         name + "\"placeholder=\"" + placeholder + "\"></div></c:when>");
//         out.write("<c:when test=\"" + errors.containsKey(name) + "\">");
//         out.write("<div class=\"form-group has-danger\">");
//         out.write("<input type=\"text\"class=\"form-control form-control-sm is-invalid\"name=\"" +
//         name + "\" value=\"" + value + "\">");
//         out.write("<div class=\"invalid-feedback\"><fmt:message key=\"" + errors.get(name) + "\"/></div>");
//         out.write("</div></c:when></c:choose>");

