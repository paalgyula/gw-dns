<html>
<head>
	<title>Domain lista</title>
</head>
<body>
	Domain lista
	<ul>
	<#list domains as domain>
		<li>${domain.domainName} (<a href="?d=${domain.id}">records</a>)</li>
	</#list>
	</ul>
</body>