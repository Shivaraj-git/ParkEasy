<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin Slot Allocation</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <h2>Admin Slot Allocation</h2>

    <form action="/admin/slots" method="post">
        <label>Shop No</label>
        <input type="number" name="shopNo" required>

        <label>Number of Slots</label>
        <input type="number" name="slotCount" required>

        <button type="submit">Create Slots</button>
    </form>

    <div class="message success">${message}</div>

    <div class="links">
        <a href="/">Home</a>
    </div>
</div>

</body>
</html>
