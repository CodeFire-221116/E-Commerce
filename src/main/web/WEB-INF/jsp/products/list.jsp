<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 11/29/16
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PostData</title>
    <%@include file="/WEB-INF/jsp/stylesheet.jsp" %>
</head>
<body>
<div class="container">

    <header class="page-header">
        <h1>Products</h1>
    </header>

    <div class="row">

        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h5>Products` count <span class="label label-default">${products.size()}</span></h5>
                </div>
                <div class="panel-body text-center">
                    <a class="btn btn-success pull-center" href="./new">
                        <i class="fa fa-fw fa-plus"></i>
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-8">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Model</th>
                    <th>Type</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Last update</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.model}</td>
                        <td>${product.productType.name}</td>
                        <td>${product.brand.name}</td>
                        <td>${product.priceValue} ${product.currency.name}</td>
                        <td>${product.lastUpdated}</td>
                        <td nowrap>
                            <a href="./edit?id=${product.id}"
                               class="btn btn-md btn-warning">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="./delete?id=${product.id}"
                               onclick="return confirm('Are you sure want delete ${product.id} item?');"
                               class="btn btn-md btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
</body>
</html>
