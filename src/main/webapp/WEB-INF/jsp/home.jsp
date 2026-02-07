<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ParkEasy | Home</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <h2>ParkEasy Parking System</h2>

    <p style="text-align:center; color:#666; margin-bottom:25px;">
        Smart Parking Allocation & Billing
    </p>

    <form action="/entry" method="get">
        <button type="submit">Record Entry</button>
    </form>

    <br>

    <form action="/exit" method="get">
        <button type="submit">Record Exit</button>
    </form>
</div>

</body>
</html>
