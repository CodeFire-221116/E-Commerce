<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <style>
        .pagination {
            display: inline-block;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }
    </style>
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
                    <h5>Products` count <span class="label label-default">${totalProductsCount}</span></h5>
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
                    <%--<th>Price</th>--%>
                    <%--<th>Last update</th>--%>
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
                            <%--<td>${product.price.value} ${product.price.currency.name}</td>--%>
                            <%--<td>${product.price.lastUpdated}</td>--%>
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

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-6">
            <ul class="pagination">
                <li class="${empty param.pageNumber or param.pageNumber le 1 ?'disabled':''}">
                    <a href="/">&laquo;</a>
                    <%--<input type="button" onclick="getListForPage(1)">--%>
                </li>
                <c:forEach var="i" begin="1" end="${numberOfPages}">
                    <li>
                        <a href="/list?pageNumber=${i}&amountPerPage=20">${i}</a>
                            <%--<input type="button" onclick="getListForPage(${i})">--%>
                    </li>
                </c:forEach>
                <li class="${param.pageNumber ge numberOfPages ? 'disabled': ''}">
                    <a href="/list?pageNumber=${numberOfPages}&amountPerPage=20" >&raquo;</a>
                    <%--<input type="button" onclick="getListForPage(${numberOfPages})">--%>
                </li>
            </ul>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
<script>
    getListForPage = function (pageNumber) {
        var amountByPage = 20;

        $.get("/list?pageNumber=" + pageNumber + "&amountPerPage=20", function (data) {
            alert("Data: " + data + "\nStatus: " + status);
        });
    }
</script>
</body>
</html>
