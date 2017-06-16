<%--
  Created by IntelliJ IDEA.
  User: Li
  Date: 2017/6/13
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DBLP论文</title>
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/asset/gentelella/vendors/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<%=request.getContextPath()%>/asset/gentelella/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="<%=request.getContextPath()%>/asset/gentelella/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md" ng-controller="myCtrl">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>John Doe</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->
                <br/>
                <!-- sidebar menu -->
                <%@include file="menu.jsp" %>

            </div>
        </div>

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>获取论文</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <form class="x_content">
                                <form class="form-horizontal form-label-left input_mask">
                                    <span class="control-label col-md-3 col-sm-3 col-xs-12" style="font-size: 17px">会议URL:</span>
                                    <div class="col-md-6 col-sm-6 col-xs-12" style="margin-left: -155px">
                                        <input type="text" id="name" ng-model="conferenceUrl" required="required" class="form-control col-md-7 col-xs-12" />
                                        <span style="color: black">*此处填写会议URL链接, 比如<a style="color: red" href="http://dblp.uni-trier.de/db/conf/recsys/" target="view_window">http://dblp.uni-trier.de/db/conf/recsys/</a></span>
                                    </div>
                                    <span class="control-label col-md-3 col-sm-3 col-xs-12" style="font-size: 17px">会议URL:</span>
                                    <div class="col-md-6 col-sm-6 col-xs-12" style="margin-left: -155px">
                                        <input type="text" id="url" ng-model="conferenceUrl" required="required" class="form-control col-md-7 col-xs-12" />
                                        <span style="color: black">*此处填写会议URL链接, 比如<a style="color: red" href="http://dblp.uni-trier.de/db/conf/recsys/" target="view_window">http://dblp.uni-trier.de/db/conf/recsys/</a></span>
                                    </div>
                                    <div><button type="submit" ng-click="spiderByUrl()" class="btn btn-success">爬取</button></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>论文列表</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content">
                                <table id="datatable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th style="width: 40%">会议名称</th>
                                        <th style="width: 40%">论文名称</th>
                                        <th style="width: 20%">论文URL</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr ng-repeat="paper in papers">
                                        <td>{{paper.conference_title}}</td>
                                        <td>{{paper.paper_name}}</td>
                                        <td><a href="{{paper.paper_url}}" target="view_window">{{paper.paper_url}}</a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <span style="font-size: 15px">每页条数:</span>
                                <select style="font-size: 15px; margin-left: 15px" ng-change="changeRows(this)" ng-model="rows">
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select>
                                <div style="float: right;">
                                    <button class=".btn-sm" ng-click="prePage()">上一页</button>
                                    <button class=".btn-sm" ng-click="nextPage()">下一页</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- /page content -->

    </div>
</div>

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/asset/gentelella/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="<%=request.getContextPath()%>/asset/gentelella/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Custom Theme Scripts -->
<script src="<%=request.getContextPath()%>/asset/gentelella/build/js/custom.min.js"></script>

<script src="<%=request.getContextPath()%>/asset/js/angular.min.js"></script>

</body>

<script>
    var app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope) {
        $scope.page = 1;
        $scope.rows = "10";

        $scope.refresh = function () {
            $.ajax({
                url: "/paper/getAllPaper.form",
                type: "post",
                dataType: "json",
                data: {
                    page: $scope.page,
                    rows: $scope.rows
                },
                success: function (data) {
                    $scope.papers = data.data;
                    $scope.$apply();
                }
            });
        };
        $scope.refresh();

        //输入URL后点击爬取
        $scope.spiderByUrl = function () {
            $.ajax({
                url: ""
            })
        };

        //选择每页条数
        $scope.changeRows = function (data) {
            $scope.page = 1;
            $scope.refresh();
        };

        //上一页
        $scope.prePage = function () {
            if ($scope.page == 1) {
                alert("当前为第一页!");
                return;
            } else {
                $scope.page--;
                $scope.refresh();
            }
        };
        //下一页
        $scope.nextPage = function () {
            $scope.page++;
            $scope.refresh();
        };
    });
</script>

</html>