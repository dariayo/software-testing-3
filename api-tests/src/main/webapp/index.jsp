<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="image/cat.png" type="image/x-icon">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <style>
        <%@include file="/css/style.css"%>
    </style>
    <title>Web</title>
</head>

<body background="image/wallpaper.avif">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<header class="header">
    <div>
        Shevchenko Daria P3230
        <br>
        Var 96318
    </div>
</header>

<div class="content">

    <div class="svg-class">
        <svg width="500" height="440" class="svg-graphic" onclick="svgPoint(event)">

            <line class="axis_x" x1="0" x2="500" y1="220" y2="220" stroke="black"></line>
            <polygon fill="black" points="500,220 480,210 480,230" stroke="black"></polygon>
            <line class="axis_y" x1="250" x2="250" y1="0" y2="440" stroke="black"></line>
            <polygon fill="black" points="250,0 260,25 240,25" stroke="black"></polygon>

            <line stroke="black" x1="400" x2="400" y1="220" y2="230"></line>
            <line stroke="black" x1="325" x2="325" y1="220" y2="230"></line>

            <line stroke="black" x1="175" x2="175" y1="220" y2="230"></line>
            <line stroke="black" x1="100" x2="100" y1="220" y2="230"></line>

            <line stroke="black" x1="255" x2="245" y1="370" y2="370"></line>
            <line stroke="black" x1="255" x2="245" y1="295" y2="295"></line>

            <line stroke="black" x1="255" x2="245" y1="70" y2="70"></line>
            <line stroke="black" x1="255" x2="245" y1="145" y2="145"></line>

            <text fill="black" x="255" y="295">-R/2</text>
            <text fill="black" x="255" y="75">R</text>

            <text fill="black" x="255" y="375">-R</text>
            <text fill="black" x="255" y="160">R/2</text>

            <text fill="black" x="395" y="220">R</text>
            <text fill="black" x="315" y="220">R/2</text>

            <text fill="black" x="160" y="220">-R/2</text>
            <text fill="black" x="90" y="220">-R</text>

            <text fill="black" x="490" y="240">x</text>
            <text fill="black" x="235" y="10">y</text>

            <polygon class="svg-figure square"
                     points="175,375 175,220 250,220 250,375"></polygon>

            <polygon class="svg-figure triangle"
                     points="325,220 250,220 250,295"></polygon>

            <path class="svg-figure circle"
                  d="M175,220 Q180,160 250,145 L 250 220 Z"></path>
            <%--@elvariable id="dots" type="org.example.model.PointCollection"--%>
            <c:forEach items="${dots.collection}" var="col">
                <circle class="point" cx="${250 + 150/col.r * col.x}"
                        cy="${220 - 150/col.r * col.y}" r="4"
                        fill="#000000" stroke-width="0"></circle>
            </c:forEach>

        </svg>

    </div>


</div>
<form class="data-send-form" id="form" onsubmit="checkInput();">
    <div class="r-values">
        <label for="r_select">
            <a>R:</a>
        </label>
        <input type="text" id="r_select" name="r_value" maxlength="15" required placeholder="[2; 5]">
    </div>
    <div class="form-inputs">
        <label for="x-value-select" class="form-label">
            <a>X:</a>
        </label>
        <input type="text" id="x-value-select" name="x_value" maxlength="15" required placeholder="[-5; 5]">
    </div>
    <div class="group">
        <label for="y">Y: </label>
        <div class="y-first">
            <input type="text" id="y" name="y" style="display: none;" value="-5">
            <% for (int i = -5; i <= -3; i++) { %>
            <input class="y-button" type="button" name="y" value="<%=i%>"
                   onclick="chooseButton(this,'y-button')">
            <%}%>
        </div>
        <div class="y-sec">
            <% for (int i = -2; i <= 0; i++) { %>
            <input class="y-button" type="button" name="y" value="<%=i%>"
                   onclick="chooseButton(this,'y-button')">
            <%}%>
        </div>
        <div class="y-third">
            <% for (int i = 1; i <= 3; i++) { %>
            <input class="y-button" type="button" name="y" value="<%=i%>"
                   onclick="chooseButton(this,'y-button')">
            <%}%>
        </div>
        <div class="buttons">
            <button class="send-button" type="submit" id="submitButton">Submit</button>
            <button class="clear-button" type="button" id="clearButton" onclick={clearTable()}>Clear</button>
        </div>

    </div>
</form>
<span class="error" id="error"> </span>


<div class="table-form" id="answer">

    <table class="table" id="res">
        <tr class="table-header" id="results">
            <th class="header__item"><a id="x-table" class="space">X</a></th>
            <th class="header__item"><a id="y-table" class="space">Y</a></th>
            <th class="header__item"><a id="r-table" class="space">R</a></th>
            <th class="header__item"><a id="result-table" class="space">result</a>
            </th>
            <th class="header__item"><a id="time-table" class="space">Time</a>
            </th>
            <th class="header__item"><a id="runtime-table" class="space">Runtime</a></th>
        </tr>
        <%--@elvariable id="dots" type="org.example.model.PointCollection"--%>
        <c:forEach items="${dots.collection}" var="col">
            <tr class="table-content">
            <tr class="table-row">
                <td class="table-data">${col.x.toString().format("%.2f", col.x)}</td>
                <td class="table-data">${col.y.toString().format("%.2f", col.y)}</td>
                <td class="table-data">${col.r.toString().trim().format("%.2f", col.r)}</td>
                <td class="table-data">${col.status.toString()}</td>
                <td class="table-data">${col.time.toString()}</td>
                <td class="table-data">${col.runtime.toString()}</td>
            </tr>
        </c:forEach>

    </table>

</div>

<script src="https://unpkg.com/react@18/umd/react.production.min.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@18/umd/react-dom.production.min.js" crossorigin></script>
<div id="root"></div>

<script type="module" src="js/like.js"></script>


<script src="js/validate.js"></script>
<script src="js/sendServer.js"></script>
<script src="js/clearTable.js"></script>
<script src="js/graph.js"></script>


</body>

</html>
