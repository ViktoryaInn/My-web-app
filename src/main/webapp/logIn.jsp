<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Log In</title>
        <link href="files.css" rel="stylesheet" type="text/css">
    </head>
    <body class="layer1" id="center">
        <form action="logIn" method="POST">
            Login: <input type="text" name="login"/><br>${login}
            Password: <input type="password" name="password"/><br>${password}
            <input type="submit" value="Sign In"/>

            <a href="http://localhost:8000/registration">Sign up</a>
        </form>
    </body>
</html>