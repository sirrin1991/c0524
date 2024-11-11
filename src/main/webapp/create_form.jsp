<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/23/2024
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
private String name;
private int price;

<h1>Thêm mới sản phẩm</h1>
<form action="/?action=create_product" method="post">
    Tên: <input type="text" name="name">
    Giá: <input type="number" name="price">
    Loai san pham:
    <select name="category" >
        <c:forEach items="${categories}" var="c">
            <option value="${c.id}">${c.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Thêm mới">
</form>
</body>
</html>
