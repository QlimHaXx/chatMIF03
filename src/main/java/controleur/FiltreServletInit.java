package controleur;

import modele.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FiltreServletInit implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {


        /* Cast des objets request et response */
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpRes = (HttpServletResponse)response;

        //httpRes.setContentType( "text/html; charset=UTF-8");
        httpReq.setCharacterEncoding("UTF-8");


        HttpSession session = httpReq.getSession();
        Utilisateur pseudo = (Utilisateur)session.getAttribute("utilisateurConnecte") ;

        String chemin = httpReq.getRequestURI().substring( httpReq.getContextPath().length() );
        if ( chemin.startsWith( "/css") || chemin.startsWith( "/js" ) || chemin.startsWith( "/images" ) || chemin.startsWith( "/back-office" )
                || chemin.startsWith("/pageAJAX")) {
            filterChain.doFilter( httpReq, httpRes );
            return;
        }


        if(pseudo == null){

            httpReq.getRequestDispatcher("/login").forward(httpReq,httpRes);
            //httpRes.sendRedirect("/login");
        }
        else
        {
            filterChain.doFilter(httpReq, httpRes);
        }
    }

    public void destroy() {

    }

}
