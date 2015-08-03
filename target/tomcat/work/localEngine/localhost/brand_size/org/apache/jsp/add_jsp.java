package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge; charset=UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<link href=\"http://i3.sdlcdn.com/img/icons/favicon.ico\" rel=\"shortcut icon\">\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("<script src=\"http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js\"></script>\n");
      out.write("<script src=\"http://code.jquery.com/ui/1.11.4/jquery-ui.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"http://code.jquery.com/jquery-migrate-1.2.1.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"js/brand.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/bootstrap-filestyle-min.js\"></script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("$(\":file\").filestyle();\n");
      out.write("</script>\n");
      out.write("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"css/brand.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<title>Brand Admin</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class = \"header header-topbar\">\n");
      out.write("\t<div id=\"caption\">\n");
      out.write("\t\t<div class=\"logo\">\n");
      out.write("\t\t\t<img src=\"http://i3.sdlcdn.com/img/snapdeal/sprite/snapdeal_logo_v9.png\">\n");
      out.write("\t\t\t<div class=\"logoutbutton\" onclick=\"window.location.href='login.jsp'\">\n");
      out.write("\t\t\t\t<span class=\"glyphicon glyphicon-off\" aria-hidden=\"true\"></span>\n");
      out.write("\t\t\t\t<input id=\"logout\" type = \"button\"  name = \"logout\" value = \"LogOut\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"alert alert-warning ");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\" role=\"alert\">\n");
      out.write("  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n");
      out.write("  <strong>Error is : ");
      if (_jspx_meth_s_005fproperty_005f0(_jspx_page_context))
        return;
      out.write("</strong>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"alert alert-success ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\" role=\"alert\" id=\"result_box\">\n");
      out.write("  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n");
      out.write("  <h4>Your request has been registered and the <strong>Ticket</strong> generated is : <strong>");
      if (_jspx_meth_s_005fproperty_005f1(_jspx_page_context))
        return;
      out.write("</strong></h4>\n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("\t<input type=\"text\" id=\"error\" name=\"error\">\n");
      out.write("</div>\n");
      out.write("<div class=\"mid_pane\">\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"panel panel-success\">\n");
      out.write("\t      <div class=\"myPanelHead panel-heading\">\n");
      out.write("\t        <h3 class=\"panel-title\" id=\"panel-title\">Add Brand<a class=\"anchorjs-link\" href=\"#panel-title\"><span class=\"anchorjs-icon\"></span></a></h3>\n");
      out.write("\t      </div>\n");
      out.write("\t      <div class=\"panel-body\">\n");
      out.write("\t        <form id=\"addbrandform\" action=\"addBrand\"  method=\"post\" enctype=\"multipart/form-data\"> \n");
      out.write("\t\t\t<!-- <form id=\"addbrandform\" action=\"addBrand\" onsubmit=\"return formcheck()\" method=\"post\" enctype=\"multipart/form-data\"> -->\n");
      out.write("\t\t\t<!-- <form id=\"addbrandform\" action=\"addBrand\" method=\"post\" enctype=\"multipart/form-data\">-->\n");
      out.write("\t\t\t<!-- <input type=\"text\" id=\"error\" name=\"error\"> -->\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<div class=\"form-group col-xs-6 item-required\">\n");
      out.write("\t\t\t\t<label class = \"control-label\" for=\"sellerCode\">Seller Code</label>\n");
      out.write("\t\t\t\t<span id=\"required\">*</span>\n");
      out.write("\t\t\t\t<input class=\"form-control\" id = \"sellerCode\" type=\"text\" name=\"sellerCode\">\n");
      out.write("\t\t\t\t<span class=\"error\" id = \"sellerCodeerror\"></span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<div class=\"form-group col-xs-6 item-required\">\n");
      out.write("\t\t\t\t<label class = \"control-label\" for=\"brandName\">Brand Name</label>\n");
      out.write("\t\t\t\t<span id=\"required\">*</span>\n");
      out.write("\t\t\t\t<input class=\"form-control\" id = \"brandName\" type=\"text\" name=\"brandName\">\n");
      out.write("\t\t\t\t<span class=\"error\" id = \"brandNameerror\"></span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<div class=\"form-group col-xs-6 item-required\">\n");
      out.write("\t\t\t\t<label class = \"control-label\" for=\"description\">Description</label>\n");
      out.write("\t\t\t\t<span id=\"required\">*</span>\n");
      out.write("\t\t\t\t<textarea class=\"form-control\" id=\"description\" rows=\"5\" cols=\"25\" name=\"description\"></textarea>\n");
      out.write("\t\t\t\t<span class=\"error\" id = \"descriptionerror\"></span> \n");
      out.write("\t\t\t</div>\n");
      out.write("        \t \n");
      out.write("        \t<div class=\"form-group col-xs-6 item-required\">\n");
      out.write("\t\t\t\t<label class = \"control-label ui-widget\" for=\"cat_name\">Choose Category</label>\n");
      out.write("\t\t\t\t<span id=\"required\">*</span>\n");
      out.write("\t\t\t\t<input class=\"form-control\" id = \"category\" type=\"text\" name=\"cat_name\">\n");
      out.write("\t\t\t\t<span id=\"searchResult\"></span>\n");
      out.write("\t\t\t\t<span class=\"error\" id = \"cat_nameerror\"></span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<div class=\"form-group col-xs-6 item-required\">\n");
      out.write("\t\t\t\t<label class = \"control-label\" for=\"brandImage\">Brand Logo(Should be less than 1 Mb)</label>\n");
      out.write("\t\t\t\t<span id=\"required\">*</span>\n");
      out.write("\t\t\t\t<!-- <input class=\"form-control filestyle\" type=\"file\" id = \"brandImage\" data-iconName=\"glyphicon-inbox\" name=\"brandImage\"/> -->\n");
      out.write("\t\t\t\t<input class=\"form-control\" type=\"file\" id = \"brandImage\" data-iconName=\"glyphicon-inbox\" name=\"brandImage\"/>\n");
      out.write("\t\t\t\t<span class=\"error\" id = \"brandImageerror\"></span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<div id=\"size_chart_box\" class=\"col-xs-12\" style=\"display:none\">\n");
      out.write("\t\t\t\t<span id = \"size_table\"></span>\n");
      out.write("\t\t\t\t<span id= \"size_tableerror\" class=\"error\"></span>\t\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\" form-group col-xs-3\" id=\"submit_button\">\n");
      out.write("\t\t\t\t\t<input class=\"myButton form-control btn btn-success\" id=\"submitbrand\" type = \"button\" onclick=\"return formcheck()\" value = \"Create Brand\"></button>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</form>\n");
      out.write("\t      </div>\n");
      out.write("\t    </div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent(null);
    // /add.jsp(43,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("error == null");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("hidden");
        int evalDoAfterBody = _jspx_th_s_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fproperty_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_005fproperty_005f0 = (org.apache.struts2.views.jsp.PropertyTag) _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_005fproperty_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fproperty_005f0.setParent(null);
    // /add.jsp(45,21) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fproperty_005f0.setValue("error");
    int _jspx_eval_s_005fproperty_005f0 = _jspx_th_s_005fproperty_005f0.doStartTag();
    if (_jspx_th_s_005fproperty_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent(null);
    // /add.jsp(48,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("ticket == null");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("hidden");
        int evalDoAfterBody = _jspx_th_s_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fproperty_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_005fproperty_005f1 = (org.apache.struts2.views.jsp.PropertyTag) _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_005fproperty_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fproperty_005f1.setParent(null);
    // /add.jsp(50,94) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fproperty_005f1.setValue("ticket");
    int _jspx_eval_s_005fproperty_005f1 = _jspx_th_s_005fproperty_005f1.doStartTag();
    if (_jspx_th_s_005fproperty_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f1);
    return false;
  }
}
