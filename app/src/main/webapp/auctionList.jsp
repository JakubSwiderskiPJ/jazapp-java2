<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Auctions Application</title>
</head>
<body>
<center>
    <h1>Auctions Management</h1>
    <h2>
        <a href="/new">Add New Auctions</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Auctions</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Auctions</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="auction" items="${listAuctions}">
            <tr>
                <td><c:out value="${auction.id}" /></td>
                <td><c:out value="${auction.title}" /></td>
                <td><c:out value="${auction.description}" /></td>
                <td><c:out value="${auction.price}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${auction.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${auction.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>