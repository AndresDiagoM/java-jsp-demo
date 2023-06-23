<%-- 
    Document   : rocket-form
    Created on : Jun 23, 2023, 12:12:51 AM
    Author     : felip
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Formulario Cohete</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="https://www.unicauca.edu.co" class="navbar-brand"> Aplicación SpaceX </a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Cohete</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${rocket != null}">
                        <form action="update" method="post">
                    </c:if>

                    <c:if test="${rocket == null}">
                        <form action="insert" method="post">
                    </c:if>
                            
                    <caption>
                        <h2>
                            <c:if test="${rocket != null}">
                                Editar Cohete
                            </c:if>

                            <c:if test="${rocket == null}">
                                Nuevo Cohete
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${rocket != null}">
                        <fieldset class="form-group">
                        <label>ID</label> 
                        <input type="text" name="id" value="<c:out value='${rocket.rocketId}'/>" 
                               class="form-control" required="required" />
                        </fieldset>
                    </c:if>
                        
                        <fieldset class="form-group">
                            <label>Nombre</label> 
                            <input type="text" value="<c:out value='${rocket.name}' />"
                                    class="form-control" name="name" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Tipo</label> 
                            <input type="text" value="<c:out value='${rocket.type}' />"
                                    class="form-control" name="type">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Actividad</label> 
                            <input type="text" value="<c:out value='${rocket.active}' />" 
                                   class="form-control" name="active">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>País</label> 
                            <input type="text" value="<c:out value='${rocket.country}' />" 
                                   class="form-control" name="country">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Empresa</label> 
                            <input type="text" value="<c:out value='${rocket.company}' />" 
                                   class="form-control" name="company">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Costo</label> 
                            <input type="text" value="<c:out value='${rocket.costPerLaunch}' />" 
                                   class="form-control" name="costPerLaunch">
                        </fieldset>
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
