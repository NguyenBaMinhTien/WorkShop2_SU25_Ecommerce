<%-- 
    Document   : replyCustomerCares
    Created on : Jul 3, 2025, 6:49:47 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reply Page</title>
    </head>
    <body>
        <form action="CustomerCaresController" method="post">
            <input type="hidden" name="ticketID" value="${param.ticketID}" />
            <textarea name="reply" rows="2" cols="20" placeholder="Phản hồi..."></textarea><br/>
            <select name="status">
                <option value="answered">Answered</option>
                <option value="closed">Closed</option>
            </select><br/>
            <input type="submit" name="action" value="ReplyTicket" />
        </form>
    </body>
</html>
