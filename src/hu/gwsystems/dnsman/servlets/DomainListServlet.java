package hu.gwsystems.dnsman.servlets;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import hu.gwsystems.dnsman.PMgr;
import hu.gwsystems.dnsman.entity.DnsDomain;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomainListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = PMgr.getInstance().createEntityManager();
		List<DnsDomain> ddList = em.createQuery( "select d from DnsDomain d" ).getResultList();
		
		Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading( new File( req.getSession().getServletContext().getRealPath( "/WEB-INF/ftl") ) );
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        Template template = cfg.getTemplate("list.ftl");

        /* Create a data-model */
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("domains", ddList );
        
        try {
			template.process( root, resp.getWriter() );
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
