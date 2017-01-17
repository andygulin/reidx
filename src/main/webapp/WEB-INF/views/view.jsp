<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="container-fluid" style="margin-top:20px;">
    <div class="row">
        <div class="col-md-12">
            <h1><a href="${ctx }">舆情浏览</a></h1>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>ID：</td>
                    <td>${resource.id }</td>
                </tr>
                <tr>
                    <td>reidxId：</td>
                    <td>${resource.reidxId }</td>
                </tr>
                <tr>
                    <td>title：</td>
                    <td>${resource.title }</td>
                </tr>
                <tr>
                    <td>url：</td>
                    <td><a href="${resource.url }" target="_blank">${resource.url }</a></td>
                </tr>
                <tr>
                    <td>referUrl：</td>
                    <td><a href="${resource.referUrl }" target="_blank">${resource.referUrl }</a></td>
                </tr>
                <tr>
                    <td>formatContent：</td>
                    <td>${resource.formatContent }</td>
                </tr>
                <tr>
                    <td>author：</td>
                    <td>${resource.author }</td>
                </tr>
                <tr>
                    <td>contentMediaName：</td>
                    <td>${resource.contentMediaName }</td>
                </tr>
                <tr>
                    <td>words：</td>
                    <td>${resource.words }</td>
                </tr>
                <tr>
                    <td>releaseDate：</td>
                    <td><fmt:formatDate value="${resource.releaseDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td>addTime：</td>
                    <td><fmt:formatDate value="${resource.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td>navigation：</td>
                    <td>${resource.navigation }</td>
                </tr>
                <tr>
                    <td>mediaId：</td>
                    <td>${resource.mediaId }</td>
                </tr>
                <tr>
                    <td>mediaName：</td>
                    <td>${resource.mediaName }</td>
                </tr>
                <tr>
                    <td>mediaUrl：</td>
                    <td>${resource.mediaUrl }</td>
                </tr>
                <tr>
                    <td>sourceType：</td>
                    <td>${sourceTypes[resource.sourceType] }</td>
                </tr>
                <tr>
                    <td>keywords：</td>
                    <td>${resource.keywords }</td>
                </tr>
                <tr>
                    <td>keywordsCode：</td>
                    <td>${resource.keywordsCode }</td>
                </tr>
                <tr>
                    <td>pictureList：</td>
                    <td>
                        <c:if test="${not empty resource.pictureList and fn:length(resource.pictureList)>0 }">
                            <c:forEach var="pl" items="${resource.pictureList }">
                                <img src="${pl }"/><br/><br/>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>ver</td>
                    <td>${resource.ver }</td>
                </tr>
                <tr>
                    <td>_abstract：</td>
                    <td>${resource._abstract }</td>
                </tr>
                <tr>
                    <td>relativity：</td>
                    <td>${resource.relativity }</td>
                </tr>
                <tr>
                    <td>relavancy：</td>
                    <td>${resource.relavancy }</td>
                </tr>
                <tr>
                    <td>relatedFields：</td>
                    <td>${resource.relatedFields }</td>
                </tr>
                <tr>
                    <td>relatedInfos：</td>
                    <td>
                        <c:if test="${fn:length(resource.relatedInfos)>0 }">
                            <c:forEach var="ri" items="${resource.relatedInfos }">
                                ${ri.tag } -> ${ri.clue }<br/><br/>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>repeatCount：</td>
                    <td>${resource.repeatCount }</td>
                </tr>
                <tr>
                    <td>commentCount：</td>
                    <td>${resource.commentCount }</td>
                </tr>
                <tr>
                    <td>clickCount：</td>
                    <td>${resource.clickCount }</td>
                </tr>
                <tr>
                    <td>quoteCount：</td>
                    <td>${resource.quoteCount }</td>
                </tr>
                <tr>
                    <td>titleCrc：</td>
                    <td>${resource.titleCrc }</td>
                </tr>
                <tr>
                    <td>urlCrc：</td>
                    <td>${resource.urlCrc }</td>
                </tr>
                <tr>
                    <td>contentCrc：</td>
                    <td>${resource.contentCrc }</td>
                </tr>
                <tr>
                    <td>repeats：</td>
                    <td>
                        <c:if test="${fn:length(resource.repeats)>0 }">
                            <table class="table table-bordered">
                                <c:forEach var="rp" items="${resource.repeats }">
                                    <tbody>
                                    <tr>
                                        <td>reidxId：</td>
                                        <td>${rp.reidxId }</td>
                                    </tr>
                                    <tr>
                                        <td>url：</td>
                                        <td>${rp.url }</td>
                                    </tr>
                                    <tr>
                                        <td>referUrl：</td>
                                        <td>${rp.referUrl }</td>
                                    </tr>
                                    <tr>
                                        <td>author：</td>
                                        <td>${rp.author }</td>
                                    </tr>
                                    <tr>
                                        <td>releaseDate：</td>
                                        <td><fmt:formatDate value="${rp.releaseDate }"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    </tr>
                                    <tr>
                                        <td>addTime：</td>
                                        <td><fmt:formatDate value="${rp.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    </tr>
                                    <tr>
                                        <td>mediaId：</td>
                                        <td>${rp.mediaId }</td>
                                    </tr>
                                    <tr>
                                        <td>mediaName：</td>
                                        <td>${rp.mediaName }</td>
                                    </tr>
                                    <tr>
                                        <td>mediaUrl：</td>
                                        <td>${rp.mediaUrl }</td>
                                    </tr>
                                    <tr>
                                        <td>titleCrc：</td>
                                        <td>${rp.titleCrc }</td>
                                    </tr>
                                    <tr>
                                        <td>urlCrc：</td>
                                        <td>${rp.urlCrc }</td>
                                    </tr>
                                    <tr>
                                        <td>contentCrc：</td>
                                        <td>${rp.contentCrc }</td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>