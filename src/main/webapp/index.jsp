<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
            <meta charset="UTF-8">
            <title>User files</title>
    </head>
    <body>
        <h3>${now}</h3>
        <h2>${path}</h2>
        <table>
            <tr>
                <th>Файл</th>
                <th>Размер</th>
                <th>Дата</th>
            </tr>
            <tr>
                <c:forEach items="${files}" var="file">
                    <td>
                        <c:if test="${file.isDirectory()}">
                            <img src="https://icon-library.com/images/directory-icon/directory-icon-6.jpg"width="50" height="50"/>
                        </c:if>
                        <c:if test="${file.isFile()}">
                            <img src="https://img.icons8.com/pastel-glyph/2x/file.png" width="50" height="50"/>
                        </c:if>
                        <a href="/files?path=${file.getAbsolutePath()}">${file.getName()}</a>
                    </td>
                    <td>${file.length()} bytes</td>
                    <td>${new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(file.lastModified())}</td>
                </c:forEach>
            </tr>
        <table>
    </body>
</html>
