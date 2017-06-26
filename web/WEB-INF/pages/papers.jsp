<%--
  Created by IntelliJ IDEA.
  User: lirf
  Date: 2017/6/16
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="myApp">
<head>
    <title>Title</title>
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/asset/gentelella/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <%--<!-- Custom Theme Style -->--%>
    <link href="<%=request.getContextPath()%>/asset/gentelella/build/css/custom.min.css" rel="stylesheet">
</head>
<body ng-controller="myCtrl">
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>会议名称</th>
                        <th>论文名称</th>
                        <th>论文URL</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr ng-repeat="paper in papers">
                        <td>{{paper.conference_title}}</td>
                        <td>{{paper.paper_name}}</td>
                        <td><a href="{{paper.paper_url}}" target="view_window">{{paper.paper_url}}</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/asset/gentelella/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="<%=request.getContextPath()%>/asset/gentelella/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AngularJs -->
<script src="<%=request.getContextPath()%>/asset/js/angular.min.js"></script>
</body>
<script>
    var app = angular.module("myApp", []);
    app.controller("myCtrl", function($scope) {

        $.ajax({
            url: "/paper/getAllPaper.form",
            type: "post",
            dataType: "json",
            success: function (data) {
                $scope.papers = data.data;
                $scope.$apply();
            }
        });
    });
</script>
</html>
