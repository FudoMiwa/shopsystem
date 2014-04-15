<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="de.imut.ec.bshop.*, java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
	 <%  
		String category = request.getParameter("name");
		DBUtil db = new DBUtil();
		int gruppennr = 7;
		%>

<!-- ...................................................................................................................... -->
<!-- ...................................................................................................................... -->
<!-- .........................................Includes Header und Menu..................................................... -->

<jsp:include page="includes/header.jsp" flush="true">
 <jsp:param name="pageTitle" value="${param.name}"/>
 </jsp:include>
<jsp:include page="includes/menu.jsp" />

<!-- ...................................................................................................................... -->
<!-- ...................................................................................................................... -->
<!-- .........................................Hauptseite Ansicht........................................................... -->

<body>

<div id="content" style="height:700px; width:,300px; overflow:auto;">

<% if (category == ""){%>		
		<img src="images/home.png">
<% } else{ %>
		<%		
			List<Buch> lb;
			lb = db.findBuecherByWarengruppe(category,7);
			for (Buch b : lb) {
			%>
					<table style="background-color:white; border-collapse:collapse; padding: 20px; border-collapse: separate; border-spacing: 20px; border: 0px">
						<tr height="30%" style="border-bottom: 5px solid black;">
							<td>
								<img src="<% out.print(b.getBild()); %>">
							</td>
							<td width="60%">
								<h4><% out.print(b.getBezeichnung()); %></h4>
								<h5><% out.print(b.getAutor()); %></h5>
								<h5>ISBN <% out.print(b.getIsbn()); %></h5>
							</td>
							<td width="60%" style="font-color: black">
								<h4>Preis: EUR <fmt:formatNumber pattern="#,##0.00"><% out.print(b.getPreis()); %></fmt:formatNumber></h4>
								<br/>
								<br/>
								<br/>
								<a href="detail.html">Detail</a>
							</td>	
						</tr>
					</table>				
			<%} %>
<% }%>

</div>
</body>
<!-- ...................................................................................................................... -->
<!-- ...................................................................................................................... -->
<!-- .........................................Fußzeile..................................................................... -->

<jsp:include page="includes/footer.jsp" />