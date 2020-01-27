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
        <a href="/new">Add New Auction</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Auctions</a>

    </h2>
</center>
<div align="center">
    <c:if test="${auction != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${auction == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${auction != null}">
                            Edit Auction
                        </c:if>
                        <c:if test="${auction == null}">
                            Add New Auction
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${auction != null}">
                    <input type="hidden" name="id" value="<c:out value='${auction.id}' />" />
                </c:if>
                <tr>
                    <th>Title: </th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${auction.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Author: </th>
                    <td>
                        <input type="text" name="author" size="45"
                               value="<c:out value='${auction.description}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Price: </th>
                    <td>
                        <input type="text" name="price" size="5"
                               value="<c:out value='${auction.price}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>