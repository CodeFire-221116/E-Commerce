<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 1/31/17
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
<div class="container">
    <header class="page-header">
        <h1>Shopping cart</h1>
    </header>
    <div class="row">
        <div class=" col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Count</th>
                    <th>Price</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartContent}" var="prod">

                    <tr>
                        <td>${prod.key.id}</td>
                        <td>${prod.key.brand.name} ${prod.key.productType.name} ${prod.key.model}</td>
                        <td>${prod.value}</td>
                        <td></td>
                        <td nowrap>
                            <a href="/cart/remove?productId=${prod.key.id}"
                               onclick="return confirm('Are you sure want delete ${prod.key.id} item?');"
                               class="btn btn-md btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
