<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<title>Parsing result</title>
</head>
<body>
	<table>
		<tr>
			<th>Manufacturer</th>
			<th>Modelname</th>
			<th>Category</th>
			<th>Partnumber</th>
			<th>Origin</th>
			<th>Releasedate</th>
			<th>Price, currency</th>
			<th>Price, value</th>
		</tr>
		<c:forEach var="device" items="${deviceSet}">
			<tr>
				<td><c:out value="${device.manufacturer}"></c:out></td>
				<td><c:out value="${device.modelname}"></c:out></td>
				<td><c:out value="${device.category}"></c:out></td>
				<td><c:out value="${device.partnumber}"></c:out></td>
				<td><c:out value="${device.origin}"></c:out></td>
				<td><c:out value="${device.releasedate}"></c:out></td>
				<td><c:out value="${device.price.currency}"></c:out></td>
				<td><c:out value="${device.price.value}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>