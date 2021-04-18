<%-- 
    Document   : webinsertardoctor
    Created on : 16-abr-2021, 19:52:40
    Author     : lscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller" class="controllers.ControllerDoctor" scope="request"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insertar Doctor</h1>
        <a href="webdoctores.jsp">Volver</a>
        <form method = "post">
            <label>hcod</label>
            <input type="text" name="cjhcod"/><br>
            <label>dcod</label>
            <input type="text" name="cjdcod"/><br>
            <label>Ape</label>
            <input type="text" name="cjape"/><br>
            <label>Espec</label>
            <input type="text" name="cjespes"/><br>
            <label>Sal</label>
            <input type="text" name="cjsal"/><br>
            <button type="submit">Insertar doc.</button>
        </form>
        
    <%  String ui = request.getParameter("cjdcod");
        if(ui != null){
            int dcod = Integer.parseInt(ui);
            String hc = request.getParameter("cjhcod");
            int hcod = Integer.parseInt(hc);
            String ape = request.getParameter("cjape");
            String espec = request.getParameter("cjape");
            String sl = request.getParameter("cjape");
            int sal = Integer.parseInt(sl);
            String msj = controller.insertDoctor(hcod, dcod, ape, espec, sal);
            out.println(msj);    
        }
    %>
    </body>
</html>
