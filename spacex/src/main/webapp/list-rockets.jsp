<%-- 
    Document   : list-rockets
    Created on : Jun 22, 2023, 11:48:51 PM
    Author     : felip
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista de Cohetes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="https://www.unicauca.edu.co" class="navbar-brand"> Aplicación Spacex </a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista de Cohetes</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Lista de Cohetes</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Nuevo Cohete</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>País</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>

                        <!-- for (Todo todo: todos) { -->
                        <c:forEach var="rocket" items="${listRockets}">
                            <tr>
                                <td>
                                    <c:out value="${rocket.rocketId}" />
                                </td>
                                <td>

                                    <c:out value="${rocket.name}" />
                                </td>
                                <td>

                                    <c:out value="${rocket.type}" />
                                </td>
                                <td>

                                    <c:out value="${rocket.country}" />
                                </td>

                                <td><a href="edit?id=<c:out value='${rocket.rocketId}' />">Editar</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${rocket.rocketId}' />">Eliminar</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
