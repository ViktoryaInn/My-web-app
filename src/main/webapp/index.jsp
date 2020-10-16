<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>User files</title>
        <link href="files.css" rel="stylesheet" type="text/css">
    </head>
    <body class="layer1" id="center">
        <h3>${now}</h3>
        <h2>${path}</h2>
        <hr/>
        <img src="/img/icons8-folder-up-64.png" width="30" height="30">
        <a href="/files?path=${path.getParent()}">Вверх</a>
        <table>
            <tr>
                <th width="200">Файл</th>
                <th width="200">Размер</th>
                <th width="200">Дата</th>
            </tr>
                <c:forEach items="${files}" var="file">
                    <c:if test="${!file.isHidden()}">
                        <tr>
                            <td>
                                <c:if test="${file.isDirectory()}">
                                    <img src="/img/icons8-folder-64.png" width="30" height="30"/>
                                </c:if>
                                <c:if test="${file.isFile()}">
                                    <img src="/img/icons8-file-64.png" width="30" height="30"/>
                                </c:if>
                                <a href="/files?path=${file.getAbsolutePath()}">${file.getName()}</a>
                            </td>
                            <td id="center">
                                <c:if test="${file.isFile()}">
                                    ${file.getSize()} B
                                </c:if>
                            </td>
                            <td id="center">${file.getLastModify()}</td>
                        </tr>
                    </c:if>
                </c:forEach>
        <table>
    </body>
</html>
