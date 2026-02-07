<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Parking Exit</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <h2>Vehicle Exit</h2>

    <form action="/exit" method="post">
        <label>Vehicle No</label>
        <input type="text" name="vehicleNo" required>

        <button type="submit">Generate Bill</button>
    </form>

    <div class="message error">${message}</div>

    <div class="links">
        <a href="/">Home</a>
    </div>
</div>

</body>
</html>
