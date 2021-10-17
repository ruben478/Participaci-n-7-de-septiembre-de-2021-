//PARTICIPACION 07/09/2021
package com.emergentes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "EjercServlet", urlPatterns = {"/EjercServlet"})
public class EjercServlet extends HttpServlet {
    @Override
    //solo trabajaremos con el metodo Do Get debido a que estamos utilizando enlaces
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String valor=null;
        String visita=null;
        String mensaje = null;
        boolean nuevavisita=true;
        //obtener el arreglo de cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            //estructura reducida para recorrer con un objeto los elementos del arreglo
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("visitante")) {
                    nuevavisita = false;
                    valor=cookies[i].getValue();
                    break;
                }
            }
            //for (Cookie c:cookies) {
                //si el nombre de la cookie es visitante? y el valor SI?
                //if (c.getName().equals("visitante")&&c.getValue().equals("SI")) {
                    //nuevavisita = false;
                    
                    //break;
                //}
            //}
        }
        if (nuevavisita) {
            Cookie ck = new Cookie("visitante","1");
            //para establecer la duracion del cookie
            ck.setMaxAge(30);
            //para poner un comentario
            ck.setComment("Control de nuevos visitantes");
            //poner la cookie en el navegador
            response.addCookie(ck);
            mensaje="Gracias por visitar la pagina"; 
            visita="Nro de Visitas: 1";
        }else{
           int cont = Integer.parseInt(valor);
           cont++;
            Cookie ck = new Cookie("visitante",""+cont);
            ck.setMaxAge(30);
           response.addCookie(ck);
            mensaje="Estamos agradecidos por tenerlo nuevamente";
           visita="Nro de Visitas: "+cont;
        }
        //para mandar un mensaje
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+mensaje+"</h1>");
        out.println("<h3>"+visita+"</h3>");
        out.println("<a href='index.jsp'>Ir al inicio</a>");
    }

}