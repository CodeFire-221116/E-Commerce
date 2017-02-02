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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h2 class="col-md-2 col-md-offset-5">Products</h2>
    </div>
    <div class="row">
        <table class="col-md-12 table table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Model</th>
                <th>Product Type</th>
                <th>Brand</th>
                <th>Price</th>
                <th style="width: 16%">Last updated</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="prod">

                <tr>
                    <td>${prod.id}</td>
                    <td>${prod.model}</td>
                    <td>${prod.productType.name}</td>
                    <td>${prod.brand.name}</td>
                    <td>${prod.price.value} ${prod.price.currency.name}</td>
                    <td>${prod.price.lastUpdated}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>
</body>
</html>
