<%-- 
    Document   : webdoctores
    Created on : 16-abr-2021, 19:52:19
    Author     : lscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller" class="controllers.ControllerDoctor" scope="request"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSON - Crud Doctor</title>
    </head>
    <body>
        <h1>Doctores Api CRUD</h1>
        <a href="webinsertardoctor.jsp">Insertar doctor</a>
    <%  String eliminar = request.getParameter("eliminar");
        if(eliminar != null){
            String msj = controller.deleteDoctor(eliminar);
            out.println(msj);
        }
    %>
        <form method="POST">
            <table>
                <thead>
                    <tr>
                        <th>Hcod</th>
                        <th>Dcod</th>
                        <th>Apellido</th>
                        <th>Especialidad</th>
                        <th>Salario</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%=controller.getDoctores()%>
                </tbody>
            </table>
        </form>
    </body>
</html>
