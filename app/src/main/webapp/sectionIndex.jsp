<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Section Application</title>
</head>
<body>
<center>
    <h1>Sections Management</h1>
    <h2>
        <a href="/new">Add New Section</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Sections</a>

    </h2>
</center>
<div align="center">
    <c:if test="${section != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${section == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${section != null}">
                            Edit Section
                        </c:if>
                        <c:if test="${section == null}">
                            Add New Section
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${section != null}">
                    <input type="number" name="id" value="<c:out value='${section.id}' />" />
                </c:if>
                <tr>
                    <th>Section name: </th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${section.section_name}' />"
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