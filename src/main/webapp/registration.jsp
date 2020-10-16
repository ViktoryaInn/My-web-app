<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <link href="files.css" rel="stylesheet" type="text/css">
    </head>
    <body class="layer1" id="center">
        <form action="registration" method="POST">
            Email: <input type="email" name="email"/><br>${email}
            Login: <input type="text" name="login"/><br>${login}
            Password: <input type="password" name="password"/><br>${password}
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>