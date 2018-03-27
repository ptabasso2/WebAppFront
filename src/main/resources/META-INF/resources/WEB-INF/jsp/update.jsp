<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script charset='UTF-8'>window['adrum-start-time'] = new Date().getTime();</script>
<script charset='UTF-8' src="/js/adrum-config Student.js"></script>
<title>Update</title>
</head>
<body>
	<h2>Struts 2 Create, Read, Update and Delete (CRUD) Example using JDBC</h2>
	<form action=updatedetails method="post">
		<pre>
<b>Name:         </b><input type="text" name="sname" value='<s:property value="sname"/>'>
		
		
<b>Email:        </b><input type="email"  name="semail" value='<s:property value="semail"/>'>
		            <input type="hidden" name="semailhidden" value='<s:property value="semail"/>'>
		
<b>Password:     </b><input type="password" name="spass" value='<s:property value="spass"/>'>
		
		
<b>Designation:  </b><input type="text" name="sdeg" value='<s:property value="sdeg"/>'>
		
		<button name="submitType" value="update" type="submit">Update</button>
		</pre>
			<a href="report">
				<button type="button">Report</button>
			</a>
	</form>
	<s:if test="ctr>0">
		<span style="color: red;"><s:property value="msg" /></span>
	</s:if>
	<s:else>
		<span style="color: red;"><s:property value="msg" /></span>
	</s:else>
</body>
</html>