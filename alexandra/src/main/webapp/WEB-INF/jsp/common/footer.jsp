<%@ page import="java.text.SimpleDateFormat" %>    
 <%
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
 	java.util.Date date = new java.util.Date();
 %>
 <div class="footer">
  	<div class="container-fluid">
	    <!-- <p class="float-right"><a href="#">Back to top</a></p>  -->
	   	<p class="text-muted credit copyright">Copyright <%=sdf.format(date)%> &#169;
	   	<a href="https://prestonsproductions.com" target="_blank">prestonsproductions.com</a></p>
	   	<p class="text-muted copyright small">
	   	${properties.getString("com.prestonsproductions.alexandra.NAME")} |
	   	${properties.getString("com.prestonsproductions.alexandra.ENVIRONMENT")}
	   	v${properties.getString("com.prestonsproductions.alexandra.VERSION")}
	   	</p>
  	</div>
</div>