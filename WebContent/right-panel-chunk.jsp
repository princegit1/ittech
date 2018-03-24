<div class="ad" style="width:300px; height:263px;margin:0 0 20px 0px;">
<div style="font-size:11px; color:#ccc; padding:0px 0px 3px 0px ; text-align:center">Advertisement</div>
<script language="JavaScript">
var zflag_nid="821"; var zflag_cid="2236/1137"; var zflag_sid="2"; var zflag_width="300"; var zflag_height="250"; var zflag_sz="9"; var zflag_ct="criteo:"+crtg_content+"^viz300x250:" + vizPAM.isVizInterested("300x250")['int'];
</script><script language="JavaScript" src="http://d2.zedo.com/jsc/d2/fo.js"></script>
</div>
	<% 
if( !URL.matches("/auto/")  ){	%>
<div class="clearfix"></div>
<div style="margin:30px 0"></div>
<% if( URL.contains("/story/") || URL.contains("story.jsp") ){ %> 
<script type="text/javascript">ajaxinclude("/auto/tab-right-chunk.jsp")</script>
<% } else  { %> 
    <jsp:include page="/auto/tab-right-chunk.jsp" flush="true" >
    <jsp:param name="" value="" /> 
  </jsp:include>
<%  } } %>  
     <div class="clearfix"></div>
 
 <% if( URL.matches("/auto/")  ){	%> 
 <%@ include file="chunk_home_tipsTricks.jsp" %>  
<%  } else  { %> 
 <script type="text/javascript">ajaxinclude("/auto/chunk_home_tipsTricks.jsp")</script>
 <% }%>
 
 <div class="clearfix"></div>
<div class="outer_digitalmazine">
<div class="colum " data-tb-region="indiathisweek"> 
 <div data-tb-region="indiathisweek" class="colum " data-tb-shadow-region="indiathisweek">
  <div class="boxcont_thisweek2 last noshadow blkbg margright width47per">
    <div class="padding">
      <jsp:include page="../chunk_static_content.jsp" flush="true" >
<jsp:param name="contentTitle" value="IT-AUTOMAGAZINE"/>
</jsp:include>
    </div>
  </div>
</div>
</div>
</div>
    