<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobile/common/jsp/header.jsp"%>


    <script src="<%=appRoot%>/mobile/custom/mobiscroll/js/mobiscroll.core.js"></script>
    <script src="<%=appRoot%>/mobile/custom/mobiscroll/js/mobiscroll.widget.js" type="text/javascript"></script>
    <script src="<%=appRoot%>/mobile/custom/mobiscroll/js/mobiscroll.scroller.js" type="text/javascript"></script>

    <script src="<%=appRoot%>/mobile/custom/mobiscroll/js/mobiscroll.datetime.js" type="text/javascript"></script>
    <script src="<%=appRoot%>/mobile/custom/mobiscroll/js/mobiscroll.select.js" type="text/javascript"></script>


    <script src="<%=appRoot%>/mobile/custom/mobiscroll/js/i18n/mobiscroll.i18n.zh.js" type="text/javascript"></script>

    <link href="<%=appRoot%>/mobile/custom/mobiscroll/css/mobiscroll.widget.css" rel="stylesheet" type="text/css" />
    <link href="<%=appRoot%>/mobile/custom/mobiscroll/css/mobiscroll.scroller.css" rel="stylesheet" type="text/css" />


    <script type="text/javascript">

    var curr = new Date().getFullYear();
    var opt = {
            'date': {
                preset: 'date',
                invalid: { daysOfWeek: [0, 6], daysOfMonth: ['5/1', '12/24', '12/25'] }
            },
            'datetime': {
                preset: 'datetime',
                minDate: new Date(2012, 3, 10, 9, 22),
                maxDate: new Date(2014, 7, 30, 15, 44),
                stepMinute: 5
            },
            'time': {
                preset: 'time'
            },
            'credit': {
                preset: 'date',
                dateOrder: 'mmyy',
                dateFormat: 'mm/yy',
                startYear: curr,
                endYear: curr + 10,
                width: 100
            },
            'select': {
                preset: 'select'
            },
            'select-opt': {
                preset: 'select',
                group: true,
                width: 50
            }
        }
        $(function () {
                $('#test').scroller('destroy').scroller($.extend(opt['date'], {
                    theme: 'default',
                    mode: 'mixed',
                    lang: 'zh',
                    animate: 'none'
                }));
                $('#city').scroller('destroy').scroller($.extend(opt['select'], {
                    theme: 'default',
                    mode: 'mixed',
                    lang: 'zh',
                    animate: 'none'
                }));
        });
    </script>

            <div data-role="fieldcontain" class="demo demo-date demo-datetime demo-time demo-credit">
                <label for="test">Click here to try</label>
                <input name="test" id="test" class="demo-test-date demo-test-datetime demo-test-time demo-test-credit" />
            </div>
            <div data-role="fieldcontain" class="demo demo-select">
                <label for="city">Click here to try</label>
                <select id="city" class="demo-test-select" data-role="none">
                    <option value="1">Atlanta</option>
                    <option value="2">Berlin</option>
                    <option value="3">Boston</option>
                    <option value="4">Chicago</option>
                    <option value="5">London</option>
                    <option value="6">Los Angeles</option>
                    <option value="7">New York</option>
                    <option value="8">Paris</option>
                    <option value="9">San Francisco</option>
                </select>
            </div>
<%@ include file="/mobile/common/jsp/footer.jsp"%>
