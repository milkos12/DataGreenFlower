<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="tablasInoformacion">
    <thead>
        <tr>
            <th>Colores</th>
            <th>Degradados</th>
        </tr>
    </thead>
    <c:forEach var="colotesTEM" items="${colores}">
        <tr>
            <td>${colotesTEM.nombre}</td>
            <td>${colotesTEM.id}</td>
        </tr>
    </c:forEach>
</table>