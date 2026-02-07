<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Parking Bill</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <h2>Final Bill</h2>

    <p><b>Vehicle No:</b> ${vehicleNo}</p>
    <p><b>Amount:</b> Rs. ${billAmount}</p>

    <div class="links">
        <a href="/entry">New Entry</a>
        <a href="/exit">Another Exit</a>
    </div>
</div>

</body>
</html>
