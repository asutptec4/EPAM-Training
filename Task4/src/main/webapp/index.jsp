<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	input[type=text] {
	width: 50%;
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}
</style>
<title>XML parsers</title>
</head>
<body>
	<p>Choose parser</p>
	<form name="parserForm" action="parse">
		<input type="radio" name="parser" value="dom" checked> DOM
		Parser<br> <input type="radio" name="parser" value="sax">
		SAX Parser<br> <input type="radio" name="parser" value="stax">
		StAX Parser<br>
		<br> Input XML file path: <input type="text" name="filePath"><br>
		<br> <input type="submit" value="Show parse result">
	</form>
</body>
</html>
