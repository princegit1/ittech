<div id="auto_menu_bar">
    <div class="auto_menu">
    <a id="pull" title="Indiatoday Auto" href="#"><img alt="Indiatoday Auto" src="http://media2.intoday.in/indiatoday/auto/images/responsive-menu-icon.jpg" width="48" height="39"></a>
      <ul class="inav">
        <li><a title="News" href="/auto/category/news/1/989.html">News</a></li>
        <li><a title="New Launches"  href="/auto/category/new-launches/1/990.html">New Launches</a></li>
        <li><a title="Photos"  href="/auto/photos">Photos</a></li>
        <!--<li><a href="/auto/videos">Videos</a></li>-->
        <li><a title="Reviews"  href="/auto/category/reviews/1/987.html">Reviews</a></li>
        <li><a title="Road Trips"  href="/auto/road-trips">Road Trips</a></li>
        <li><a title="The Advisor"  href="/auto/category/the-advisor/1/992.html">The Advisor</a></li>
        <li><a title="Spy Shots"  href="/auto/category/spy-shots/1/1068.html">Spy Shots</a></li> 
        <li><a title="Press Releases"  href="/auto/category/auto-press-releases/1/1022.html">Press Releases</a></li>
        <li><a title="Vintage Cars"  href="/auto/vintage-cars">Vintage Cars</a></li>
        <%/*<li><a href="#">Tips and tricks</a></li>
        <li><a href="#">Price list</a></li>*/ %>
        <li><a href="#" title="Auto Search" class="serch_btn"></a></li>
      </ul>
    </div>
  </div>
   <div id="search-saction">
<div class="search-inner">
<form action="/auto/advanced_autosearch.jsp" method="get" name="searchtech" onsubmit="return autosearch();" style="margin:0;padding:0;">
<input type="hidden" name="option" value="com_search">
<input type="text" class="input-box" name="searchword" id="mod_search_searchword" value="Type Your search" onblur="if(this.value=='') this.value='Type Your search';" onfocus="if(this.value=='Type Your search') this.value='';"> 
<input type="submit" name="" class="submit-btn" value="search">
</form> 
</div>
</div>
<%
if(URL.matches("/") || URL.matches("/index.jsp")|| URL.matches("/index1.jsp")){ %>

<!-- Javascript tag: -->
    <!-- begin ZEDO for channel:  IT RichMedia , publisher: India Today , Ad Dimension: Side kick 1x1 - 1 x 1 -->
    <script language="JavaScript">
    var zflag_nid="821"; var zflag_cid="2045/1137"; var zflag_sid="2"; var zflag_width="1"; var zflag_height="1"; var zflag_sz="67"; 
    </script>
    <script language="JavaScript" src="http://d2.zedo.com/jsc/d2/fo.js"></script>
    <!-- end ZEDO for channel:  IT RichMedia , publisher: India Today , Ad Dimension: Side kick 1x1 - 1 x 1 -->
<% } else  { %>
<!-- Javascript tag: -->
<!-- begin ZEDO for channel:  IT RichMedia Ros , publisher: India Today , Ad Dimension: Side kick 1x1 - 1 x 1 -->
<script language="JavaScript">
var zflag_nid="821"; var zflag_cid="2044/1137"; var zflag_sid="2"; var zflag_width="1"; var zflag_height="1"; var zflag_sz="67"; 
</script>
<script language="JavaScript" src="http://d2.zedo.com/jsc/d2/fo.js"></script>
<!-- end ZEDO for channel:  IT RichMedia Ros , publisher: India Today , Ad Dimension: Side kick 1x1 - 1 x 1 -->
<% } %>
