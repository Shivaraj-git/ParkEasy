<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Parking Entry</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <h2>Vehicle Entry</h2>

    <form action="/entry" method="post">
        <label>Vehicle No</label>
        <input type="text" name="vehicleNo" required>

        <label>Shop No</label>
        <input type="number" name="shopNo" required>

        <label>Estimated Hours</label>
        <input type="number" name="estHours" required>

        <button type="submit">Allocate Slot</button>
    </form>

    <div class="message success">${message}</div>

    <div class="links">
        <a href="/">Home</a>
    </div>
</div>

</body>
</html>
