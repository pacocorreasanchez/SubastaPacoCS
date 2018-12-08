/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author paco
 */
public class AnioCopyrightTag extends TagSupport{
    
    private Calendar cal= Calendar.getInstance();

    
    public int doStartTag() throws JspException {
        Writer out = pageContext.getOut();
        
            try {
                out.write("\u00a9" + cal.get(Calendar.YEAR));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        return Tag.EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }
}
