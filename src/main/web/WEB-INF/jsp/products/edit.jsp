<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<%--<script type="text/javascript" src="/res/js/check_product.js"></script>onsubmit="return checkProduct()"--%>
<div class="container">

    <header class="page-header">
        <h1>Edit Product</h1>
    </header>

    <div class="row">

        <div class="col-md-1"></div>
        <div class="col-md-10 col-md-offset-1">
            <form name="edit_form" action="" method="post">

                <div class="form-group row">

                    <label class="col-md-1" style="text-align: right">Type</label>

                    <div class="col-md-8">
                        <form:select class="selectpicker form-control" path="productToEdit.productType.id"
                                     title="${productToEdit.productType.name}" data-live-search="true">
                            <form:options items="${types}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>

                    <div class="col-md-1">
                        <button type="button" class="btn btn-primary btn-md" data-toggle="modal"
                                data-target="#typeModal">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </div>
                    <div class="modal fade" id="typeModal" tabindex="-1" data-width="760" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header form-group row">
                                    <div class="col-md-11">
                                        <h3 class="modal-title" id="typeModalLabel">Add new type</h3>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" class="close form-control" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </div>
                                <%--<form id="add_type_form" name="add_type_form">--%>

                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <input class="form-control" type="text" name="name"
                                                   placeholder="Enter type here" id="newType"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                    </button>
                                    <input type="button" class="btn btn-primary" onclick="sayHello()" title="Add"/>
                                </div>

                                <%--</form>--%>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="form-group row">
                    <%--<form: path="productToEdit.brand.id"/>--%>

                    <label class="col-md-1" style="text-align: right">Brand</label>

                    <div class="col-md-8">
                        <form:select path="productToEdit.brand.id" class="selectpicker form-control"
                                     title="${productToEdit.brand.name}"
                                     data-live-search="true">
                            <form:options items="${brands}" itemValue="id" itemLabel="name"/>
                        </form:select>
                        <%--<spring:bind path="productToEdit.brand.name">--%>
                        <%--<select class="selectpicker form-control" name="brand" title="${productToEdit.brand.name}"--%>
                        <%--data-live-search="true">--%>
                        <%--<c:forEach items="${brands}" var="brand">--%>
                        <%--<option>${brand.name}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</spring:bind>--%>
                    </div>

                    <div class="col-md-1">
                        <button type="button" class="btn btn-primary btn-md" style="text-align: left"
                                data-toggle="modal" data-target="#brandModal">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </div>
                    <div class="modal fade" id="brandModal" tabindex="-1" data-width="760" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header form-group row">
                                    <div class="col-md-11">
                                        <h3 class="modal-title" id="brandModalLabel">Add new brand</h3>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" class="close form-control" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>

                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <input class="form-control" type="text" name="name"
                                                   placeholder="Enter brand here" id="newBrand"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Add</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class=" form-group row">
                    <label class="col-md-1" style="text-align: right" for="model">Model</label>
                    <div class="col-md-8">
                        <form:input path="productToEdit.model" class="form-control" type="text" name="model"
                                    value="${productToEdit.model}" placeholder="Enter model here" id="model"/>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="form-group row">

                    <form:hidden path="productToEdit.price.lastUpdated"/>

                    <label class="col-md-1" style="text-align: right">Price</label>

                    <div class="col-md-5">

                        <%--<form:input path="productToEdit.price.value" class="form-control" type="text" name="price"--%>
                                    <%--value="${productToEdit.price.value}" placeholder="Enter price here" id="price"/>--%>

                        <form:select path="productToEdit.price.id" class="selectpicker form-control"
                                     data-live-search="true"
                                     title="${productToEdit.price.value}">
                            <form:options items="${prices}" itemValue="id" itemLabel="value"/>
                        </form:select>

                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <form:select path="productToEdit.price.currency.id" class="selectpicker form-control"
                                         data-live-search="true" name="currency"
                                         title="${productToEdit.price.currency.name}">
                                <form:options items="${currencies}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </div>
                    </div>

                    <div class="col-md-1">
                        <button type="button" class="btn btn-primary btn-md" style="text-align: left"
                                data-toggle="modal" data-target="#currencyModal">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </div>
                    <div class="modal fade" id="currencyModal" tabindex="-1" data-width="760" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header form-group row">
                                    <div class="col-md-11">
                                        <h3 class="modal-title" id="currencyModalLabel">Add new currency</h3>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" class="close form-control" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>

                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12 form-group">
                                            <input class="form-control" type="text" name="name"
                                                   placeholder="Enter currency here" id="newCurrency"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Add</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-1"></div>
                <div class="col-md-8">
                    <div class="pull-left">
                        <c:if test="${productToEdit.id != null}">
                            <a href="./delete?id=${productToEdit.id}"
                               onclick="return confirm('Are you sure want delete ${productToEdit.id} item?');"
                               class="btn btn-lg btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </c:if>
                    </div>
                    <div class="pull-right">
                        <a href="./" class="btn btn-lg btn-info">
                            <i class="fa fa-mail-reply"></i>
                        </a>
                        <button class="btn btn-lg btn-success" type="submit" name="add">
                            <i class="fa fa-check-square-o"></i>
                        </button>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </form>
        </div>
        <div class="col-md-1"></div>
    </div>

    <c:if test="${errors != null}">
        <br>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="alert alert-danger">${errors}</div>
            </div>
            <div class="col-md-2"></div>
        </div>
    </c:if>
</div>
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
<script>

    $('#add_type_form').on('submit', function (e) {
        e.preventDefault();
        $.ajax({
            url: "/productType/addProduct",
            type: "POST",
            data: $("add_type_form").serialize(),
            success: function (data) {
                alert("Successfully submitted.")
            }
        });
    });
    sayHello = function () {
        $.ajax({
            url: "/productType/addProduct",
            type: "POST",
            data: $("#add_type_form").serialize(),
            success: function (data) {
                alert("Successfully submitted.")
            }
        });
    };
    $('#sendmail').submit(function () {
        var that = this;
        $('#response').html("<b>Loading response...</b>");
        $.ajax({
            type: 'POST',
            url: 'proccess.php',
            data: $(that).serialize()
        })
                .done(function (data) {
                    $('#response').html(data);

                })
                .fail(function () {
                    alert("Posting failed.");

                });
        return false;
    });
</script>
</body>
</html>
