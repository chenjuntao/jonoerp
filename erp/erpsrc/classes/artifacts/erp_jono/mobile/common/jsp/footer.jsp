<%@ page language="java" pageEncoding="UTF-8"%>    
    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->
    
    <hr>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h3>Settings</h3>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
                </div>
            </div>
        </div>
    </div>

    <footer class="row">
<!--         <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="#" target="_blank"> 探睿电子科技有限公司 </a> 2012 - 2014</p> -->
    </footer>
    </footer>

</div><!--/.fluid-container-->

<!-- external javascript -->

<script src="<%=appRoot %>/mobile/framework/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- mobiscroll -->
<script src="<%=appRoot%>/mobile/common/lib/mobiscroll/js/mobiscroll.core.js"></script>
<script src="<%=appRoot%>/mobile/common/lib/mobiscroll/js/mobiscroll.widget.js" type="text/javascript"></script>
<script src="<%=appRoot%>/mobile/common/lib/mobiscroll/js/mobiscroll.scroller.js" type="text/javascript"></script>

<script src="<%=appRoot%>/mobile/common/lib/mobiscroll/js/mobiscroll.datetime.js" type="text/javascript"></script>
<script src="<%=appRoot%>/mobile/common/lib/mobiscroll/js/mobiscroll.select.js" type="text/javascript"></script>

<script src="<%=appRoot%>/mobile/common/lib/mobiscroll/js/i18n/mobiscroll.i18n.zh.js" type="text/javascript"></script>

<link href="<%=appRoot%>/mobile/common/lib/mobiscroll/css/mobiscroll.widget.css" rel="stylesheet" type="text/css" />
<link href="<%=appRoot%>/mobile/common/lib/mobiscroll/css/mobiscroll.scroller.css" rel="stylesheet" type="text/css" />

<!-- data table plugin -->
<script src='<%=appRoot %>/mobile/common/lib/datatables/jquery.dataTables.min.js'></script>
<link rel="stylesheet" type="text/css" href="<%=appRoot%>/mobile/common/lib/datatables/css/jquery.dataTables.min.css">
	
<link rel="stylesheet" type="text/css" href="<%=appRoot%>/mobile/common/lib/datatables/extensions/FixedColumns/css/dataTables.fixedColumns.css">
<script type="text/javascript" language="javascript" src="<%=appRoot%>/mobile/common/lib/datatables/extensions/FixedColumns/js/dataTables.fixedColumns.js"></script>
		
<!-- application script for Charisma demo -->
<script src="<%=appRoot %>/mobile/framework/js/charisma.js"></script>

</body>
</html>
